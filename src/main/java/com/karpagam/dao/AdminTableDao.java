package com.karpagam.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.karpagam.bean.AdminTable;
import com.karpagam.util.DBUtil;

public class AdminTableDao {
	
	public String getAdminPassword(String userName) {
		AdminTable user = null;
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		user = session.get(AdminTable.class , userName);
		session.getTransaction().commit();
		session.close();
		sf.close();
		if(user==null) {
			return "";
		}
		return user.getPassWord();
	}
	
	public boolean addAdmin(AdminTable adminTable) {
		try {
			SessionFactory sf = DBUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			session.persist(adminTable);
			session.getTransaction().commit();
			session.close();
			sf.close();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public boolean changePassword(AdminTable adminTable) {
		try {
			AdminTable updateAdmin = null;
			SessionFactory sf = DBUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			
			updateAdmin = session.get(AdminTable.class,adminTable.getUserName());
			updateAdmin.setPassWord(adminTable.getPassWord());
			
			session.getTransaction().commit();
			session.close();
			sf.close();
			return true;
		}catch(Exception e) {
			return false;
		}
	}

}
