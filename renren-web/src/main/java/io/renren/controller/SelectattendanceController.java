package io.renren.controller;

import io.renren.entity.SelectattendanceEntity;
import io.renren.service.SelectattendanceService;
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
 * @author neckhyg
 * @email 497273569@qq.com
 * @date 2018-01-14 21:57:31
 */
@RestController
@RequestMapping("selectattendance")
public class SelectattendanceController {
	@Autowired
	private SelectattendanceService selectattendanceService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("selectattendance:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SelectattendanceEntity> selectattendanceList = selectattendanceService.queryList(query);
		int total = selectattendanceService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(selectattendanceList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}

    @RequestMapping("/listbydeptId/{deptID}")
    @RequiresPermissions("selectattendance:listbydeptId")
    public R list(@PathVariable("deptID") String deptID){
        //查询列表数据
//        Query query = new Query(params);

        List<SelectattendanceEntity> selectattendanceList = selectattendanceService.queryListByDeptID(deptID);
//        int total = selectattendanceService.queryTotal(query);
//
//        PageUtils pageUtil = new PageUtils(selectattendanceList, total, query.getLimit(), query.getPage());

        return R.ok().put("list", selectattendanceList);
    }
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("selectattendance:info")
	public R info(@PathVariable("id") Integer id){
		SelectattendanceEntity selectattendance = selectattendanceService.queryObject(id);
		
		return R.ok().put("selectattendance", selectattendance);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("selectattendance:save")
	public R save(@RequestBody SelectattendanceEntity selectattendance){
		selectattendanceService.save(selectattendance);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("selectattendance:update")
	public R update(@RequestBody SelectattendanceEntity selectattendance){
		selectattendanceService.update(selectattendance);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("selectattendance:delete")
	public R delete(@RequestBody Integer[] ids){
		selectattendanceService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
