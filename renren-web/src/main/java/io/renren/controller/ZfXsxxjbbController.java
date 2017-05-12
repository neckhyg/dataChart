package io.renren.controller;

import java.util.List;
import java.util.Map;

import io.renren.entity.ZfstudentchartEntity;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import io.renren.entity.ZfXsxxjbbEntity;
import io.renren.service.ZfXsxxjbbService;
import io.renren.utils.Query;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-31 16:00:34
 */
@Controller
@RequestMapping("zfxsxxjbb")
public class ZfXsxxjbbController {
	@Autowired
	private ZfXsxxjbbService zfXsxxjbbService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("zfxsxxjbb:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ZfXsxxjbbEntity> zfXsxxjbbList = zfXsxxjbbService.queryList(query);
		int total = zfXsxxjbbService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(zfXsxxjbbList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/count")
    @RequiresPermissions("zfxsxxjbb:count")
    public R count(@RequestParam Map<String, Object> params){
        //查询total数据
//        Query query = new Query(params);

//        List<ZfXsxxjbbEntity> zfXsxxjbbList = zfXsxxjbbService.queryList(query);
//        int total = zfXsxxjbbService.queryTotal(params);
        List<ZfstudentchartEntity> ZfstudentchartList  = zfXsxxjbbService.queryStudentChartList(params);


//        PageUtils pageUtil = new PageUtils(zfXsxxjbbList, total, query.getLimit(), query.getPage());

        return R.ok().put("ZfstudentchartList", ZfstudentchartList);
    }
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{xh}")
	@RequiresPermissions("zfxsxxjbb:info")
	public R info(@PathVariable("xh") String xh){
		ZfXsxxjbbEntity zfXsxxjbb = zfXsxxjbbService.queryObject(xh);
		
		return R.ok().put("zfXsxxjbb", zfXsxxjbb);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("zfxsxxjbb:save")
	public R save(@RequestBody ZfXsxxjbbEntity zfXsxxjbb){
		zfXsxxjbbService.save(zfXsxxjbb);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("zfxsxxjbb:update")
	public R update(@RequestBody ZfXsxxjbbEntity zfXsxxjbb){
		zfXsxxjbbService.update(zfXsxxjbb);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("zfxsxxjbb:delete")
	public R delete(@RequestBody String[] xhs){
		zfXsxxjbbService.deleteBatch(xhs);
		
		return R.ok();
	}
	
}
