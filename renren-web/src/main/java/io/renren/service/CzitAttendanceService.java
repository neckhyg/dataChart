package io.renren.service;

import io.renren.entity.CzitAttendanceEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-26 15:48:20
 */
public interface CzitAttendanceService {
	
	CzitAttendanceEntity queryObject(Integer id);
	
	List<CzitAttendanceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CzitAttendanceEntity czitAttendance);
	
	void update(CzitAttendanceEntity czitAttendance);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
