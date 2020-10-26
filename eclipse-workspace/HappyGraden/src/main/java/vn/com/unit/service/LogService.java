package vn.com.unit.service;

import java.util.List;

import vn.com.unit.entity.Log;

public interface LogService {

	public void saveLog(String log, String type, String author);
	
	public List<Log> findAllLog(int limit,int offset);
	
	public int countAllLog();
}
