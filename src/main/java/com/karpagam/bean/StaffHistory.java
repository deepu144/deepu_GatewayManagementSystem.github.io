package com.karpagam.bean;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StaffHistory {
	@Id
	private UUID id;
	private String rollNumber;
	private String name;
	private Date inDate;
	private Time inTime;
	private Date outDate;
	private Time outTime;
	private boolean isEntered;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Time getInTime() {
		return inTime;
	}

	public void setInTime(Time inTime) {
		this.inTime = inTime;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public Time getOutTime() {
		return outTime;
	}

	public void setOutTime(Time outTime) {
		this.outTime = outTime;
	}

	public boolean isEntered() {
		return isEntered;
	}

	public void setEntered(boolean isEntered) {
		this.isEntered = isEntered;
	}

	@Override
	public String toString() {
		return "StaffHistory [id=" + id + ", rollNumber=" + rollNumber + ", name=" + name + ", inDate=" + inDate
				+ ", inTime=" + inTime + ", outDate=" + outDate + ", outTime=" + outTime + ", isEntered=" + isEntered
				+ "]";
	}

}
