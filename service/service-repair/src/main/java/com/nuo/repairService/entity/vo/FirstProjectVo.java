package com.nuo.repairService.entity.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class FirstProjectVo {
    /**
     * 一级报修项目ID
     */
    @TableId
    private String id;

    /**
     * 一级报修项目内容
     */
    private String projectName;

    /**
     * 一级项目包含的二级项目
     */
    private List<SecondProjectVo> childList;
}
