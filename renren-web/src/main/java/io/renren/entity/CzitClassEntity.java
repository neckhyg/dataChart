package io.renren.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-24 08:47:04
 */
public class CzitClassEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String trainingCode;
	//
	private String trainingName;
	//
	private Integer trainingPersonquantity;
	//
	private Integer trainingPrice;
	//
	private Date trainingRegisterstarttime;
	//
	private Date trainingStartdate;
	//
	private String trainingId;
	//
	private String trainingStudenttype;
	//
	private String trainingStationlist;
	//
	private Integer trainingIsshowstation;
	//
	private String trainingPaperurl;
	//
	private String attendanceId;

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
	public void setTrainingCode(String trainingCode) {
		this.trainingCode = trainingCode;
	}
	/**
	 * 获取：
	 */
	public String getTrainingCode() {
		return trainingCode;
	}
	/**
	 * 设置：
	 */
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	/**
	 * 获取：
	 */
	public String getTrainingName() {
		return trainingName;
	}
	/**
	 * 设置：
	 */
	public void setTrainingPersonquantity(Integer trainingPersonquantity) {
		this.trainingPersonquantity = trainingPersonquantity;
	}
	/**
	 * 获取：
	 */
	public Integer getTrainingPersonquantity() {
		return trainingPersonquantity;
	}
	/**
	 * 设置：
	 */
	public void setTrainingPrice(Integer trainingPrice) {
		this.trainingPrice = trainingPrice;
	}
	/**
	 * 获取：
	 */
	public Integer getTrainingPrice() {
		return trainingPrice;
	}
	/**
	 * 设置：
	 */
	public void setTrainingRegisterstarttime(Date trainingRegisterstarttime) {
		this.trainingRegisterstarttime = trainingRegisterstarttime;
	}
	/**
	 * 获取：
	 */
	public Date getTrainingRegisterstarttime() {
		return trainingRegisterstarttime;
	}
	/**
	 * 设置：
	 */
	public void setTrainingStartdate(Date trainingStartdate) {
		this.trainingStartdate = trainingStartdate;
	}
	/**
	 * 获取：
	 */
	public Date getTrainingStartdate() {
		return trainingStartdate;
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
	public void setTrainingStudenttype(String trainingStudenttype) {
		this.trainingStudenttype = trainingStudenttype;
	}
	/**
	 * 获取：
	 */
	public String getTrainingStudenttype() {
		return trainingStudenttype;
	}
	/**
	 * 设置：
	 */
	public void setTrainingStationlist(String trainingStationlist) {
		this.trainingStationlist = trainingStationlist;
	}
	/**
	 * 获取：
	 */
	public String getTrainingStationlist() {
		return trainingStationlist;
	}
	/**
	 * 设置：
	 */
	public void setTrainingIsshowstation(Integer trainingIsshowstation) {
		this.trainingIsshowstation = trainingIsshowstation;
	}
	/**
	 * 获取：
	 */
	public Integer getTrainingIsshowstation() {
		return trainingIsshowstation;
	}
	/**
	 * 设置：
	 */
	public void setTrainingPaperurl(String trainingPaperurl) {
		this.trainingPaperurl = trainingPaperurl;
	}
	/**
	 * 获取：
	 */
	public String getTrainingPaperurl() {
		return trainingPaperurl;
	}
	/**
	 * 设置：
	 */
	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}
	/**
	 * 获取：
	 */
	public String getAttendanceId() {
		return attendanceId;
	}
}
