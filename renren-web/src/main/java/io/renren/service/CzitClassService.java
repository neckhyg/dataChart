package io.renren.service;

import io.renren.entity.CzitClassEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-24 08:47:04
 */
public interface CzitClassService {
	
	CzitClassEntity queryObject(Integer id);
	
	List<CzitClassEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CzitClassEntity czitClass);
	
	void update(CzitClassEntity czitClass);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    CzitClassEntity queryObject(String Training_Code);
}
