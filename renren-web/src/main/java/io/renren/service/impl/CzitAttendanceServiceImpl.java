package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CzitAttendanceDao;
import io.renren.entity.CzitAttendanceEntity;
import io.renren.service.CzitAttendanceService;



@Service("czitAttendanceService")
public class CzitAttendanceServiceImpl implements CzitAttendanceService {
	@Autowired
	private CzitAttendanceDao czitAttendanceDao;
	
	@Override
	public CzitAttendanceEntity queryObject(Integer id){
		return czitAttendanceDao.queryObject(id);
	}
	
	@Override
	public List<CzitAttendanceEntity> queryList(Map<String, Object> map){
		return czitAttendanceDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return czitAttendanceDao.queryTotal(map);
	}
	
	@Override
	public void save(CzitAttendanceEntity czitAttendance){
		czitAttendanceDao.save(czitAttendance);
	}
	
	@Override
	public void update(CzitAttendanceEntity czitAttendance){
		czitAttendanceDao.update(czitAttendance);
	}
	
	@Override
	public void delete(Integer id){
		czitAttendanceDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		czitAttendanceDao.deleteBatch(ids);
	}
	
}
