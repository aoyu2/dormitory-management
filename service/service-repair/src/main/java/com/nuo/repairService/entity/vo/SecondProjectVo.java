package com.nuo.repairService.entity.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SecondProjectVo {
    /**
     * 二级报修项目ID
     */
    @TableId
    private String id;

    /**
     * 二级报修项目内容
     */
    private String projectName;
}
