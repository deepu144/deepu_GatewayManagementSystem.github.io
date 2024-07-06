package com.karpagam.service;

import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import com.karpagam.bean.AdminTable;
import com.karpagam.bean.EntryDetail;
import com.karpagam.bean.OtherStudentEntry;
import com.karpagam.bean.StaffEntryDetail;
import com.karpagam.bean.StaffInformation;
import com.karpagam.bean.StudentInformation;
import com.karpagam.dao.AdminTableDao;
import com.karpagam.dao.EntryDetailDao;
import com.karpagam.dao.OtherStudentEntryDao;
import com.karpagam.dao.OtherStudentEntryHistoryDao;
import com.karpagam.dao.StaffEntryDetailDao;
import com.karpagam.dao.StaffHistoryDao;
import com.karpagam.dao.StaffInformationDao;
import com.karpagam.dao.StudentHistoryDao;
import com.karpagam.dao.StudentInformationDao;

public class Administator {
	public static AdminTableDao adminTableDao = new AdminTableDao();
	public static EntryDetailDao entryDetailDao = new EntryDetailDao();
	public static StudentInformationDao studentInformationDao = new StudentInformationDao();
	public static StudentHistoryDao studentHistoryDao = new StudentHistoryDao();
	public static StaffEntryDetailDao staffEntryDetailDao = new StaffEntryDetailDao();
	public static StaffHistoryDao staffHistoryDao = new StaffHistoryDao();
	public static StaffInformationDao staffInformation = new StaffInformationDao();
	public static StaffInformationDao staffInformationDao = new StaffInformationDao();
	public static OtherStudentEntryDao otherStudentEntryDao = new OtherStudentEntryDao();
	public static OtherStudentEntryHistoryDao otherStudentEntryHistoryDao = new OtherStudentEntryHistoryDao();
	
	public boolean uploadStudentInformationDB(InputStream fileContent) {
		return studentInformationDao.uploadStudentInformationDB(fileContent);
	}
	
	public boolean uploadStaffInformationDB(InputStream fileContent) {
		return staffInformationDao.uploadStaffInformationDB(fileContent);
	}
	
	public boolean isValidAdminLogin(AdminTable user) {
		if (user.getPassWord().equals(adminTableDao.getAdminPassword(user.getUserName()))) {
			return true;
		}
		return false;
	}

	public StudentInformation getStudentInformation(String rollNumber) {
		return studentInformationDao.getInformation(rollNumber);
	}

	public boolean whetherStudentEntered(String rollNumber) {
		if(studentHistoryDao.checkEntered(rollNumber)) {
			return true;
		}
		if(entryDetailDao.checkEntered(rollNumber)) {
			return true;
		}
		return false;
	}

	public boolean insertNewStudentEntry(String rollNumber, String name) {

		Date date = new Date(Calendar.getInstance().getTime().getTime());
		Time time = new Time(Calendar.getInstance().getTime().getTime());
		EntryDetail entryDetail = new EntryDetail();
		entryDetail.setRollNumber(rollNumber);
		entryDetail.setName(name);
		entryDetail.setInDate(date);
		entryDetail.setInTime(time);
		entryDetail.setOutDate(null);
		entryDetail.setOutTime(null);
		entryDetail.setEntered(true);
		if (entryDetailDao.insert(entryDetail)) {
			return true;
		}
		return false;
	}

	public boolean insertNewStaffEntry(String rollNumber,String name) {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		Time time = new Time(Calendar.getInstance().getTime().getTime());
		StaffEntryDetail entryDetail = new StaffEntryDetail();
		entryDetail.setRollNumber(rollNumber);
		entryDetail.setName(name);
		entryDetail.setInDate(date);
		entryDetail.setInTime(time);
		entryDetail.setOutDate(null);
		entryDetail.setOutTime(null);
		entryDetail.setEntered(true);
		if (staffEntryDetailDao.insert(entryDetail)) {
			return true;
		}
		return false;
	}
	
	
	public void UpdateOldStudentEntry(String rollNumber) {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		Time time = new Time(Calendar.getInstance().getTime().getTime());
		if(studentHistoryDao.checkEntered(rollNumber)) {
			studentHistoryDao.update(rollNumber,date,time);
		}else {
			entryDetailDao.update(rollNumber, date, time);			
		}
	}
	
	public void updateOldStaffEntry(String rollNumber) {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		Time time = new Time(Calendar.getInstance().getTime().getTime());
		if(staffHistoryDao.checkStaffEntered(rollNumber)) {
			staffHistoryDao.update(rollNumber,date,time);
		}else {
			staffEntryDetailDao.update(rollNumber, date, time);			
		}
	}
	

	public List<EntryDetail> getAllRecords() {
		return entryDetailDao.getRecords();
	}

	public List<Object> getTodayStudentDetailInFilter(Date date ,boolean inDate,Time fromTime, Time toTime) {
		return entryDetailDao.getTodayStudentDetailInFilter(date, inDate, fromTime, toTime);
	}

	public List<Object> getTodayStudentDetailInFilter(Date date ,boolean inDate , Time fromTime, Time toTime,String rollNumber) {
		return entryDetailDao.getTodayStudentDetailInFilter(date, inDate, fromTime, toTime,rollNumber);
	}
	
	public List<Object> getTodayStaffDetailInFilter(Date date,boolean inDate,Time fromTime, Time toTime) {
		return staffEntryDetailDao.getTodayStaffDetailInFilter(date,inDate,fromTime,toTime);
	}

