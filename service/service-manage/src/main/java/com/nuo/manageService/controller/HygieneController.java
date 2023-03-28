package com.nuo.manageService.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nuo.manageService.entity.TbHygiene;
import com.nuo.manageService.entity.vo.ReqHygiene;
import com.nuo.manageService.entity.vo.WeekVo;
import com.nuo.manageService.service.TbHygieneService;
import commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/manage/hygiene")
public class HygieneController {

    @Autowired
    private TbHygieneService hygieneService;

    // 根据楼栋号条件分页查询返回宿舍卫生得分信息
    @PostMapping("/page/{pageNum}/{limit}")
    public R getPageList(@PathVariable("pageNum") Integer pageNum,
                         @PathVariable("limit") Integer limit,
                         @RequestBody ReqHygiene reqHygiene){
        Page<TbHygiene> hygienePage = new Page<>(pageNum, limit);
        hygieneService.getPageList(hygienePage,reqHygiene);
        long total = hygienePage.getTotal();
        List<TbHygiene> records = hygienePage.getRecords();
        return R.ok().data("rows",records).data("total",total);
    }

    // 返回卫生得分周期
    @GetMapping("/week")
    public R getAllWeek(){
        List<WeekVo> weekList = hygieneService.getAllWeek();
        return R.ok().data("list",weekList);
    }

    // 通过excel文件批量添加学生信息
    //实现EasyExcel对Excel读操作
    @PostMapping("/upload")
    public R uploadByExcel(MultipartFile file){
        hygieneService.uploadByExcel(file,hygieneService);
        return R.ok();
    }

    // 通过excel文件批量导出卫生排名信息
    //实现EasyExcel对Excel写操作
    @GetMapping("/export")
    public R exportByExcel(){
        String exportInfo = hygieneService.exportByExcel();
        return R.ok().data("path",exportInfo);
    }

    // 修改成绩
    @PostMapping("/update/grade/{id}/{value}")
    public R updateGrade(@PathVariable("id") String id,
                         @PathVariable("value") Double value){
        hygieneService.updateGrade(id,value);
        return R.ok();
    }
}
