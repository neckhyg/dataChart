package io.renren.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.CzitStudentEntity;
import io.renren.service.CzitStudentService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


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
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("czitstudent:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CzitStudentEntity> czitStudentList = czitStudentService.queryList(query);
		int total = czitStudentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(czitStudentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
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