	public List<Object> getTodayStaffDetailInFilter(Date date, boolean inDate, Time fromTime, Time toTime,String rollNumber) {
		return staffEntryDetailDao.getTodayStaffDetailInFilter(date,inDate,fromTime,toTime,rollNumber);
	}
	
	public List<Object> getStudentHistoryDetailInFilter(Date date,boolean inDate, Time fromTime, Time toTime){
		return studentHistoryDao.getStudentHistoryDetailInFilter(date,inDate,fromTime,toTime);
	}
	
	public List<Object> getStudentHistoryDetailInFilter(Date date,boolean inDate, String category, Time fromTime, Time toTime ,String rollNumber){
		return studentHistoryDao.getStudentHistoryDetailInFilter(date,inDate,fromTime,toTime,rollNumber);
	}
	
	public List<Object> getStaffHistoryDetailInFilter(Date date,boolean inDate, String category, Time fromTime, Time toTime){
		return staffHistoryDao.getStaffHistoryDetailInFilter(date,inDate, fromTime, toTime);
	}
	
	public List<Object> getStaffHistoryDetailInFilter(Date date,boolean inDate, String category, Time fromTime, Time toTime,String rollNumber){
		return staffHistoryDao.getStaffHistoryDetailInFilter(date,inDate, fromTime, toTime,rollNumber);
	}
	
	public Long getTodayStudentCount() {
		return entryDetailDao.getTodayStudentCount();
	}
	
	public Long getTodayStaffCount() {
		return staffEntryDetailDao.getTodayStaffCount();
	}
	
	public boolean addAdmin(AdminTable adminTable) {
		return adminTableDao.addAdmin(adminTable);
	}
	
	public boolean changePassword(AdminTable adminTable) {
		return adminTableDao.changePassword(adminTable);
	}
	
	public StaffInformation getStaffInformation(String rollNumber) {
		return staffInformation.getStaffInformation(rollNumber);
	}
	
	public boolean whetherStaffEntered(String rollNumber) {
		if(staffHistoryDao.checkStaffEntered(rollNumber)) {
			return true;
		}
		if(staffEntryDetailDao.checkStaffEntered(rollNumber)) {
			return true;
		}
		return false;
	}
	
	public Long getInCount() {
		return entryDetailDao.getInCount() + staffEntryDetailDao.getInCount() + otherStudentEntryDao.getInCount();
	}
	
	public Long getOutCount() {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		return entryDetailDao.getOutCount() + staffEntryDetailDao.getOutCount()+otherStudentEntryDao.getOutCount()+otherStudentEntryHistoryDao.getOutCount(date)+studentHistoryDao.getOutCount(date)+staffHistoryDao.getOutCount(date);
	}
	
	public boolean shiftStudentToHistoryRecords() {
		if(entryDetailDao.shiftAllRecords()) {
			if(otherStudentEntryDao.shiftAllRecords()) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	
	public void truncateStudentEntryTable() {
		 entryDetailDao.truncateTable();
		 otherStudentEntryDao.truncateTable();
	}
	
	public boolean shiftStaffToHistoryRecords() {
		 return staffEntryDetailDao.shiftAllRecords();
	}
	
	public void truncateStaffEntryTable() {
		 staffEntryDetailDao.truncateTable();
	}
	
	public boolean whetherOtherStudentEntered(String rollNumber) {
		if(otherStudentEntryHistoryDao.checkEntered(rollNumber)) {
			return true;
		}
		if(otherStudentEntryDao.checkEntered(rollNumber)) {
			return true;
		}
		return false;
	}
	
	public void UpdateOldOtherStudentEntry(String rollNumber) {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		Time time = new Time(Calendar.getInstance().getTime().getTime());
		if(otherStudentEntryHistoryDao.checkEntered(rollNumber)) {
			otherStudentEntryHistoryDao.update(rollNumber,date,time);
		}else {
			otherStudentEntryDao.update(rollNumber, date, time);			
		}
	}
	
	public boolean insertNewOtherStudentEntry(String rollNumber) {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		Time time = new Time(Calendar.getInstance().getTime().getTime());
		OtherStudentEntry entryDetail = new OtherStudentEntry();
		entryDetail.setRollNumber(rollNumber);
		entryDetail.setName(null);
		entryDetail.setInDate(null);
		entryDetail.setInTime(null);
		entryDetail.setOutDate(date);
		entryDetail.setOutTime(time);
		entryDetail.setEntered(false);

		if (otherStudentEntryDao.insertNewOtherStudentEntry(entryDetail)) {
			return true;
		}
		return false;
	}

	public List<Object> getTodayOtherStudentDetailInFilter(Date date, Boolean inDate, Time fromTime, Time toTime) {
		
		return otherStudentEntryDao.getTodayOtherStudentDetailInFilter( date,  inDate,  fromTime,  toTime);
	}

	public List<Object> getTodayOtherStudentDetailInFilter(Date date, Boolean inDate, Time fromTime, Time toTime,
			String rollNumber) {
		
		return otherStudentEntryDao.getTodayOtherStudentDetailInFilter( date,  inDate,  fromTime,  toTime , rollNumber);
	}

	public List<Object> getOtherStudentHistoryDetailInFilter(Date date, Boolean inDate, Time fromTime, Time toTime) {
		
		return otherStudentEntryHistoryDao.getTodayOtherStudentDetailInFilter( date,  inDate,  fromTime,  toTime);
	}

	public List<Object> getOtherStudentHistoryDetailInFilter(Date date, Boolean inDate, String category, Time fromTime,
			Time toTime, String rollNumber) {
		
		return otherStudentEntryHistoryDao.getTodayOtherStudentDetailInFilter( date,  inDate,  fromTime,  toTime,rollNumber);
	}
	
}
