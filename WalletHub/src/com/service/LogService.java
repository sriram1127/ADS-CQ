package com.service;

import java.util.List;

import com.hib.entities.Agent;
import com.hib.entities.Log;
import com.hib.entities.Request;
import com.hib.entities.Status;

public interface LogService {

	public void insert(List<Log> log);

	public Integer insertStatus(Status status);

	public Integer insertAgent(Agent agent);

	public Integer insertRequest(Request request);
}
