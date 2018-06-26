package com.liumi.base.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcContext;

/**
 * Dubbo调用日志和消费日志
 *
 * @author liangyq
 * @version [1.0, 2018/6/12 15:12]
 */
public abstract class DubboLogFilter implements Filter {

	protected DubboLogBean buildDubboLogBean(Invoker<?> invoker, Invocation invocation, String args, long elaspedTime) {
		DubboLogBean logBean = new DubboLogBean();
		RpcContext context = RpcContext.getContext();
		logBean.setFromAddress(context.getLocalAddressString());
		logBean.setToAddress(context.getRemoteAddressString());
		String qualifiedName = invoker.getInterface() + "#" + invocation.getMethodName();
		logBean.setQualifiedName(qualifiedName);
		logBean.setArgs(args);
		logBean.setVersion(invoker.getUrl().getParameter(Constants.VERSION_KEY));
		logBean.setGroup(invoker.getUrl().getParameter(Constants.GROUP_KEY));
		logBean.setElaspedTime(elaspedTime);
		return logBean;
	}

}
