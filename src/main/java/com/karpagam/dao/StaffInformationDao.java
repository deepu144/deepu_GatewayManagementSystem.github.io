package com.karpagam.dao;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.karpagam.bean.StaffInformation;
import com.karpagam.util.DBUtil;
import com.opencsv.CSVReader;

public class StaffInformationDao {
	
	public StaffInformation getStaffInformation(String rollNumber) {
		StaffInformation sInformation = null ;
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		sInformation = session.get(StaffInformation.class, rollNumber);
		
		session.getTransaction().commit();
		session.close();
		sf.close();
		if(sInformation!=null) {
			return sInformation;			
		}else {
			return null;
		}
	}
	
	public boolean uploadStaffInformationDB(InputStream fileContent) {
		try (CSVReader reader = new CSVReader(new InputStreamReader(fileContent))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String rollNumber = nextLine[0];
                String name = nextLine[1];
                String dept = nextLine[2];
               
                StaffInformation sInformation = new StaffInformation();
                sInformation.setRollNumber(rollNumber);
                sInformation.setName(name);
                sInformation.setDept(dept);
                sInformation.setImage(null);

                SessionFactory sf = DBUtil.getSessionFactory();
                Session session = sf.openSession();
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    session.persist(sInformation);
                    tx.commit();
                } catch (Exception e) {
                    if (tx != null) {
                    	tx.rollback();
                    }
                } finally {
                    session.close();
                    sf.close();
                }
            }
        } catch (Exception e) {
            return false;
        }
		return true;
	}
	
}
