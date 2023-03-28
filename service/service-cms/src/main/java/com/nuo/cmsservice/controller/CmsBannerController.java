package com.nuo.cmsservice.controller;

import com.nuo.cmsservice.entity.TbBanner;
import com.nuo.cmsservice.service.TbBannerService;
import commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/banner")
public class CmsBannerController {

    @Autowired
    private TbBannerService bannerService;

    // 根据时间降序返回三张轮播图
    @GetMapping("get")
    public R getBanners(){
        List<TbBanner> banners = bannerService.getBanners();
        return R.ok().data("bannerList",banners);
    }
}
