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

import io.renren.entity.CzitQuestionEntity;
import io.renren.service.CzitQuestionService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-26 15:49:44
 */
@RestController
@RequestMapping("czitquestion")
public class CzitQuestionController {
	@Autowired
	private CzitQuestionService czitQuestionService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("czitquestion:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CzitQuestionEntity> czitQuestionList = czitQuestionService.queryList(query);
		int total = czitQuestionService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(czitQuestionList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("czitquestion:info")
	public R info(@PathVariable("id") Integer id){
		CzitQuestionEntity czitQuestion = czitQuestionService.queryObject(id);
		
		return R.ok().put("czitQuestion", czitQuestion);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("czitquestion:save")
	public R save(@RequestBody CzitQuestionEntity czitQuestion){
		czitQuestionService.save(czitQuestion);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("czitquestion:update")
	public R update(@RequestBody CzitQuestionEntity czitQuestion){
		czitQuestionService.update(czitQuestion);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("czitquestion:delete")
	public R delete(@RequestBody Integer[] ids){
		czitQuestionService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
