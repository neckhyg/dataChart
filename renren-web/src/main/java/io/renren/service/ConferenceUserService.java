package io.renren.service;

import io.renren.entity.ConferenceUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-02 09:56:17
 */
public interface ConferenceUserService {
	
	ConferenceUserEntity queryObject(Integer id);
	
	List<ConferenceUserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ConferenceUserEntity conferenceUser);
	
	void update(ConferenceUserEntity conferenceUser);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
