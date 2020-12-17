package vn.com.unit.repository;

import java.util.List;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.repository.query.Param;

import vn.com.unit.entity.Category;
import vn.com.unit.entity.Origin;

public interface OriginRepository extends MirageRepository<Origin, Long> {
	
	public int countAllOrigin();
	
	public List<Origin> findOriginPageable(@Param("sizeOfPage") Integer sizeOfPage,@Param("offset") Integer offset);
	
	public Origin findOriginByName(@Param("name") String name);
	
	public Origin createOrigin(@Param("name") String name);
	
	public Origin findOriginById(@Param("id") Long id);
	
	public void updateOriginById(@Param("id") Long id,@Param("name") String name);
	
	public void deleteOriginById(@Param("id") Long id);
	
	public List<Origin> findAllOrigin(); 
}
