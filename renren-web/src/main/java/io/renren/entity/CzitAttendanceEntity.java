package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-26 15:48:20
 */
public class CzitAttendanceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String attendanceId;
	//
	private String state;
	//
	private String attendanceconfigLesson;
	//
	private String attendanceconfigId;
	//
	private String studentIdcard;

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
	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}
	/**
	 * 获取：
	 */
	public String getAttendanceId() {
		return attendanceId;
	}
	/**
	 * 设置：
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置：
	 */
	public void setAttendanceconfigLesson(String attendanceconfigLesson) {
		this.attendanceconfigLesson = attendanceconfigLesson;
	}
	/**
	 * 获取：
	 */
	public String getAttendanceconfigLesson() {
		return attendanceconfigLesson;
	}
	/**
	 * 设置：
	 */
	public void setAttendanceconfigId(String attendanceconfigId) {
		this.attendanceconfigId = attendanceconfigId;
	}
	/**
	 * 获取：
	 */
	public String getAttendanceconfigId() {
		return attendanceconfigId;
	}
	/**
	 * 设置：
	 */
	public void setStudentIdcard(String studentIdcard) {
		this.studentIdcard = studentIdcard;
	}
	/**
	 * 获取：
	 */
	public String getStudentIdcard() {
		return studentIdcard;
	}
}
