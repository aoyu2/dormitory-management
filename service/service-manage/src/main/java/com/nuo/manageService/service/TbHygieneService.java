package com.nuo.manageService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nuo.manageService.entity.TbHygiene;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuo.manageService.entity.vo.ReqHygiene;
import com.nuo.manageService.entity.vo.WeekVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author Liu
* @description 针对表【tb_hygiene(卫生得分表)】的数据库操作Service
* @createDate 2023-03-12 15:55:16
*/
public interface TbHygieneService extends IService<TbHygiene> {
    // 根据楼栋号条件分页查询返回宿舍卫生得分信息
    void getPageList(Page<TbHygiene> hygienePage, ReqHygiene reqHygiene);

    // 返回卫生得分周期
    List<WeekVo> getAllWeek();

    // 通过excel文件批量添加学生信息
    void uploadByExcel(MultipartFile file, TbHygieneService hygieneService);

    // 修改成绩
    void updateGrade(String id, double value);

    // 通过excel文件批量导出卫生排名信息
    String exportByExcel();
}
