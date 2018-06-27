package com.liumi.base.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dubbo Provider日志
 *
 * @author liangyq
 * @version [1.0, 2018/6/12 18:11]
 */
@Activate(group = {Constants.PROVIDER}, order = -998)
public class DubboProviderLogFilter extends DubboLogFilter {

	private static final String DUBBO_PROVIDER_LOG = "dubbo.provider";
	private final static Logger LOGGER_PROVIDER = LoggerFactory.getLogger(DUBBO_PROVIDER_LOG);

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		// 开始执行时间
		long startTime = System.currentTimeMillis();
		Result result = invoker.invoke(invocation);
		long elaspedTime = System.currentTimeMillis() - startTime;

		Object[] objs = invocation.getArguments();
		StringBuilder sb = new StringBuilder();
		for (Object obj : objs) {
			sb.append(obj).append("|");
		}
		String args = sb.toString().substring(0, sb.length() - 1);

		DubboLogBean logBean = buildDubboLogBean(invoker, invocation, args, elaspedTime);
		LOGGER_PROVIDER.info(logBean.toString());
		return result;
	}
}
