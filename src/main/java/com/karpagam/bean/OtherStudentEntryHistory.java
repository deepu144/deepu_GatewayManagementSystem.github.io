package com.karpagam.bean;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OtherStudentEntryHistory {
	@Id
	private UUID id;
	private String rollNumber;
	private String name;
	private Time inTime;
	private Time outTime;
	private Date inDate;
	private Date outDate;
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

	public Time getInTime() {
		return inTime;
	}

	public void setInTime(Time inTime) {
		this.inTime = inTime;
	}

	public Time getOutTime() {
		return outTime;
	}

	public void setOutTime(Time outTime) {
		this.outTime = outTime;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public boolean isEntered() {
		return isEntered;
	}

	public void setEntered(boolean isEntered) {
		this.isEntered = isEntered;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "OtherStudentEntryHistory [id=" + id + ", rollNumber=" + rollNumber + ", name=" + name + ", inTime="
				+ inTime + ", outTime=" + outTime + ", inDate=" + inDate + ", outDate=" + outDate + ", isEntered="
				+ isEntered + "]";
	}

}
