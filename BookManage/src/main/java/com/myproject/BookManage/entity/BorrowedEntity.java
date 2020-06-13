package com.myproject.BookManage.entity;

import java.util.Date;

public class BorrowedEntity extends BaseEntity {

	private Integer tid;
	private Integer bid;
	private Date borrowedTime;
	private Date returnTime;
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
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
		return "BorrowedEntity [tid=" + tid + ", bid=" + bid + ", borrowedTime=" + borrowedTime + ", returnTime="
				+ returnTime + ", toString()=" + super.toString() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((tid == null) ? 0 : tid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BorrowedEntity other = (BorrowedEntity) obj;
		if (tid == null) {
			if (other.tid != null)
				return false;
		} else if (!tid.equals(other.tid))
			return false;
		return true;
	}
	
	
	
}
