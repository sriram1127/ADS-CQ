package com.service;

import java.sql.Timestamp;
import java.util.List;

import com.hib.entities.Report;

public interface ReportService {
	
	public List<String> check(Timestamp start, Timestamp end, int threshold);
	
	public void insertErrorMsg(List<Report> reports);
}
