package io.renren.entity;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 17-4-3
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
public class ZfstudentchartEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //
    private String departmentCode;
    private String departmentName;
    private String departmentNum;

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentNum() {
        return departmentNum;
    }

    public void setDepartmentNum(String departmentNum) {
        this.departmentNum = departmentNum;
    }
}
