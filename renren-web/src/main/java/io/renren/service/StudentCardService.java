package io.renren.service;

import io.renren.entity.StudentCardEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author neckhyg
 * @email 497273569@qq.com
 * @date 2017-09-26 15:16:32
 */
public interface StudentCardService {
	
	StudentCardEntity queryObject(String stuno);
	
	List<StudentCardEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(StudentCardEntity studentCard);
	
	void update(StudentCardEntity studentCard);
	
	void delete(String stuno);
	
	void deleteBatch(String[] stunos);
    StudentCardEntity queryObjectByStuno(String stuno);
}
