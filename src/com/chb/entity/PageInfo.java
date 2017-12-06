package com.chb.entity;

import java.util.List;

public class PageInfo<T> {
	/**
	 * 总记录数
	 */
	private long total;
	/**
	 * 第几页
	 */
	private Integer pageNum;
	/**
	 * 每页记录数
	 */
	private Integer pageSize;
	/**
	 * 总页数
	 */
	private Integer pages;
	/**
	 * 当前页的记录数
	 */
	private Integer size;
	/**
	 * 结果集
	 */
	private List<T> list;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
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

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public PageInfo(Page page,long total,List<T> list) {
		this.total = total;
		this.pageNum = page.getPageNum();
		this.pageSize = page.getPageSize();
		this.pages = (int) ((total + pageSize - 1) / pageSize);
		this.size = list.size();
		this.list = list;
	}


	@Override
	public String toString() {
		return "PageInfo [total=" + total + ", pageNum=" + pageNum + ", pageSize=" + pageSize + ", pages=" + pages
				+ ", size=" + size + ", list=" + list + "]";
	}

}
