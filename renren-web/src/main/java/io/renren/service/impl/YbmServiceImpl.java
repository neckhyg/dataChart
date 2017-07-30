package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.YbmDao;
import io.renren.entity.YbmEntity;
import io.renren.service.YbmService;



@Service("ybmService")
public class YbmServiceImpl implements YbmService {
	@Autowired
	private YbmDao ybmDao;
	
	@Override
	public YbmEntity queryObject(Integer id){
		return ybmDao.queryObject(id);
	}
	
	@Override
	public List<YbmEntity> queryList(Map<String, Object> map){
		return ybmDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ybmDao.queryTotal(map);
	}
	
	@Override
	public void save(YbmEntity ybm){
		ybmDao.save(ybm);
	}
	
	@Override
	public void update(YbmEntity ybm){
		ybmDao.update(ybm);
	}
	
	@Override
	public void delete(Integer id){
		ybmDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ybmDao.deleteBatch(ids);
	}
	
}
