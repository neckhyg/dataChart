package io.renren.service.impl;

import io.renren.dao.CzitClassDao;
import io.renren.dao.CzitStudentDao;
import io.renren.entity.CzitClassEntity;
import io.renren.entity.CzitStudentEntity;
import io.renren.service.CzitStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service("czitStudentService")
public class CzitStudentServiceImpl implements CzitStudentService {
	@Autowired
	private CzitStudentDao czitStudentDao;
//    @Autowired
//    private CzitClassDao czitClassDao ;

	@Override
	public CzitStudentEntity queryObject(Integer id){
		return czitStudentDao.queryObject(id);
	}
    @Override
    public CzitStudentEntity queryObjectByidCard(String  idCard){

//          String sqlStr  =  " where Student_IDCard = \'" +idCard+"\'"  ;
        return czitStudentDao.queryObjectBySql(idCard);
    }
	@Override
	public List<CzitStudentEntity> queryList(Map<String, Object> map){
//        List<CzitStudentEntity> list =  czitStudentDao.queryList(map);
//
//        if( list != null){
//
//            CzitStudentEntity czitStudent = list.get(0);
//
//            CzitClassEntity czitClass = czitClassDao.(czitStudent.getTrainingId());
//            list.get(0).setTrainingClass(czitClass);
//
//
//        }
//		return list;//czitStudentDao.queryList(map);

        return czitStudentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return czitStudentDao.queryTotal(map);
	}
	
	@Override
	public void save(CzitStudentEntity czitStudent){
		czitStudentDao.save(czitStudent);
	}

	@Override
	public void update(CzitStudentEntity czitStudent){
		czitStudentDao.update(czitStudent);
	}
	
	@Override
	public void delete(Integer id){
		czitStudentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		czitStudentDao.deleteBatch(ids);
	}
	
}
