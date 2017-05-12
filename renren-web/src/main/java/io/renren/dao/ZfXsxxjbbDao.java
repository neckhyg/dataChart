package io.renren.dao;

import io.renren.entity.ZfXsxxjbbEntity;
import io.renren.entity.ZfstudentchartEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-31 16:00:34
 */
public interface ZfXsxxjbbDao extends BaseDao<ZfXsxxjbbEntity> {
    List<ZfstudentchartEntity> queryStudentChartList(Map<String, Object> map);
}
