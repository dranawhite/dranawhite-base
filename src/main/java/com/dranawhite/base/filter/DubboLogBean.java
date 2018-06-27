package com.dranawhite.base.filter;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liangyq
 * @version [1.0, 2018/6/12 15:48]
 */
@Data
public class DubboLogBean implements Serializable {

	private static final long serialVersionUID = -7642910868194250951L;

	private long elaspedTime;

	private String fromAddress;

	private String toAddress;

	private String qualifiedName;

	private String version;

	private String group;

	private String args;
}
