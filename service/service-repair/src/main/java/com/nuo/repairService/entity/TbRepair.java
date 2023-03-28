package com.nuo.repairService.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 保修信息表
 * @TableName tb_repair
 */
@TableName(value ="tb_repair")
@Data
public class TbRepair implements Serializable {
    /**
     * 报修id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学生学号
     */
    private String studentId;

    /**
     * 报修人姓名
     */
    private String studentName;

    /**
     * 报修人电话号码
     */
    private Long phone;

    /**
     * 宿舍号
     */
    private String dormitoryNo;

    /**
     * 楼栋号
     */
    private String buildingNo;

    /**
     * 报修情况
     */
    private String situation;

    /**
     * 报修图片
     */
    private String situationImg;

    /**
     * 一级报修项目
     */
    private String firstProject;

    /**
     * 二级报修项目
     */
    private String secondtProject;

    /**
     * 报修工单
     */
    private String workOrder;

    /**
     * 负责维修人员id
     */
    private Integer workerId;

    /**
     * 是否已经处理（0，未维修  1，已处理  2，已维修）
     */
    private String state;

    /**
     * 处理时间
     */
    private Date handleTime;

    /**
     * 完成维修时间
     */
    private Date finishTime;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
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
        TbRepair other = (TbRepair) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
                && (this.getStudentName() == null ? other.getStudentName() == null : this.getStudentName().equals(other.getStudentName()))
                && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
                && (this.getDormitoryNo() == null ? other.getDormitoryNo() == null : this.getDormitoryNo().equals(other.getDormitoryNo()))
                && (this.getBuildingNo() == null ? other.getBuildingNo() == null : this.getBuildingNo().equals(other.getBuildingNo()))
                && (this.getSituation() == null ? other.getSituation() == null : this.getSituation().equals(other.getSituation()))
                && (this.getSituationImg() == null ? other.getSituationImg() == null : this.getSituationImg().equals(other.getSituationImg()))
                && (this.getFirstProject() == null ? other.getFirstProject() == null : this.getFirstProject().equals(other.getFirstProject()))
                && (this.getSecondtProject() == null ? other.getSecondtProject() == null : this.getSecondtProject().equals(other.getSecondtProject()))
                && (this.getWorkOrder() == null ? other.getWorkOrder() == null : this.getWorkOrder().equals(other.getWorkOrder()))
                && (this.getWorkerId() == null ? other.getWorkerId() == null : this.getWorkerId().equals(other.getWorkerId()))
                && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
                && (this.getHandleTime() == null ? other.getHandleTime() == null : this.getHandleTime().equals(other.getHandleTime()))
                && (this.getFinishTime() == null ? other.getFinishTime() == null : this.getFinishTime().equals(other.getFinishTime()))
                && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
                && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getStudentName() == null) ? 0 : getStudentName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getDormitoryNo() == null) ? 0 : getDormitoryNo().hashCode());
        result = prime * result + ((getBuildingNo() == null) ? 0 : getBuildingNo().hashCode());
        result = prime * result + ((getSituation() == null) ? 0 : getSituation().hashCode());
        result = prime * result + ((getSituationImg() == null) ? 0 : getSituationImg().hashCode());
        result = prime * result + ((getFirstProject() == null) ? 0 : getFirstProject().hashCode());
        result = prime * result + ((getSecondtProject() == null) ? 0 : getSecondtProject().hashCode());
        result = prime * result + ((getWorkOrder() == null) ? 0 : getWorkOrder().hashCode());
        result = prime * result + ((getWorkerId() == null) ? 0 : getWorkerId().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getHandleTime() == null) ? 0 : getHandleTime().hashCode());
        result = prime * result + ((getFinishTime() == null) ? 0 : getFinishTime().hashCode());
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
        sb.append(", studentId=").append(studentId);
        sb.append(", studentName=").append(studentName);
        sb.append(", phone=").append(phone);
        sb.append(", dormitoryNo=").append(dormitoryNo);
        sb.append(", buildingNo=").append(buildingNo);
        sb.append(", situation=").append(situation);
        sb.append(", situationImg=").append(situationImg);
        sb.append(", firstProject=").append(firstProject);
        sb.append(", secondtProject=").append(secondtProject);
        sb.append(", workOrder=").append(workOrder);
        sb.append(", workerId=").append(workerId);
        sb.append(", state=").append(state);
        sb.append(", handleTime=").append(handleTime);
        sb.append(", finishTime=").append(finishTime);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}