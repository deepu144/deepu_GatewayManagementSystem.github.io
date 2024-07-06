package com.karpagam.bean;

import java.sql.Blob;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StudentInformation {

	@Id
	private String rollNumber;
	private String name;
	private String dept;
	private int year;
	private Blob image;

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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "StudentInformation [rollNumber=" + rollNumber + ", name=" + name + ", dept=" + dept + ", year=" + year
				+ ", image=" + image + "]";
	}

}
