package com.dranawhite.base.mybatis.plugin;

import java.io.Serializable;

public class PersistenceObject implements Serializable {

	private static final long serialVersionUID = -7148901803997759356L;

	/**
	 * 当前页
	 */
	private int pageNum = 1;
	/**
	 * 每页条数
	 */
	private int pageSize = 10;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
