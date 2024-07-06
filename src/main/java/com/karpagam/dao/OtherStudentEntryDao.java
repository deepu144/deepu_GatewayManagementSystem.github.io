package com.karpagam.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.karpagam.bean.OtherStudentEntry;
import com.karpagam.bean.OtherStudentEntryHistory;
import com.karpagam.util.DBUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class OtherStudentEntryDao {
	
	public boolean checkEntered(String rollNumber) {
		OtherStudentEntry entryDetail = null;
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<OtherStudentEntry> cq = cb.createQuery(OtherStudentEntry.class);
		Root<OtherStudentEntry> root = cq.from(OtherStudentEntry.class);
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
			OtherStudentEntry entryDetail = null;
			SessionFactory sf = DBUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<OtherStudentEntry> cq = cb.createQuery(OtherStudentEntry.class);
			Root<OtherStudentEntry> root = cq.from(OtherStudentEntry.class);

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

	public boolean insertNewOtherStudentEntry(OtherStudentEntry entryDetail) {
		try {
			SessionFactory sf = DBUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();

			session.persist(entryDetail);

			session.getTransaction().commit();
			session.close();
			sf.close();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public List<Object> getTodayOtherStudentDetailInFilter(Date date, Boolean inDate, Time fromTime, Time toTime) {
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery(Object.class);
		Root<OtherStudentEntry> root = cq.from(OtherStudentEntry.class);
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
		Root<OtherStudentEntry> root = cq.from(OtherStudentEntry.class);
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

	public boolean shiftAllRecords() {
		try {
			SessionFactory sf = DBUtil.getSessionFactory();
			Session session = sf.openSession();
			
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<OtherStudentEntry> cq = cb.createQuery(OtherStudentEntry.class);
			Root<OtherStudentEntry> root = cq.from(OtherStudentEntry.class);
			cq.select(root);
			List<OtherStudentEntry> li = session.createQuery(cq).getResultList();
			session.getTransaction().commit();
			session.close();
			
			Session session2 = sf.openSession();
			
			session2.beginTransaction();			
			for(OtherStudentEntry ed : li) {
				OtherStudentEntryHistory sh = new OtherStudentEntryHistory();
				sh.setRollNumber(ed.getRollNumber());
				sh.setName(ed.getName());
				sh.setEntered(ed.isEntered());
				sh.setInDate(ed.getInDate());
				sh.setOutDate(ed.getOutDate());
				sh.setInTime(ed.getInTime());
				sh.setOutTime(ed.getOutTime());
				sh.setId(ed.getId());
				session2.persist(sh);
			}
			
			session2.getTransaction().commit();
			session2.close();
			
			sf.close();
			
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public void truncateTable() {
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		
		session.beginTransaction();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<OtherStudentEntry> cq = cb.createQuery(OtherStudentEntry.class);
		Root<OtherStudentEntry> root = cq.from(OtherStudentEntry.class);
		cq.select(root);
		List<OtherStudentEntry> li = session.createQuery(cq).getResultList();
		session.getTransaction().commit();
		session.close();
		
		Session session2 = sf.openSession();
		
		session2.beginTransaction();			
		for(OtherStudentEntry ed : li) {
			session2.remove(ed);
		}
		session2.getTransaction().commit();
		session2.close();
		
		sf.close();
		
	}

	public Long getInCount() {
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery(Object.class);
		Root<OtherStudentEntry> root = cq.from(OtherStudentEntry.class);
		
		Predicate p1 = cb.isNotNull(root.get("inTime"));
		cq.select(cb.count(root)).where(p1);
		
		Long count = (Long) session.createQuery(cq).getSingleResult();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return count;
	}

	public Long getOutCount() {
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery(Object.class);
		Root<OtherStudentEntry> root = cq.from(OtherStudentEntry.class);
		
		Predicate p1 = cb.isNotNull(root.get("outTime"));
		cq.select(cb.count(root)).where(p1);
		
		Long count = (Long) session.createQuery(cq).getSingleResult();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return count;
	}
}
