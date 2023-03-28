package com.nuo.manageService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nuo.manageService.entity.TbNotice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuo.manageService.entity.TbRepair;
import com.nuo.manageService.entity.vo.ReqNoticeVo;

/**
* @author Liu
* @description 针对表【tb_notice(公告表)】的数据库操作Service
* @createDate 2023-03-09 21:37:32
*/
public interface TbNoticeService extends IService<TbNotice> {
    // 分页条件查询返回通知信息
    Page<TbNotice> getPageList(Page<TbNotice> page, ReqNoticeVo reqNoticeVo);

}
