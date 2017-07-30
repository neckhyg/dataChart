package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CzitStudentDao;
import io.renren.entity.CzitStudentEntity;
import io.renren.service.CzitStudentService;



@Service("czitStudentService")
public class CzitStudentServiceImpl implements CzitStudentService {
	@Autowired
	private CzitStudentDao czitStudentDao;
	
	@Override
	public CzitStudentEntity queryObject(Integer id){
		return czitStudentDao.queryObject(id);
	}
	
	@Override
	public List<CzitStudentEntity> queryList(Map<String, Object> map){
		return czitStudentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return czitStudentDao.queryTotal(map);
	}
	
	@Override
	public void save(CzitStudentEntity czitStudent){
		czitStudentDao.save(czitStudent);
	}
	
	@Override
	public void update(CzitStudentEntity czitStudent){
		czitStudentDao.update(czitStudent);
	}
	
	@Override
	public void delete(Integer id){
		czitStudentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		czitStudentDao.deleteBatch(ids);
	}
	
}
