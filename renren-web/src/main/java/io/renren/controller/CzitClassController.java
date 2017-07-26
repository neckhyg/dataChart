package io.renren.controller;

import io.renren.entity.CzitClassEntity;
import io.renren.service.CzitClassService;
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
 * @date 2017-07-24 08:47:04
 */
@RestController
@RequestMapping("czitclass")
public class CzitClassController {
	@Autowired
	private CzitClassService czitClassService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("czitclass:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CzitClassEntity> czitClassList = czitClassService.queryList(query);
		int total = czitClassService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(czitClassList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
    @RequestMapping("/list2")
    @RequiresPermissions("czitclass:list2")
    public R list2(@RequestParam Map<String, Object> params){
        //查询列表数据
      //  Query query = new Query(params);

        List<CzitClassEntity> czitClassList = czitClassService.queryList(params);
//        int total = czitClassService.queryTotal(query);

//        PageUtils pageUtil = new PageUtils(czitClassList, total, query.getLimit(), query.getPage());

        return R.ok().put("data", czitClassList);
    }
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("czitclass:info")
	public R info(@PathVariable("id") Integer id){
		CzitClassEntity czitClass = czitClassService.queryObject(id);
		
		return R.ok().put("czitClass", czitClass);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("czitclass:save")
	public R save(@RequestBody CzitClassEntity czitClass){
		czitClassService.save(czitClass);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("czitclass:update")
	public R update(@RequestBody CzitClassEntity czitClass){
		czitClassService.update(czitClass);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("czitclass:delete")
	public R delete(@RequestBody Integer[] ids){
		czitClassService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
