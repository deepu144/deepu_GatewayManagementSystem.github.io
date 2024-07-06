package com.karpagam.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.karpagam.bean.AdminTable;
import com.karpagam.bean.EntryDetail;
import com.karpagam.bean.OtherStudentEntry;
import com.karpagam.bean.OtherStudentEntryHistory;
import com.karpagam.bean.StaffEntryDetail;
import com.karpagam.bean.StaffHistory;
import com.karpagam.bean.StaffInformation;
import com.karpagam.bean.StudentHistory;
import com.karpagam.bean.StudentInformation;

public class DBUtil {

	public static SessionFactory getSessionFactory() {
//		Configuration con = new Configuration().configure().addAnnotatedClass(OtherStudentEntryHistory.class);
		Configuration con = new Configuration().configure().addAnnotatedClass(AdminTable.class)
				.addAnnotatedClass(StudentInformation.class).addAnnotatedClass(EntryDetail.class)
				.addAnnotatedClass(StudentHistory.class).addAnnotatedClass(StaffEntryDetail.class)
				.addAnnotatedClass(StaffHistory.class).addAnnotatedClass(StaffInformation.class)
				.addAnnotatedClass(OtherStudentEntry.class).addAnnotatedClass(OtherStudentEntryHistory.class);
		return con.buildSessionFactory();
	}
}
