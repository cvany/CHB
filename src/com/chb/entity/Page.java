package com.chb.entity;

/**
 * 分页类
 * @author shilim
 *
 */
public class Page {
	/**
	 * 当前页
	 */
	private Integer pageNum;
	/**
	 * 每页记录数
	 */
	private Integer pageSize;
	/**
	 * 排序方式
	 */
	private String order;
	/**
	 * 分页条件
	 */
	private String keyWords;
	/**
	 * 是否模糊查询
	 */
	private Boolean fuzzy;
	/**
	 * 数据库查询开始行数
	 */
	private Long startColum;
	
	public Page() {
		super();
	}

	/**
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param order
	 * @param keyWords
	 * @param fuzzy
	 */
	public Page(Integer pageNum, Integer pageSize, String order, String keyWords, Boolean fuzzy) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.order = order;
		this.keyWords = keyWords;
		this.fuzzy = fuzzy;
		this.startColum = (long) ((pageNum-1) * pageSize);
	}
	
	public void coutStartColum() {
		startColum = (long) ((pageNum-1) * pageSize);
	}
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public Boolean getFuzzy() {
		return fuzzy;
	}
	public void setFuzzy(Boolean fuzzy) {
		this.fuzzy = fuzzy;
	}
	public Long getStartColum() {
		return startColum;
	}
	public void setStartColum(Long startColum) {
		this.startColum = startColum;
	}

	@Override
	public String toString() {
		return "Page [pageNum=" + pageNum + ", pageSize=" + pageSize + ", order=" + order + ", keyWords=" + keyWords
				+ ", fuzzy=" + fuzzy + "]";
	}
}
