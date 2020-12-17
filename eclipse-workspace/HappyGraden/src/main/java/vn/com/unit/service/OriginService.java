package vn.com.unit.service;

import java.util.List;
import vn.com.unit.entity.Origin;

public interface OriginService {
	
	public int countAllOrigin();
	
	public List<Origin> findOriginPageable(int limit,int offset);
	
	public Origin findOriginByName(String Name);
	
	public Origin createOrigin(Origin origin);
	
	public Origin findOriginById(Long id);
	
	public void updateOriginById(Origin origin);
	
	public void deleteOriginById(Long id);
	
	public List<Origin> findAllOrigin();
}
