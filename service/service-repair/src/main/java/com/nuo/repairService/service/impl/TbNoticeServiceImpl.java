package com.nuo.repairService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.repairService.entity.TbNotice;
import com.nuo.repairService.service.TbNoticeService;
import com.nuo.repairService.mapper.TbNoticeMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
* @author Liu
* @description 针对表【tb_notice(公告表)】的数据库操作Service实现
* @createDate 2023-02-19 17:02:33
*/
@Service
public class TbNoticeServiceImpl extends ServiceImpl<TbNoticeMapper, TbNotice>
    implements TbNoticeService{


    // 返回最新的一条通知
    @Override
    public TbNotice getOneNotice() {
        QueryWrapper<TbNotice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("publish_time");
        List<TbNotice> tbNotices = baseMapper.selectList(queryWrapper);
        tbNotices.sort(new Comparator<TbNotice>() {
            @Override
            public int compare(TbNotice o1, TbNotice o2) {
                return -(o1.getGmtModified().compareTo(o2.getGmtModified()));
            }
        });
        TbNotice notice = tbNotices.get(0);
        return notice;
    }
}




