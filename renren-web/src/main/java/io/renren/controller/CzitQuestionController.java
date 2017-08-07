package io.renren.controller;

import io.renren.entity.CzitQuestionEntity;
import io.renren.entity.CzitStudentEntity;
import io.renren.service.CzitQuestionService;
import io.renren.service.CzitStudentService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


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
    @Autowired
    private CzitStudentService czitStudentService;
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
    @RequestMapping("/list2")
   // @RequiresPermissions("czitquestion:list")
    public R list2(@RequestParam Map<String, Object> params){
        //查询列表数据
      //  Query query = new Query(params);

        List<CzitQuestionEntity> czitQuestionList = czitQuestionService.queryList(params);
//        int total = czitQuestionService.queryTotal(query);

//        PageUtils pageUtil = new PageUtils(czitQuestionList, total, query.getLimit(), query.getPage());

        return R.ok().put("data", czitQuestionList);
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
    @ResponseBody
    @RequestMapping(value = "/save2", method = RequestMethod.POST)
   // @RequiresPermissions("czitquestion:save")
    public R save2(String training,String card,String title) throws IOException {
        CzitQuestionEntity czitQuestion  = new CzitQuestionEntity();
        czitQuestion.setTrainingId(training);
        czitQuestion.setQuestionCreatorid(card);
        czitQuestion.setQuestionTitle(title);
        czitQuestion.setQuestionCreatetime(new Date());


        CzitStudentEntity czitStudent = czitStudentService.queryObjectByidCard(card);
        czitQuestion.setQuestionCreatorcompany(czitStudent.getStudentCompany());
        czitQuestion.setQuestionCreatorname(czitStudent.getStudentName());

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
