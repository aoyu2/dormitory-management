package com.nuo.repairService.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 二级报修项目表
 * @TableName second_project
 */
@TableName(value ="second_project")
@Data
public class SecondProject implements Serializable {
    /**
     * 二级报修项目ID
     */
    @TableId
    private String id;

    /**
     * 二级报修项目内容
     */
    private String secondProject;

    /**
     * 一级报修项目ID
     */
    private String firstId;

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
        SecondProject other = (SecondProject) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSecondProject() == null ? other.getSecondProject() == null : this.getSecondProject().equals(other.getSecondProject()))
            && (this.getFirstId() == null ? other.getFirstId() == null : this.getFirstId().equals(other.getFirstId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSecondProject() == null) ? 0 : getSecondProject().hashCode());
        result = prime * result + ((getFirstId() == null) ? 0 : getFirstId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", secondProject=").append(secondProject);
        sb.append(", firstId=").append(firstId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}