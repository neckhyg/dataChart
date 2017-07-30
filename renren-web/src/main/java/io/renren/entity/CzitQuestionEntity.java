package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-26 15:49:44
 */
public class CzitQuestionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String trainingId;
	//
	private String questionTitle;
	//
	private String questionContent;
	//
	private String questionCreatorid;
	//
	private String questionCreatorname;
	//
	private String questionCreatorcompany;
	//
	private Date questionCreatetime;
	//
	private Integer questionReplyquantity;
	//
	private String questionId;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}
	/**
	 * 获取：
	 */
	public String getTrainingId() {
		return trainingId;
	}
	/**
	 * 设置：
	 */
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	/**
	 * 获取：
	 */
	public String getQuestionTitle() {
		return questionTitle;
	}
	/**
	 * 设置：
	 */
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	/**
	 * 获取：
	 */
	public String getQuestionContent() {
		return questionContent;
	}
	/**
	 * 设置：
	 */
	public void setQuestionCreatorid(String questionCreatorid) {
		this.questionCreatorid = questionCreatorid;
	}
	/**
	 * 获取：
	 */
	public String getQuestionCreatorid() {
		return questionCreatorid;
	}
	/**
	 * 设置：
	 */
	public void setQuestionCreatorname(String questionCreatorname) {
		this.questionCreatorname = questionCreatorname;
	}
	/**
	 * 获取：
	 */
	public String getQuestionCreatorname() {
		return questionCreatorname;
	}
	/**
	 * 设置：
	 */
	public void setQuestionCreatorcompany(String questionCreatorcompany) {
		this.questionCreatorcompany = questionCreatorcompany;
	}
	/**
	 * 获取：
	 */
	public String getQuestionCreatorcompany() {
		return questionCreatorcompany;
	}
	/**
	 * 设置：
	 */
	public void setQuestionCreatetime(Date questionCreatetime) {
		this.questionCreatetime = questionCreatetime;
	}
	/**
	 * 获取：
	 */
	public Date getQuestionCreatetime() {
		return questionCreatetime;
	}
	/**
	 * 设置：
	 */
	public void setQuestionReplyquantity(Integer questionReplyquantity) {
		this.questionReplyquantity = questionReplyquantity;
	}
	/**
	 * 获取：
	 */
	public Integer getQuestionReplyquantity() {
		return questionReplyquantity;
	}
	/**
	 * 设置：
	 */
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	/**
	 * 获取：
	 */
	public String getQuestionId() {
		return questionId;
	}
}
