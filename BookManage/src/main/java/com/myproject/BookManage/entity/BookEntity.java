package com.myproject.BookManage.entity;

import java.util.Date;

/**
 * 书本的实体类
 * @author clc
 *
 */
public class BookEntity extends BaseEntity{
	
	private Integer bid;
	private String title;
	private String type;
	private String author;
	private String press;
	private Integer isBroken;
	private Integer isBorrowed;
	private Date borrowedTime;
	private Date returnTime;
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public Integer getIsBroken() {
		return isBroken;
	}
	public void setIsBroken(Integer isBroken) {
		this.isBroken = isBroken;
	}
	public Integer getIsBorrowed() {
		return isBorrowed;
	}
	public void setIsBorrowed(Integer isBorrowed) {
		this.isBorrowed = isBorrowed;
	}
	public Date getBorrowedTime() {
		return borrowedTime;
	}
	public void setBorrowedTime(Date borrowedTime) {
		this.borrowedTime = borrowedTime;
	}
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	@Override
	public String toString() {
		return "BookEntity [bid=" + bid + ", title=" + title + ", type=" + type + ", author=" + author + ", press="
				+ press + ", isBroken=" + isBroken + ", isBorrowed=" + isBorrowed + ", borrowedTime=" + borrowedTime
				+ ", returnTime=" + returnTime + ", toString()=" + super.toString() + "]";
	}
	
	
}
