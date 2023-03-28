package com.nuo.repairService.controller;

import com.nuo.repairService.entity.TbNotice;
import com.nuo.repairService.service.TbNoticeService;
import commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private TbNoticeService noticeService;

    @GetMapping("/get")
    public R getNotice(){
        // 返回最新的一条通知
        TbNotice notice = noticeService.getOneNotice();
        return R.ok().data("notice",notice);
    }
}
