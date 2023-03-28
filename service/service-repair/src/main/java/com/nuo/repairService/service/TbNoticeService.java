package com.nuo.repairService.service;

import com.nuo.repairService.entity.TbNotice;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Liu
* @description 针对表【tb_notice(公告表)】的数据库操作Service
* @createDate 2023-02-19 17:02:33
*/
public interface TbNoticeService extends IService<TbNotice> {

    // 返回最新的一条通知
    TbNotice getOneNotice();
}
