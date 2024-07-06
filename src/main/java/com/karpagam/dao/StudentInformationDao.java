package com.karpagam.dao;

import java.io.InputStream;
import java.util.Iterator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.karpagam.bean.StudentInformation;
import com.karpagam.util.DBUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StudentInformationDao {
	
	public StudentInformation getInformation(String rollNumber) {
		StudentInformation sInformation = null ;
		SessionFactory sf = DBUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		sInformation = session.get(StudentInformation.class, rollNumber);
		session.getTransaction().commit();
		session.close();
		sf.close();
		return sInformation;
	}
	
//	public boolean uploadStudentInformationDB(InputStream fileContent) {
//		try (CSVReader reader = new CSVReader(new InputStreamReader(fileContent))) {
//            String[] nextLine;
//            while ((nextLine = reader.readNext()) != null) {
//                String rollNumber = nextLine[0];
//                String name = nextLine[1];
//                String dept = nextLine[2];
//                int year = Integer.parseInt(nextLine[3]);
//               
//                StudentInformation sInformation = new StudentInformation();
//                sInformation.setRollNumber(rollNumber);
//                sInformation.setName(name);
//                sInformation.setDept(dept);
//                sInformation.setYear(year);
//                sInformation.setImage(null);
//
//                SessionFactory sf = DBUtil.getSessionFactory();
//                Session session = sf.openSession();
//                Transaction tx = null;
//                try {
//                	System.out.println("INSERTED A ROW" + sInformation);
//                    tx = session.beginTransaction();
//                    session.persist(sInformation);
//                    tx.commit();
//                } catch (Exception e) {
//                    if (tx != null) {
//                    	tx.rollback();
//                    }
//                    return false;
//                } finally {
//                    session.close();
//                    sf.close();
//                }
//            }
//        } catch (Exception e) {
//            return false;
//        }
//		return true;
//	}
	
	public boolean uploadStudentInformationDB(InputStream fileContent) {
		try (Workbook workbook = new XSSFWorkbook(fileContent)) {
	        Sheet sheet = workbook.getSheetAt(0);
	        Iterator<Row> rowIterator = sheet.iterator();

	        while (rowIterator.hasNext()) {
	            Row row = rowIterator.next();

	            String rollNumber = row.getCell(0).getStringCellValue();
	            String name = row.getCell(1).getStringCellValue();
	            String dept = row.getCell(2).getStringCellValue();
	            int year = (int) row.getCell(3).getNumericCellValue();

	            StudentInformation sInformation = new StudentInformation();
	            sInformation.setRollNumber(rollNumber);
	            sInformation.setName(name);
	            sInformation.setDept(dept);
	            sInformation.setYear(year);
	            sInformation.setImage(null);

	            SessionFactory sf = DBUtil.getSessionFactory();
	            Session session = sf.openSession();
	            Transaction tx = null;
	            try {
	                System.out.println("INSERTED A ROW" + sInformation);
	                tx = session.beginTransaction();
	                session.persist(sInformation);
	                tx.commit();
	            } catch (Exception e) {
	                if (tx != null) {
	                    tx.rollback();
	                }
	                return false;
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
