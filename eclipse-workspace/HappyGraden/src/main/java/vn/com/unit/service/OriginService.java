package vn.com.unit.service;

import java.util.List;

import vn.com.unit.entity.Category;
import vn.com.unit.entity.Origin;

public interface OriginService {
	
	public int countAllOrigin();
	
	public List<Origin> findOriginPageable(int limit,int offset);
	
	public Origin findOriginByName(String Name);
	
	public Origin createOrigin(Origin origin);
}
