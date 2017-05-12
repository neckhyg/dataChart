package io.renren.service.impl;

import io.renren.entity.ZfstudentchartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.ZfXsxxjbbDao;
import io.renren.entity.ZfXsxxjbbEntity;
import io.renren.service.ZfXsxxjbbService;



@Service("zfXsxxjbbService")
public class ZfXsxxjbbServiceImpl implements ZfXsxxjbbService {
	@Autowired
	private ZfXsxxjbbDao zfXsxxjbbDao;
	
	@Override
	public ZfXsxxjbbEntity queryObject(String xh){
		return zfXsxxjbbDao.queryObject(xh);
	}
	
	@Override
	public List<ZfXsxxjbbEntity> queryList(Map<String, Object> map){
		return zfXsxxjbbDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return zfXsxxjbbDao.queryTotal(map);
	}
	
	@Override
	public void save(ZfXsxxjbbEntity zfXsxxjbb){
		zfXsxxjbbDao.save(zfXsxxjbb);
	}
	
	@Override
	public void update(ZfXsxxjbbEntity zfXsxxjbb){
		zfXsxxjbbDao.update(zfXsxxjbb);
	}
	
	@Override
	public void delete(String xh){
		zfXsxxjbbDao.delete(xh);
	}
	
	@Override
	public void deleteBatch(String[] xhs){
		zfXsxxjbbDao.deleteBatch(xhs);
	}

    @Override
    public List<ZfstudentchartEntity> queryStudentChartList(Map<String, Object> map) {
        return zfXsxxjbbDao.queryStudentChartList(map) ;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
