package com.liumi.base.exception;

import com.alibaba.fastjson.JSON;

import com.liumi.base.model.Response;
import com.liumi.base.model.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * Dubbo统一异常处理
 *
 * @author liangyq
 * @version [1.0, 2018/5/31 15:59]
 */

@Slf4j
@Component("exceptionAdvice")
public class ExceptionAdvice {

	private final String FAIL_CODE = "2000";
	private final String FAIL_DESC = "服务调用失败";

	@SuppressWarnings("unchecked")
	public <T> Response<T> exceptionIntercept(ProceedingJoinPoint joinPoint) {
		try {
			Object response = joinPoint.proceed();
			if (response instanceof Response) {
				return (Response<T>) response;
			} else {
				return ResponseBuilder.buildResponse(FAIL_CODE, FAIL_DESC);
			}
		} catch (Throwable tw) {
			final String methodName = joinPoint.getSignature().getDeclaringTypeName() + "#" +
					joinPoint.getSignature().getName();
			final String methodArg = buildMethodArgs(joinPoint);
			log.error("系统异常, 业务方法:{}, 方法入参: {}", methodName, methodArg, tw);
			return ResponseBuilder.buildResponse(FAIL_CODE, FAIL_DESC);
		}
	}

	private String buildMethodArgs(ProceedingJoinPoint joinPoint) {
		Object[] argObjs = joinPoint.getArgs();
		String methodArg = "";
		StringBuilder args = new StringBuilder();
		if (argObjs != null && argObjs.length > 0) {
			for (Object arg : argObjs) {
				args.append(arg.getClass()).append(JSON.toJSONString(arg)).append(",");
			}
			methodArg = args.substring(0, args.length() - 1);
		}
		return methodArg;
	}

}
