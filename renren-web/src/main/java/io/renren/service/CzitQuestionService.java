package io.renren.service;

import io.renren.entity.CzitQuestionEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-26 15:49:44
 */
public interface CzitQuestionService {
	
	CzitQuestionEntity queryObject(Integer id);
	
	List<CzitQuestionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CzitQuestionEntity czitQuestion);
	
	void update(CzitQuestionEntity czitQuestion);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
