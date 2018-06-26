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
 * @author liangyq
 * @version [1.0, 2018/6/12 18:03]
 */
@Activate(group = {Constants.CONSUMER}, order = -999)
public class DubboConsumerLogFilter extends DubboLogFilter {

	private static final String DUBBO_CONSUMER_LOG = "dubbo.consumer";
	private final static Logger LOGGER_CONSUMER = LoggerFactory.getLogger(DUBBO_CONSUMER_LOG);

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		// 开始执行时间
		long startTime = System.currentTimeMillis();
		Result result = invoker.invoke(invocation);
		long elaspedTime = System.currentTimeMillis() - startTime;

		String args = result.toString();
		DubboLogBean logBean = buildDubboLogBean(invoker, invocation, args, elaspedTime);
		LOGGER_CONSUMER.info(logBean.toString());
		return result;
	}
}
