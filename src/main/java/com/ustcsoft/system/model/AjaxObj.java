package com.ustcsoft.system.model;

/**
 * 传递ajax消息的传输层对象
 * 
 * @author 吴金华
 * @since 2017年1月5日
 */
public class AjaxObj implements java.io.Serializable {
	private static final long serialVersionUID = -8820644469868938235L;
	private Object obj;// 要写到的对象
	private int code;// 消息编码
	private String msg;// 要携带的消息
	
	private String id;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
