package com.service;

import java.sql.Timestamp;
import java.util.List;

import com.entity.dao.ReportDao;
import com.entity.dao.ReportInterface;
import com.hib.entities.Report;

public class ReportServiceImpl implements ReportService {

	private ReportInterface ri = new ReportDao();

	@Override
	public List<String> check(Timestamp start, Timestamp end, int threshold) {
		// TODO Auto-generated method stub
		return ri.check(start, end, threshold);
	}

	@Override
	public void insertErrorMsg(List<Report> reports) {
		// TODO Auto-generated method stub
		ri.insertErrorMsg(reports);

	}

}
