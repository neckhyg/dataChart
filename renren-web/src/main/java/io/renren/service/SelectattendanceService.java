package io.renren.service;

import io.renren.entity.SelectattendanceEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author neckhyg
 * @email 497273569@qq.com
 * @date 2018-01-14 21:57:31
 */
public interface SelectattendanceService {
	
	SelectattendanceEntity queryObject(Integer id);
	
	List<SelectattendanceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SelectattendanceEntity selectattendance);
	
	void update(SelectattendanceEntity selectattendance);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<SelectattendanceEntity> queryListByDeptID(String deptid);
}
