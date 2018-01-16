package io.renren.entity;

import java.io.Serializable;



/**
 * 
 * 
 * @author neckhyg
 * @email 497273569@qq.com
 * @date 2018-01-14 21:57:31
 */
public class SelectattendanceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String username;
	//
	private Integer deptid;

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
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：
	 */
	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}
	/**
	 * 获取：
	 */
	public Integer getDeptid() {
		return deptid;
	}
}
