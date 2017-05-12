package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-11 14:13:25
 */
public class ConferenceUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String userId;
	//
	private String userName;
	//
	private String sex;
	//
	private String department;
	//
	private String position;
	//
	private String mobile;
	//
	private String email;
	//
	private String room;
	//
	private String transport;
	//
	private Date arriveDate;
	//
	private String testType;

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
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：
	 */
	public String getSex() {
		return sex;
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
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * 获取：
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * 设置：
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：
	 */
	public void setRoom(String room) {
		this.room = room;
	}
	/**
	 * 获取：
	 */
	public String getRoom() {
		return room;
	}
	/**
	 * 设置：
	 */
	public void setTransport(String transport) {
		this.transport = transport;
	}
	/**
	 * 获取：
	 */
	public String getTransport() {
		return transport;
	}
	/**
	 * 设置：
	 */
	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}
	/**
	 * 获取：
	 */
	public Date getArriveDate() {
		return arriveDate;
	}
	/**
	 * 设置：
	 */
	public void setTestType(String testType) {
		this.testType = testType;
	}
	/**
	 * 获取：
	 */
	public String getTestType() {
		return testType;
	}
}
