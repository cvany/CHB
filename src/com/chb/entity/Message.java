package com.chb.entity;
/**
 * ��Ϣʵ����
 * @author ����Ԫ
 * 2017��11��30��
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
