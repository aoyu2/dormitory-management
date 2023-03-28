package com.nuo.manageService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.manageService.entity.TbNotice;
import com.nuo.manageService.entity.TbRepair;
import com.nuo.manageService.entity.vo.ReqNoticeVo;
import com.nuo.manageService.service.TbNoticeService;
import com.nuo.manageService.mapper.TbNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author Liu
* @description 针对表【tb_notice(公告表)】的数据库操作Service实现
* @createDate 2023-03-09 21:37:32
*/
@Service
public class TbNoticeServiceImpl extends ServiceImpl<TbNoticeMapper, TbNotice>
    implements TbNoticeService{

    @Override
    public Page<TbNotice> getPageList(Page<TbNotice> page, ReqNoticeVo reqNoticeVo) {
        QueryWrapper<TbNotice> queryWrapper = new QueryWrapper<>();
        // 按发布时间进行降序排序
        queryWrapper.orderByDesc("publish_time");
        // 如果条件为空
        if (reqNoticeVo == null) {
            baseMapper.selectPage(page, queryWrapper);
            return page;
        }
        String begin = reqNoticeVo.getBegin();
        String end = reqNoticeVo.getEnd();

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_modified", end);
        }
        Page<TbNotice> noticePage = baseMapper.selectPage(page, queryWrapper);
        return noticePage;
    }

}




