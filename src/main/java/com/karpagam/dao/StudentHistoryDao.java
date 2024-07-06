package com.karpagam.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.karpagam.bean.StudentHistory;
import com.karpagam.util.DBUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class StudentHistoryDao {
	
	public List<Object> getStudentHistoryDetailInFilter(Date date,Boolean inDate, Time fromTime, Time toTime){
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery(Object.class);
		Root<StudentHistory> root = cq.from(StudentHistory.class);
		
		Predicate p7 = cb.equal(root.get("inDate"), date);
		Predicate p1 = cb.greaterThanOrEqualTo(root.get("inTime"), fromTime);
		Predicate p2 = cb.isNull(root.get("outTime"));
		Predicate p3 = cb.lessThanOrEqualTo(root.get("outTime"), toTime);
		Predicate p4 = cb.or(p2,p3);
		Predicate p6 = null;
		if(inDate) {
			p6 = cb.equal(root.get("inDate"), date);
		}else {
			p6 = cb.equal(root.get("outDate"), date);
		}
		Predicate p5 = cb.and(p7,p1,p4,p6);
		cq.select(root).where(p5);
		
		List<Object> li = session.createQuery(cq).getResultList();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return li;
	}
	
	public List<Object> getStudentHistoryDetailInFilter(Date date,boolean inDate, Time fromTime, Time toTime, String rollNumber){
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery(Object.class);
		Root<StudentHistory> root = cq.from(StudentHistory.class);
		Predicate p7 = cb.equal(root.get("inDate"), date);
		Predicate p1 = cb.greaterThanOrEqualTo(root.get("inTime"), fromTime);
		Predicate p2 = cb.isNull(root.get("outTime"));
		Predicate p3 = cb.lessThanOrEqualTo(root.get("outTime"), toTime);
		Predicate p4 = cb.or(p2,p3);
		Predicate p6 = null;
		if(inDate) {
			p6 = cb.equal(root.get("inDate"), date);
		}else {
			p6 = cb.equal(root.get("outDate"), date);
		}
		Predicate p8 = cb.equal(root.get("rollNumber"), rollNumber);
		Predicate p5 = cb.and(p7,p8,p1,p4,p6);
		cq.select(root).where(p5);
		
		List<Object> li = session.createQuery(cq).getResultList();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return li;
	}
	
	public boolean checkEntered(String rollNumber) {

		StudentHistory entryDetail = null;
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<StudentHistory> cq = cb.createQuery(StudentHistory.class);
		Root<StudentHistory> root = cq.from(StudentHistory.class);
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
			StudentHistory entryDetail = null;
			SessionFactory sf = DBUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<StudentHistory> cq = cb.createQuery(StudentHistory.class);
			Root<StudentHistory> root = cq.from(StudentHistory.class);

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
		Root<StudentHistory> root = cq.from(StudentHistory.class);
		
		Predicate p1 = cb.equal(root.get("outDate"),date);
		cq.select(cb.count(root)).where(p1);
		
		Long count = (Long) session.createQuery(cq).getSingleResult();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return count;
	}
	
}
