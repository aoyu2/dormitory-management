package com.nuo.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.cmsservice.entity.TbBanner;
import com.nuo.cmsservice.service.TbBannerService;
import com.nuo.cmsservice.mapper.TbBannerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Liu
* @description 针对表【tb_banner(首页banner表)】的数据库操作Service实现
* @createDate 2023-02-18 13:00:46
*/
@Service
public class TbBannerServiceImpl extends ServiceImpl<TbBannerMapper, TbBanner>
    implements TbBannerService{

    @Override
    public List<TbBanner> getBanners() {
        QueryWrapper<TbBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create").last("limit 3");
        List<TbBanner> banners = baseMapper.selectList(queryWrapper);
        return banners;
    }
}




