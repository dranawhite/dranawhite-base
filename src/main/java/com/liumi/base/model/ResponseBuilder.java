package com.liumi.base.model;

/**
 * Response构造器
 *
 * @author liangyq
 * @version [1.0, 2018/5/30 15:06]
 */
public class ResponseBuilder {

	public static <T> Response<T> buildResponse(String code, String desc, T detailInfo) {
		Response<T> response = new Response<>();
		response.setRespCode(code);
		response.setRespMsg(desc);
		response.setDetailInfo(detailInfo);
		return response;
	}

	public static <T> Response<T> buildResponse(String code, String desc) {
		return buildResponse(code, desc, null);
	}

}
