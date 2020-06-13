package com.myproject.BookManage.VO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookEntityVO implements Serializable {
	
	private static final long serialVersionUID = -8491715916536298753L;
	
	private Integer uid;
	private Integer bid;
	private String title;
	private String author;
	private String type;
	private String press;
	private Date borrowedTime;
	private Date returnTime;
	
	public String getReturnTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long borrow = borrowedTime.getTime();
		System.err.println("borrow:" + borrow);
		long returntime = borrow + 2678400000l;
		System.err.println("return:" + returntime);
		System.err.println("!!!!!!" + (returntime - borrow));
		Date date = new Date(returntime);
		return sdf.format(date);
	}
	
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	
	public String getBorrowedTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(borrowedTime);
	}
	public void setBorrowedTime(Date borrowedTime) {
		this.borrowedTime = new Date();
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	
	
	@Override
	public String toString() {
		return "BookEntityVO [uid=" + uid + ", bid=" + bid + ", title=" + title + ", author=" + author + ", type="
				+ type + ", press=" + press + ", borrowedTime=" + borrowedTime + ", returnTime=" + returnTime + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bid == null) ? 0 : bid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookEntityVO other = (BookEntityVO) obj;
		if (bid == null) {
			if (other.bid != null)
				return false;
		} else if (!bid.equals(other.bid))
			return false;
		return true;
	}
	
	
	
}
