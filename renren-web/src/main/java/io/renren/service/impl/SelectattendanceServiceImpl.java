package io.renren.service.impl;

import io.renren.dao.SelectattendanceDao;
import io.renren.entity.SelectattendanceEntity;
import io.renren.service.SelectattendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service("selectattendanceService")
public class SelectattendanceServiceImpl implements SelectattendanceService {
	@Autowired
	private SelectattendanceDao selectattendanceDao;
	
	@Override
	public SelectattendanceEntity queryObject(Integer id){
		return selectattendanceDao.queryObject(id);
	}
	
	@Override
	public List<SelectattendanceEntity> queryList(Map<String, Object> map){
		return selectattendanceDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return selectattendanceDao.queryTotal(map);
	}
	
	@Override
	public void save(SelectattendanceEntity selectattendance){
		selectattendanceDao.save(selectattendance);
	}
	
	@Override
	public void update(SelectattendanceEntity selectattendance){
		selectattendanceDao.update(selectattendance);
	}
	
	@Override
	public void delete(Integer id){
		selectattendanceDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		selectattendanceDao.deleteBatch(ids);
	}

    @Override
    public List<SelectattendanceEntity> queryListByDeptID(String deptID) {

        List<SelectattendanceEntity> lst=   selectattendanceDao.queryListByDeptID(deptID);
        return  lst;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
