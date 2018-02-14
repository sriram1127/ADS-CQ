package com.service;

import java.util.List;

import com.entity.dao.LogDao;
import com.entity.dao.LogInterface;
import com.hib.entities.Agent;
import com.hib.entities.Log;
import com.hib.entities.Request;
import com.hib.entities.Status;

public class LogServiceImpl implements LogService {

	LogInterface li = new LogDao();

	@Override
	public void insert(List<Log> log) {
		// TODO Auto-generated method stub
		li.insert(log);
	}

	@Override
	public Integer insertStatus(Status status) {
		// TODO Auto-generated method stub
		return li.insertStatus(status);
	}

	@Override
	public Integer insertAgent(Agent agent) {
		// TODO Auto-generated method stub
		return li.insertAgent(agent);
	}

	@Override
	public Integer insertRequest(Request request) {
		// TODO Auto-generated method stub
		return li.insertRequest(request);
	}

}
