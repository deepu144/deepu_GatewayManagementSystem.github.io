package com.karpagam.bean;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StudentHistory {
	@Id
    private UUID id; 
	private String rollNumber;
	private String name;
	private Date inDate;
	private Time inTime;
	private Date outDate;
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	private Time outTime;
	private boolean isEntered;

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
		return "EntryDetail [rollNumber=" + rollNumber + ", name=" + name + ", inDate=" + inDate + ", inTime=" + inTime
				+ ", outDate=" + outDate + ", outTime=" + outTime + ", isEntered=" + isEntered + "]";
	}

}

