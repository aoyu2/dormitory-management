package com.nuo.manageService.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nuo.manageService.entity.TbHousemaster;
import com.nuo.manageService.entity.TbStudent;
import com.nuo.manageService.entity.vo.ReqHousemaster;
import com.nuo.manageService.entity.vo.ReqStudentVo;
import com.nuo.manageService.service.TbHousemasterService;
import commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/manage/host")
public class HostMasterController {

    @Autowired
    private TbHousemasterService housemasterService;

    // 条件分页查询返回宿管信息
    @PostMapping("/page/{pageNum}/{limit}")
    public R getPageList(@PathVariable("pageNum") Integer pageNum,
                         @PathVariable("limit") Integer limit,
                         @RequestBody ReqHousemaster reqHousemaster){
        Page<TbHousemaster> housemasterPage = new Page<>(pageNum, limit);
        housemasterService.getPageList(housemasterPage,reqHousemaster);
        long total = housemasterPage.getTotal();
        List<TbHousemaster> records = housemasterPage.getRecords();
        return R.ok().data("rows",records).data("total",total);
    }

    // 根据id删除宿管信息
    @DeleteMapping("/delete/{id}")
    public R deleteStudent(@PathVariable("id") String id){
        housemasterService.removeById(id);
        return R.ok();
    }

    // 根据id返回宿管信息
    @GetMapping("/get/{id}")
    public R getStudentInfo(@PathVariable("id") String id){
        TbHousemaster housemaster = housemasterService.getById(id);
        return R.ok().data("item",housemaster);
    }

    // 添加单个宿管信息
    @PostMapping("/add")
    public R addStudent(@RequestBody TbHousemaster tbHousemaster){
        housemasterService.save(tbHousemaster);
        return R.ok();
    }

    // 根据id修改宿管信息
    @PostMapping("/update")
    public R updateStudentInfo(@RequestBody TbHousemaster tbHousemaster){
//        studentService.updateStudentInfo(tbStudent);
        housemasterService.updateById(tbHousemaster);
        return R.ok();
    }
}
