package com.liumi.base.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应
 *
 * @author liangyq
 * @version [1.0, 2018/5/30 14:59]
 */
@Data
public class Response<T> implements Serializable {

	private static final long serialVersionUID = -7794865625869433250L;

	private String respCode;

	private String respMsg;

	private T detailInfo;
}
