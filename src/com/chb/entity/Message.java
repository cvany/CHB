package com.chb.entity;
/**
 * 消息实体类
 * @author 崔文元
 * 2017年11月30日
 *
 */
public class Message {
	private Integer id;
	private Integer sender;
	private Integer receiver;
	private Integer type;
	private String content;
	
	public Message() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSender() {
		return sender;
	}

	public void setSender(Integer sender) {
		this.sender = sender;
	}

	public Integer getReceiver() {
		return receiver;
	}

	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

}
