package com.karpagam.bean;
import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.karpagam.util.DBUtil;

public class Demo {
	
	public static void main(String[] args) {
		try {
//			insert();
			create();
		} catch (Exception e) {
			System.out.println("NOT Inserted");
			System.out.println(e);
		}
	}
	
	public static void create() {
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.getTransaction().commit();
		session.close();
		sf.close();
	}
	
	public static void insert() throws IOException {
		
//		File f = new File("C:\\ALL CODINGS\\JAVA_ECLIPSE_WORKSPACE\\GateWay_Management_System\\src\\main\\webapp\\images\\deepu.jpg");
//		byte[] imageData = new byte[(int)f.length()];
//		FileInputStream fis = new FileInputStream(f);
//		fis.read(imageData);
//		fis.close();
		
		
		
//		StudentInformation s1 = new StudentInformation();
//		s1.setRollNumber("717822F110");
//		s1.setName("Deepak S");
//		s1.setYear(2);
//		s1.setDept("IT");
//		s1.setImage(null);
		
//		StaffInformation s1 = new StaffInformation();
//		s1.setRollNumber("C0178");
//		s1.setName("Arul Antran Vijay S");
//		s1.setDept("CSE");
		
//		EntryLogin s1 = new EntryLogin();
//		s1.setUserName("karpagam");
//		s1.setPassWord("karpagam");
		
//		AdminTable adminTable = new AdminTable();
//		adminTable.setUserName("admin");
//		adminTable.setPassWord("admin");
		
//		SessionFactory sf = DBUtil.getSessionFactory();
//		Session session = sf.openSession();
//		session.beginTransaction();
//		session.persist(s1);
//		session.getTransaction().commit();
//		session.close();
//		sf.close();
	}
}
