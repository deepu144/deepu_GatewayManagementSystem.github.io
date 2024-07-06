package com.karpagam.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.karpagam.bean.StaffHistory;
import com.karpagam.util.DBUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class StaffHistoryDao {
	
	public List<Object> getStaffHistoryDetailInFilter(Date date,boolean inDate, Time fromTime, Time toTime){
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery(Object.class);
		Root<StaffHistory> root = cq.from(StaffHistory.class);
		Predicate p1 = cb.equal(root.get("inDate"),date);
		Predicate p2 = cb.greaterThanOrEqualTo(root.get("inTime"), fromTime);
		Predicate p3 = cb.isNull(root.get("outTime"));
		Predicate p4 = cb.lessThanOrEqualTo(root.get("outTime"), toTime);
		Predicate p5 = cb.or(p3,p4);
		Predicate p6 = null;
		if(inDate) {
			p6 = cb.equal(root.get("inDate"), date);
		}else {
			p6 = cb.equal(root.get("outDate"), date);
		}
		Predicate p7 = cb.and(p1,p6,p2,p5);
		
		cq.select(root).where(p7);
		
		List<Object> li = session.createQuery(cq).getResultList();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return li;
	}
	
	public List<Object> getStaffHistoryDetailInFilter(Date date,boolean inDate, Time fromTime, Time toTime, String rollNumber){
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery(Object.class);
		Root<StaffHistory> root = cq.from(StaffHistory.class);
		Predicate p1 = cb.equal(root.get("rollNumber"),rollNumber);
		Predicate p2 = cb.equal(root.get("inDate"),date);
		Predicate p3 = cb.greaterThanOrEqualTo(root.get("inTime"), fromTime);
		Predicate p4 = cb.isNull(root.get("outTime"));
		Predicate p5 = cb.lessThanOrEqualTo(root.get("outTime"), toTime);
		Predicate p6 = cb.or(p4,p5);
		Predicate p8 = null;
		if(inDate) {
			p8 = cb.equal(root.get("inDate"), date);
		}else {
			p8 = cb.equal(root.get("outDate"), date);
		}
		Predicate p7 = cb.and(p1,p8,p2,p3,p6);
		
		cq.select(root).where(p7);
		
		List<Object> li = session.createQuery(cq).getResultList();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return li;
	}
	
	public boolean checkStaffEntered(String rollNumber) {

		StaffHistory entryDetail = null;
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<StaffHistory> cq = cb.createQuery(StaffHistory.class);
		Root<StaffHistory> root = cq.from(StaffHistory.class);
		Predicate p1 = cb.equal(root.get("rollNumber"), rollNumber);
		Predicate p2 = cb.equal(root.get("isEntered"), true);
		Predicate p3 = cb.isNull(root.get("outDate"));
		Predicate p4 = cb.and(p1, p2, p3);
		cq.select(root).where(p4);

		entryDetail = session.createQuery(cq).getSingleResultOrNull();

		session.close();
		sf.close();
		if (entryDetail != null) {
			return true;
		}
		return false;
	}
	
	public boolean update(String rollNumber, Date date, Time time) {
		try {
			StaffHistory entryDetail = null;
			SessionFactory sf = DBUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<StaffHistory> cq = cb.createQuery(StaffHistory.class);
			Root<StaffHistory> root = cq.from(StaffHistory.class);

			Predicate p1 = cb.equal(root.get("rollNumber"), rollNumber);
			Predicate p2 = cb.equal(root.get("isEntered"), true);
			Predicate p3 = cb.isNull(root.get("outDate"));
			Predicate p4 = cb.and(p1, p2, p3);
			cq.select(root).where(p4);

			entryDetail = session.createQuery(cq).getSingleResult();
			entryDetail.setOutDate(date);
			entryDetail.setOutTime(time);
			entryDetail.setEntered(false);
			session.merge(entryDetail);

			session.getTransaction().commit();
			session.close();
			sf.close();

			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Long getOutCount(Date date) {
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery(Object.class);
		Root<StaffHistory> root = cq.from(StaffHistory.class);
		
		Predicate p1 = cb.equal(root.get("outDate"),date);
		cq.select(cb.count(root)).where(p1);
		
		Long count = (Long) session.createQuery(cq).getSingleResult();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return count;
	}
	
}
