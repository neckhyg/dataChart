package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.StudentCardDao;
import io.renren.entity.StudentCardEntity;
import io.renren.service.StudentCardService;



@Service("studentCardService")
public class StudentCardServiceImpl implements StudentCardService {
	@Autowired
	private StudentCardDao studentCardDao;
	
	@Override
	public StudentCardEntity queryObject(String stuno){
		return studentCardDao.queryObject(stuno);
	}
	
	@Override
	public List<StudentCardEntity> queryList(Map<String, Object> map){
		return studentCardDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return studentCardDao.queryTotal(map);
	}
	
	@Override
	public void save(StudentCardEntity studentCard){
		studentCardDao.save(studentCard);
	}
	
	@Override
	public void update(StudentCardEntity studentCard){
		studentCardDao.update(studentCard);
	}
	
	@Override
	public void delete(String stuno){
		studentCardDao.delete(stuno);
	}
	
	@Override
	public void deleteBatch(String[] stunos){
		studentCardDao.deleteBatch(stunos);
	}

    @Override
    public StudentCardEntity queryObjectByStuno(String stuno) {
        return studentCardDao.queryObjectBySql(stuno);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
