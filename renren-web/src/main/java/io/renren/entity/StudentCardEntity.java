package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author neckhyg
 * @email 497273569@qq.com
 * @date 2017-09-26 15:16:32
 */
public class StudentCardEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String stuno;
	//
	private String stuname;
	//
	private String stucardid;
	//
	private String department;
	//
	private String major;
	//
	private String gender;
	//
	private String classname;

	/**
	 * 设置：
	 */
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	/**
	 * 获取：
	 */
	public String getStuno() {
		return stuno;
	}
	/**
	 * 设置：
	 */
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	/**
	 * 获取：
	 */
	public String getStuname() {
		return stuname;
	}
	/**
	 * 设置：
	 */
	public void setStucardid(String stucardid) {
		this.stucardid = stucardid;
	}
	/**
	 * 获取：
	 */
	public String getStucardid() {
		return stucardid;
	}
	/**
	 * 设置：
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * 获取：
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * 设置：
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * 获取：
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * 设置：
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 获取：
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * 设置：
	 */
	public void setClassname(String classname) {
		this.classname = classname;
	}
	/**
	 * 获取：
	 */
	public String getClassname() {
		return classname;
	}
}
