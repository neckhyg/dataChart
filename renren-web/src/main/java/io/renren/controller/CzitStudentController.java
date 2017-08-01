package io.renren.controller;

import io.renren.entity.CzitClassEntity;
import io.renren.entity.CzitStudentEntity;
import io.renren.service.CzitClassService;
import io.renren.service.CzitStudentService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-26 15:46:00
 */
@RestController
@RequestMapping("czitstudent")
public class CzitStudentController {
	@Autowired
	private CzitStudentService czitStudentService;
    @Autowired
    private CzitClassService czitClassService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
//	@RequiresPermissions("czitstudent:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CzitStudentEntity> czitStudentList = czitStudentService.queryList(query);
		int total = czitStudentService.queryTotal(query);

        for ( int i = 0 ; i<total; i ++){
            CzitStudentEntity czitStudent = czitStudentList.get(i);
            CzitClassEntity czitClass = czitClassService.queryObject(czitStudent.getTrainingId());
            czitStudentList.get(i).setTrainingClass(czitClass);
        }
        PageUtils pageUtil = new PageUtils(czitStudentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
    @RequestMapping("/list2")
    public R list2(@RequestParam Map<String, Object> params){
        //查询列表数据
    //    Query query = new Query(params);
        List<CzitStudentEntity> czitStudentList = czitStudentService.queryList(params);
        int total = czitStudentService.queryTotal(params);

        for ( int i = 0 ; i<total; i ++){
            CzitStudentEntity czitStudent = czitStudentList.get(i);
            CzitClassEntity czitClass = czitClassService.queryObject(czitStudent.getTrainingId());
            czitStudentList.get(i).setTrainingClass(czitClass);
        }
//        PageUtils pageUtil = new PageUtils(czitStudentList, total, query.getLimit(), query.getPage());

        return R.ok().put("data", czitStudentList);
    }
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("czitstudent:info")
	public R info(@PathVariable("id") Integer id){
		CzitStudentEntity czitStudent = czitStudentService.queryObject(id);
		
		return R.ok().put("czitStudent", czitStudent);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("czitstudent:save")
	public R save(@RequestBody CzitStudentEntity czitStudent){
		czitStudentService.save(czitStudent);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("czitstudent:update")
	public R update(@RequestBody CzitStudentEntity czitStudent){
		czitStudentService.update(czitStudent);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("czitstudent:delete")
	public R delete(@RequestBody Integer[] ids){
		czitStudentService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
