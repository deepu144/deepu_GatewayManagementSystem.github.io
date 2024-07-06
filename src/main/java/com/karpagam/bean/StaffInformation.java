package com.karpagam.bean;

import java.sql.Blob;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StaffInformation {
	@Id
	private String rollNumber;
	private String name;
	private String dept;
	private Blob image;

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
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

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "StaffInformation [rollNumber=" + rollNumber + ", name=" + name + ", dept=" + dept + ", image="
				+ image + "]";
	}

}
