package com.ef;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.hib.entities.Report;
import com.service.ReportService;
import com.service.ReportServiceImpl;

public class IPChecker {
	private ReportService rs = new ReportServiceImpl();

	@SuppressWarnings("null")
	public void checkIP(String startDate, String rate, String threshold) {
		List<String> ips = null;
		Date startTime = null;
		Date endTime = new Date();
		Timestamp start = null;
		Timestamp end = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd.hh:mm:ss");
		try {
			startTime = (Date) dateFormat.parse(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if ("hourly".equals(rate)) {
			endTime.setTime(startTime.getTime() + TimeUnit.HOURS.toMillis(1));
		} else {
			endTime.setTime(startTime.getTime() + TimeUnit.DAYS.toMillis(1));
		}
		start = new java.sql.Timestamp(startTime.getTime());
		end = new java.sql.Timestamp(endTime.getTime());
		ips = rs.check(start, end, Integer.parseInt(threshold));
		if (ips != null && ips.size() != 0) {
			System.out.println("OUTPUT");
			System.out.println("------------------------------------------------------------");
			List<Report> reports = new ArrayList<Report>();
			Report report = null;
			for (String ip : ips) {
				report = new Report();
				report.setIp(ip);
				report.setStartTime(start);
				report.setEndTime(end);
				report.setThreshold(Integer.parseInt(threshold));
				report.setMessage(rate + " limit exceeded");
				report.setRequestedTime(new Timestamp(System.currentTimeMillis()));
				reports.add(report);
				System.out.println(ip);
			}
			rs.insertErrorMsg(reports);
		} else {
			System.out.println("No ip is found");
		}
		System.exit(0);
	}

}
