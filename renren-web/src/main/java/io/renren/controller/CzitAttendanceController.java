package io.renren.controller;

import io.renren.entity.CzitAttendanceEntity;
import io.renren.service.CzitAttendanceService;
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
 * @date 2017-07-26 15:48:20
 */
@RestController
@RequestMapping("czitattendance")
public class CzitAttendanceController {
	@Autowired
	private CzitAttendanceService czitAttendanceService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("czitattendance:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CzitAttendanceEntity> czitAttendanceList = czitAttendanceService.queryList(query);
		int total = czitAttendanceService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(czitAttendanceList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
    @RequestMapping("/list2")
    //@RequiresPermissions("czitattendance:list")
    public R list2(@RequestParam Map<String, Object> params){
        //查询列表数据
    //    Query query = new Query(params);

        List<CzitAttendanceEntity> czitAttendanceList = czitAttendanceService.queryList(params);
//        int total = czitAttendanceService.queryTotal(query);
//
//        PageUtils pageUtil = new PageUtils(czitAttendanceList, total, query.getLimit(), query.getPage());

        return R.ok().put("data", czitAttendanceList);
    }
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("czitattendance:info")
	public R info(@PathVariable("id") Integer id){
		CzitAttendanceEntity czitAttendance = czitAttendanceService.queryObject(id);
		
		return R.ok().put("czitAttendance", czitAttendance);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("czitattendance:save")
	public R save(@RequestBody CzitAttendanceEntity czitAttendance){
		czitAttendanceService.save(czitAttendance);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("czitattendance:update")
	public R update(@RequestBody CzitAttendanceEntity czitAttendance){
		czitAttendanceService.update(czitAttendance);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("czitattendance:delete")
	public R delete(@RequestBody Integer[] ids){
		czitAttendanceService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
