package com.nuo.manageService.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nuo.manageService.entity.TbDormitory;
import com.nuo.manageService.entity.TbNotice;
import com.nuo.manageService.entity.vo.DormitoryVo;
import com.nuo.manageService.entity.vo.ReqNoticeVo;
import com.nuo.manageService.service.TbDormitoryService;
import commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage/dormitory")
public class DormitoryController {

    @Autowired
    private TbDormitoryService dormitoryService;

    // 根据楼栋号查询返回宿舍信息
    @GetMapping("get/{buildingNo}")
    public R getDormitoryNo(@PathVariable("buildingNo") String buildingNo){
        List<TbDormitory> dormitoryList = dormitoryService.getDormitoryList(buildingNo);
        return R.ok().data("list",dormitoryList);
    }

    // 解决前端清除请求楼栋号信息后，清除掉宿舍信息
    @GetMapping("get")
    public R resolveNoArgs(){
        return R.ok().data("list",null);
    }

    // 根据楼栋号分页查询返回宿舍特定信息
    @GetMapping("/page/{pageNum}/{limit}/{buildingNo}")
    public R getPageList(@PathVariable("pageNum") Integer pageNum,
                         @PathVariable("limit") Integer limit,
                         @PathVariable("buildingNo") String buildingNo){
        Page<DormitoryVo> page = new Page<>(pageNum, limit);
        Page<DormitoryVo> repairPage =  dormitoryService.getPageList(page,buildingNo);
        long total = repairPage.getTotal();
        List<DormitoryVo> records = repairPage.getRecords();
        System.out.println(records);
        return R.ok().data("rows",records).data("total",total);
    }

    // 根据宿舍号和楼栋号返回DormitoryVo对象
    @GetMapping("/search/{buildingNo}/{dormitoryNo}")
    public R getDormitoryVo(@PathVariable("buildingNo") String buildingNo,
                            @PathVariable("dormitoryNo") String dormitoryNo){
        // 返回是一个长度为1的集合
        List<DormitoryVo> list = dormitoryService.getDormitoryVo(dormitoryNo,buildingNo);
        if (list == null){
            return R.ok().data("list",null);
        }
        return R.ok().data("list",list);
    }

    // 添加宿舍
    @PostMapping("/add/{buildingNo}/{dormitoryNo}")
    public R addDormitory(@PathVariable("buildingNo") String buildingNo,
                          @PathVariable("dormitoryNo") String dormitoryNo){
        TbDormitory tbDormitory = new TbDormitory();
        tbDormitory.setBuildingNo(buildingNo);
        tbDormitory.setDormitoryNo(dormitoryNo);
        dormitoryService.save(tbDormitory);
        return R.ok();
    }
}
