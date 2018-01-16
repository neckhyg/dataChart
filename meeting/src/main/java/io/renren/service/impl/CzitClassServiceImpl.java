package io.renren.service.impl;

import io.renren.dao.CzitClassDao;
import io.renren.entity.CzitClassEntity;
import io.renren.service.CzitClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service("czitClassService")
public class CzitClassServiceImpl implements CzitClassService {
	@Autowired
	private CzitClassDao czitClassDao;
	
	@Override
	public CzitClassEntity queryObject(Integer id){
		return czitClassDao.queryObject(id);
	}
	
	@Override
	public List<CzitClassEntity> queryList(Map<String, Object> map){
		return czitClassDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return czitClassDao.queryTotal(map);
	}
	
	@Override
	public void save(CzitClassEntity czitClass){
		czitClassDao.save(czitClass);
	}
	
	@Override
	public void update(CzitClassEntity czitClass){
		czitClassDao.update(czitClass);
	}
	
	@Override
	public void delete(Integer id){
		czitClassDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		czitClassDao.deleteBatch(ids);
	}
	
}
