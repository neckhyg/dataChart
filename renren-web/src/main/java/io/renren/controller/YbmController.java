package io.renren.controller;

import io.renren.entity.YbmEntity;
import io.renren.service.YbmService;
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
 * @date 2017-07-28 15:33:03
 */
@RestController
@RequestMapping("ybm")
public class YbmController {
	@Autowired
	private YbmService ybmService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ybm:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YbmEntity> ybmList = ybmService.queryList(query);
		int total = ybmService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ybmList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ybm:info")
	public R info(@PathVariable("id") Integer id){
		YbmEntity ybm = ybmService.queryObject(id);
		
		return R.ok().put("ybm", ybm);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ybm:save")
	public R save(@RequestBody YbmEntity ybm){
		ybmService.save(ybm);
		
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ybm:update")
	public R update(@RequestBody YbmEntity ybm){
		ybmService.update(ybm);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ybm:delete")
	public R delete(@RequestBody Integer[] ids){
		ybmService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
