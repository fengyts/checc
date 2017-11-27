package com.checc.util;

import ng.bayue.common.CommonResultMessage;

public class ResultMessage extends CommonResultMessage {

	private static final long serialVersionUID = -5485919363820147146L;

	public ResultMessage() {
	}

	// public ResultMessage(int result) {
	// this.result = result;
	// }

	public ResultMessage(Object data) {
//		this.data = data;
		super(data);
	}

	public ResultMessage(int result, String message) {
		// this.result = result;
		// this.message = message;
//		this(result, message, null);
		super(result, message, null);
	}

	public ResultMessage(String message, Object data) {
//		this.message = message;
//		this.data = data;
		super(message, data);
	}

	public ResultMessage(int result, String message, Object data) {
//		this.result = result;
//		this.message = message;
//		this.data = data;
		super(result, message, data);
	}


	/**
	 * <pre>
	 * 校验入参为空时返回值
	 * </pre>
	 *
	 * @return
	 */
	public static ResultMessage validParamResult() {
		return new ResultMessage(Failure, Messages.ParameterNull);
	}

	/**
	 * <pre>
	 * 校验数据已经存在时返回值
	 * </pre>
	 *
	 * @return
	 */
	public static ResultMessage validIsExist() {
		return new ResultMessage(Failure, Messages.IsExist);
	}

	/**
	 * <pre>
	 * 校验数据正在使用
	 * </pre>
	 *
	 * @return
	 */
	public static ResultMessage validIsUsed() {
		return new ResultMessage(Failure, Messages.IsUsed);
	}

	/**
	 * <pre>
	 * 校验必填参数项为空时返回值
	 * </pre>
	 *
	 * @param parameters
	 * @return
	 */
	public static ResultMessage validParameterNull(String... parameters) {
		if (null == parameters) {
			return new ResultMessage(Failure, Messages.ParameterNull);
		}
		return new ResultMessage(Failure, Messages.parameterErrMsgs(parameters));
	}

	/**
	 * <pre>
	 * 服务器内部异常
	 * </pre>
	 *
	 * @return
	 */
	public static ResultMessage serverInnerError() {
		return new ResultMessage(Failure, Messages.ServerInnerError);
	}

	public static ResultMessage success() {
		return new ResultMessage(Success, Messages.HandleSuccess);
	}
	
	public static ResultMessage failure(){
		return new ResultMessage(Failure, Messages.HandleFailure);
	}
	
	public static ResultMessage failure(String message){
		return new ResultMessage(Failure, message);
	}

}
