package com.nuo.manageService.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 学生表
 *
 * @TableName tb_student
 */
@TableName(value = "tb_student")
@Data
public class TbStudent implements Serializable {
    /**
     * 学生ID
     */
    @TableId
    private String id;

    /**
     * 学生名称
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 学号
     */
    private Long studentId;

    /**
     * 班级号
     */
    private Integer classNo;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话号码
     */
    private Long phone;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 楼栋号
     */
    private String buildingNo;

    /**
     * 宿舍ID
     */
    private String dormitoryNo;

    /**
     * 床位号
     */
    private Integer berth;


    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TbStudent other = (TbStudent) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
                && (this.getClassNo() == null ? other.getClassNo() == null : this.getClassNo().equals(other.getClassNo()))
                && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
                && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
                && (this.getHeadImg() == null ? other.getHeadImg() == null : this.getHeadImg().equals(other.getHeadImg()))
                && (this.getBuildingNo() == null ? other.getBuildingNo() == null : this.getBuildingNo().equals(other.getBuildingNo()))
                && (this.getDormitoryNo() == null ? other.getDormitoryNo() == null : this.getDormitoryNo().equals(other.getDormitoryNo()))
                && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
                && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getClassNo() == null) ? 0 : getClassNo().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getHeadImg() == null) ? 0 : getHeadImg().hashCode());
        result = prime * result + ((getBuildingNo() == null) ? 0 : getBuildingNo().hashCode());
        result = prime * result + ((getDormitoryNo() == null) ? 0 : getDormitoryNo().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", studentId=").append(studentId);
        sb.append(", classNo=").append(classNo);
        sb.append(", password=").append(password);
        sb.append(", phone=").append(phone);
        sb.append(", headImg=").append(headImg);
        sb.append(", buildingNo=").append(buildingNo);
        sb.append(", dormitoryNo=").append(dormitoryNo);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}