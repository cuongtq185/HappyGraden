package vn.com.unit.repository;

import java.util.List;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.mirage.repository.query.Modifying;
import org.springframework.data.repository.query.Param;

import vn.com.unit.entity.Log;

public interface LogRepository extends MirageRepository<Log, Long> {

	@Modifying
	public void saveLog(@Param("log") String log, @Param("type") String type, @Param("author") String author);
	
	public List<Log> findAllLog(@Param("sizeOfPage") Integer sizeOfPage,@Param("offset") Integer offset);
	
	public int CountAllLog();
}
