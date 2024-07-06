package com.karpagam.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.karpagam.bean.OtherStudentEntryHistory;
import com.karpagam.util.DBUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class OtherStudentEntryHistoryDao {
	
	public boolean checkEntered(String rollNumber) {
		OtherStudentEntryHistory entryDetail = null;
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<OtherStudentEntryHistory> cq = cb.createQuery(OtherStudentEntryHistory.class);
		Root<OtherStudentEntryHistory> root = cq.from(OtherStudentEntryHistory.class);
		Predicate p1 = cb.equal(root.get("rollNumber"), rollNumber);
		Predicate p2 = cb.equal(root.get("isEntered"), false);
		Predicate p3 = cb.and(p1, p2);
		cq.select(root).where(p3);

		entryDetail = session.createQuery(cq).getSingleResultOrNull();

		session.close();
		sf.close();
		if (entryDetail != null) {
			return true;
		}
		return false;
	}

	public void update(String rollNumber, Date date, Time time) {
		try {
			OtherStudentEntryHistory entryDetail = null;
			SessionFactory sf = DBUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<OtherStudentEntryHistory> cq = cb.createQuery(OtherStudentEntryHistory.class);
			Root<OtherStudentEntryHistory> root = cq.from(OtherStudentEntryHistory.class);

			Predicate p1 = cb.equal(root.get("rollNumber"), rollNumber);
			Predicate p2 = cb.equal(root.get("isEntered"), false);
			Predicate p3 = cb.isNull(root.get("inDate"));
			Predicate p4 = cb.and(p1, p2, p3);
			cq.select(root).where(p4);

			entryDetail = session.createQuery(cq).getSingleResult();
			entryDetail.setInDate(date);
			entryDetail.setInTime(time);
			entryDetail.setEntered(true);
			session.merge(entryDetail);

			session.getTransaction().commit();
			session.close();
			sf.close();

		} catch (Exception e) {
		}
		
	}

	public List<Object> getTodayOtherStudentDetailInFilter(Date date, Boolean inDate, Time fromTime, Time toTime) {
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery(Object.class);
		Root<OtherStudentEntryHistory> root = cq.from(OtherStudentEntryHistory.class);
		Predicate p2 = cb.lessThanOrEqualTo(root.get("outTime"), toTime);
		Predicate p3 = cb.isNull(root.get("inTime"));
		Predicate p4 = cb.greaterThanOrEqualTo(root.get("inTime"), fromTime);
		Predicate p5 = cb.or(p3,p4);
		Predicate p7 = null;
		if(inDate) {
			p7 = cb.equal(root.get("inDate"), date);
		}else {
			p7 = cb.equal(root.get("outDate"), date);
		}
		Predicate p6 = cb.and(p2,p5,p7);
		
		cq.select(root).where(p6);
		List<Object> li = session.createQuery(cq).getResultList();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return li;
	}

	public List<Object> getTodayOtherStudentDetailInFilter(Date date, Boolean inDate, Time fromTime, Time toTime,
			String rollNumber) {
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery(Object.class);
		Root<OtherStudentEntryHistory> root = cq.from(OtherStudentEntryHistory.class);
		Predicate p7 = cb.equal(root.get("rollNumber"), rollNumber);
		Predicate p2 = cb.lessThanOrEqualTo(root.get("outTime"), toTime);
		Predicate p3 = cb.isNull(root.get("inTime"));
		Predicate p4 = cb.greaterThanOrEqualTo(root.get("inTime"), fromTime);
		Predicate p5 = cb.or(p3,p4);
		Predicate p8 = null;
		if(inDate) {
			p8 = cb.equal(root.get("inDate"), date);
		}else {
			p8 = cb.equal(root.get("outDate"), date);
		}
		Predicate p6 = cb.and(p7,p2,p5,p8);
		
		cq.select(root).where(p6);
		List<Object> li = session.createQuery(cq).getResultList();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return li;
	}

	public Long getOutCount(Date date) {
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery(Object.class);
		Root<OtherStudentEntryHistory> root = cq.from(OtherStudentEntryHistory.class);
		
		Predicate p1 = cb.equal(root.get("outDate"),date);
		cq.select(cb.count(root)).where(p1);
		
		Long count = (Long) session.createQuery(cq).getSingleResult();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return count;
	}
	
}
