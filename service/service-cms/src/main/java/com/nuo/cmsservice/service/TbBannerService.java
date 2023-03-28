package com.nuo.cmsservice.service;

import com.nuo.cmsservice.entity.TbBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Liu
* @description 针对表【tb_banner(首页banner表)】的数据库操作Service
* @createDate 2023-02-18 13:00:46
*/
public interface TbBannerService extends IService<TbBanner> {

    // 根据时间降序返回三张轮播图
    List<TbBanner> getBanners();
}
