package com.nuo.manageService.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nuo.manageService.entity.TbNotice;
import com.nuo.manageService.entity.TbRepair;
import com.nuo.manageService.entity.vo.ReqNoticeVo;
import com.nuo.manageService.entity.vo.ReqRepairVo;
import com.nuo.manageService.service.TbNoticeService;
import commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/manage/notice")
public class NoticeController {

    @Autowired
    private TbNoticeService noticeService;

    // 分页条件查询返回通知信息
    @PostMapping("/page/{pageNum}/{limit}")
    public R getPageList(@PathVariable("pageNum") Integer pageNum,
                         @PathVariable("limit") Integer limit,
                         @RequestBody ReqNoticeVo reqNoticeVo){
        Page<TbNotice> page = new Page<>(pageNum, limit);
        Page<TbNotice> repairPage =  noticeService.getPageList(page,reqNoticeVo);
        long total = repairPage.getTotal();
        List<TbNotice> records = repairPage.getRecords();
        return R.ok().data("rows",records).data("total",total);
    }

    // 提交并发布通知
    @PostMapping("/publish")
    public R publishNotice(@RequestBody TbNotice tbNotice){
        tbNotice.setPublishTime(new Date());
        noticeService.save(tbNotice);
        return R.ok();
    }

    // 根据id删除通知信息
    @DeleteMapping("/delete/{id}")
    public R deleteNoticeVById(@PathVariable("id") String id){
        boolean result = noticeService.removeById(id);
        if (result){
            return R.ok().data("msg","删除成功");
        }else {
            return R.error().data("msg","删除失败");
        }
    }

    // 修改通知信息并发布
    @PostMapping("/update")
    public R updateNotice(@RequestBody TbNotice tbNotice){
        tbNotice.setPublishTime(new Date());
        noticeService.updateById(tbNotice);
        return R.ok();
    }
}
