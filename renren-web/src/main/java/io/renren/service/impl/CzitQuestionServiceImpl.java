package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CzitQuestionDao;
import io.renren.entity.CzitQuestionEntity;
import io.renren.service.CzitQuestionService;



@Service("czitQuestionService")
public class CzitQuestionServiceImpl implements CzitQuestionService {
	@Autowired
	private CzitQuestionDao czitQuestionDao;
	
	@Override
	public CzitQuestionEntity queryObject(Integer id){
		return czitQuestionDao.queryObject(id);
	}
	
	@Override
	public List<CzitQuestionEntity> queryList(Map<String, Object> map){
		return czitQuestionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return czitQuestionDao.queryTotal(map);
	}
	
	@Override
	public void save(CzitQuestionEntity czitQuestion){
		czitQuestionDao.save(czitQuestion);
	}
	
	@Override
	public void update(CzitQuestionEntity czitQuestion){
		czitQuestionDao.update(czitQuestion);
	}
	
	@Override
	public void delete(Integer id){
		czitQuestionDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		czitQuestionDao.deleteBatch(ids);
	}
	
}
