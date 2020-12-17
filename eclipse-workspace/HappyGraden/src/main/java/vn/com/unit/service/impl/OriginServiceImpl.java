package vn.com.unit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.unit.entity.Category;
import vn.com.unit.entity.Origin;
import vn.com.unit.repository.OriginRepository;
import vn.com.unit.service.OriginService;

@Service
@Transactional
public class OriginServiceImpl implements OriginService {
	
	@Autowired
	private OriginRepository originRepository;

	@Override
	public int countAllOrigin() {
		// TODO Auto-generated method stub
		try {
			return originRepository.countAllOrigin();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	@Override
	public List<Origin> findOriginPageable(int limit, int offset) {
		// TODO Auto-generated method stub
		try {
			return originRepository.findOriginPageable(limit, offset);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@Override
	public Origin findOriginByName(String name) {
		// TODO Auto-generated method stub
		try {
			return originRepository.findOriginByName(name);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@Override
	public Origin createOrigin(Origin origin) {
		// TODO Auto-generated method stub
		try {
			return originRepository.createOrigin(origin.getOriginName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@Override
	public Origin findOriginById(Long id) {
		// TODO Auto-generated method stub
		try {
			return originRepository.findOriginById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@Override
	public void updateOriginById(Origin origin) {
		try {
			Origin origin_temp = new Origin();

			origin_temp.setOriginId(origin.getOriginId());
			
			/*
			 * Category category_temp =
			 * categoryRepository.findOne(category.getCategoryId());
			 * 
			 * category_temp.setCategoryName(category.getCategoryName());
			 */

			originRepository.updateOriginById(origin.getOriginId(), origin.getOriginName());

		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub

	}
	
	@Override
	public void deleteOriginById(Long id) {
		// TODO Auto-generated method stub
		try {
			originRepository.deleteOriginById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public List<Origin> findAllOrigin(){
		return originRepository.findAllOrigin();
	}
}
