package com.ef;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hib.entities.Agent;
import com.hib.entities.Log;
import com.hib.entities.Request;
import com.hib.entities.Status;
import com.service.LogService;
import com.service.LogServiceImpl;

public class FileParser {

	public void parseFile(String path) {
		String[] values = null;
		Map<Integer, Integer> statusMap = new HashMap<Integer, Integer>();
		Map<String, Integer> requestMap = new HashMap<String, Integer>();
		Map<String, Integer> agentMap = new HashMap<String, Integer>();
		Log log = null;
		Integer statusCode = null;
		String requestName = null;
		String agentName = null;
		String iP = null;
		Integer requestId = null;
		Integer agentId = null;
		Integer statusId = null;
		LogService ls = new LogServiceImpl();
		List<Log> logs = new ArrayList<Log>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Date parsedDate = null;
		Timestamp timestamp = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			String str;
			int i = 1;
			while ((str = in.readLine()) != null) {
				values = str.split("\\|");

				try {
					parsedDate = (Date) dateFormat.parse(values[0]);
					timestamp = new java.sql.Timestamp(parsedDate.getTime());
					iP = values[1];
				} catch (Exception e) {
					System.out.println(e);
				}
				requestName = values[2];
				requestName = requestName != null && requestName.length() > 2
						? requestName.substring(1, requestName.length() - 1)
						: requestName;
				if (requestMap.get(requestName) == null) {
					requestId = ls.insertRequest(new Request(requestName));
					requestMap.put(requestName, requestId);
				} else {
					requestId = requestMap.get(requestName);
				}
				statusCode = Integer.parseInt(values[3]);
				if (statusMap.get(statusCode) == null) {
					statusId = ls.insertStatus(new Status(statusCode));
					statusMap.put(statusCode, statusId);
				} else {
					statusId = statusMap.get(statusCode);
				}
				agentName = values[4];
				agentName = agentName != null && agentName.length() > 2 ? agentName.substring(1, agentName.length() - 1)
						: agentName;
				if (agentMap.get(agentName) == null) {
					agentId = ls.insertAgent(new Agent(agentName));
					agentMap.put(agentName, agentId);
				} else {
					agentId = agentMap.get(agentName);
				}
				log = new Log(iP, timestamp, requestId, statusId, agentId);
				logs.add(log);
				if (i % 1000 == 0) {
					System.out.println("Please Wait...");
					ls.insert(logs);
					logs.clear();
				}
				++i;
			}
			if (logs.size() > 0) {
				ls.insert(logs);
				logs.clear();
			}
			in.close();

		} catch (IOException e) {
			System.out.println("File Read Error");
		}

	}
}
