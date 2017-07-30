package io.renren.entity;

import java.io.Serializable;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-28 15:33:03
 */
public class YbmEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String ksname;
	//
	private String ksid;
	//
	private String kszkz;
	//
	private String ksksh;
	//
	private Float gkgrade;
	//
	private String ksmajor;
	//
	private String kslesson;
	//
	private String ksschool;
	//
	private String ksmobile;

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
	public void setKsname(String ksname) {
		this.ksname = ksname;
	}
	/**
	 * 获取：
	 */
	public String getKsname() {
		return ksname;
	}
	/**
	 * 设置：
	 */
	public void setKsid(String ksid) {
		this.ksid = ksid;
	}
	/**
	 * 获取：
	 */
	public String getKsid() {
		return ksid;
	}
	/**
	 * 设置：
	 */
	public void setKszkz(String kszkz) {
		this.kszkz = kszkz;
	}
	/**
	 * 获取：
	 */
	public String getKszkz() {
		return kszkz;
	}
	/**
	 * 设置：
	 */
	public void setKsksh(String ksksh) {
		this.ksksh = ksksh;
	}
	/**
	 * 获取：
	 */
	public String getKsksh() {
		return ksksh;
	}
	/**
	 * 设置：
	 */
	public void setGkgrade(Float gkgrade) {
		this.gkgrade = gkgrade;
	}
	/**
	 * 获取：
	 */
	public Float getGkgrade() {
		return gkgrade;
	}
	/**
	 * 设置：
	 */
	public void setKsmajor(String ksmajor) {
		this.ksmajor = ksmajor;
	}
	/**
	 * 获取：
	 */
	public String getKsmajor() {
		return ksmajor;
	}
	/**
	 * 设置：
	 */
	public void setKslesson(String kslesson) {
		this.kslesson = kslesson;
	}
	/**
	 * 获取：
	 */
	public String getKslesson() {
		return kslesson;
	}
	/**
	 * 设置：
	 */
	public void setKsschool(String ksschool) {
		this.ksschool = ksschool;
	}
	/**
	 * 获取：
	 */
	public String getKsschool() {
		return ksschool;
	}
	/**
	 * 设置：
	 */
	public void setKsmobile(String ksmobile) {
		this.ksmobile = ksmobile;
	}
	/**
	 * 获取：
	 */
	public String getKsmobile() {
		return ksmobile;
	}
}
