package vn.com.unit.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.unit.entity.Log;
import vn.com.unit.repository.LogRepository;
import vn.com.unit.service.LogService;

@Service
@Transactional
public class LogServiceImpl implements LogService {

	@Autowired
	LogRepository logRepository;

	@Override
	public void saveLog(String log, String type, String author) {
		logRepository.saveLog(log, type, author);

		Log log_temp = new Log();
		log_temp.setLog(log);
		log_temp.setType(type);
		log_temp.setAuthor(author);
		log_temp.setCreateAt(new Date());

		logRepository.save(log_temp);
	}

	@Override
	public List<Log> findAllLog(int limit, int offset) {
		// TODO Auto-generated method stub
		try {
			return logRepository.findAllLog(limit, offset);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public int countAllLog() {
		// TODO Auto-generated method stub
		try {
			return logRepository.CountAllLog();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

}
