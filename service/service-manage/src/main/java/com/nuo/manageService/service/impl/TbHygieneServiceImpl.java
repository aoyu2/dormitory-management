package com.nuo.manageService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.manageService.entity.TbBuilding;
import com.nuo.manageService.entity.TbHygiene;
import com.nuo.manageService.entity.excel.HygieneExcelData;
import com.nuo.manageService.entity.excel.HygieneExcelExportData;
import com.nuo.manageService.entity.excel.StudentExcelData;
import com.nuo.manageService.entity.excel.StudentExcelExportData;
import com.nuo.manageService.entity.vo.ReqHygiene;
import com.nuo.manageService.entity.vo.WeekVo;
import com.nuo.manageService.listener.HygieneListener;
import com.nuo.manageService.listener.StudentListener;
import com.nuo.manageService.service.TbHygieneService;
import com.nuo.manageService.mapper.TbHygieneMapper;
import com.nuo.servicebase.config.exception.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
* @author Liu
* @description 针对表【tb_hygiene(卫生得分表)】的数据库操作Service实现
* @createDate 2023-03-12 15:55:16
*/
@Service
public class TbHygieneServiceImpl extends ServiceImpl<TbHygieneMapper, TbHygiene>
    implements TbHygieneService{

    @Autowired
    private TbHygieneMapper hygieneMapper;

    @Override
    public void getPageList(Page<TbHygiene> hygienePage, ReqHygiene reqHygiene) {
        QueryWrapper<TbHygiene> queryWrapper = new QueryWrapper<>();
        // 取出数据
        String week = reqHygiene.getWeek();
        String buildingNo = reqHygiene.getBuildingNo();
        queryWrapper.eq("building_no",buildingNo).eq("week",week).orderByDesc("grade");
        baseMapper.selectPage(hygienePage, queryWrapper);
    }

    @Override
    public List<WeekVo> getAllWeek() {
        List<String> weeks = hygieneMapper.getAllWeek();
        List<WeekVo> weekList = new ArrayList<>();
        for (String week : weeks) {
            WeekVo weekVo = new WeekVo();
            weekVo.setId(UUID.randomUUID().toString());
            weekVo.setWeek(week);
            weekList.add(weekVo);
        }
        return weekList;
    }

    @Override
    public void uploadByExcel(MultipartFile file, TbHygieneService hygieneService) {
        try {
            EasyExcel.read(file.getInputStream(), HygieneExcelData.class, new HygieneListener(hygieneService)).sheet().doRead();
        } catch (IOException e) {
            throw new CustomException(44444,"Excel表格读取错误！");
        }
    }

    @Override
    public void updateGrade(String id, double value) {
        hygieneMapper.updateGrade(id,value);
    }

    @Override
    public String exportByExcel() {
        // 查出所有周期
        List<WeekVo> weekVoList = this.getAllWeek();

        // 方法2: 如果写到不同的sheet 同一个对象
        String fileName = "E://宿舍卫生成绩" + ".xlsx";
        // 这里 指定文件
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, HygieneExcelExportData.class).build()) {
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
            for (WeekVo weekVo : weekVoList) {
                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
                WriteSheet writeSheet = EasyExcel.writerSheet(weekVo.getWeek()).build();
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<HygieneExcelExportData> data = getGradeData(weekVo.getWeek());
                excelWriter.write(data, writeSheet);
            }
        }
        return fileName;
    }
    // 根据周期查找对应的宿舍卫生成绩
    public List<HygieneExcelExportData> getGradeData(String week){
        QueryWrapper<TbHygiene> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("week",week);
        List<TbHygiene> tbHygienes = baseMapper.selectList(queryWrapper);

        List<HygieneExcelExportData> returnList = new ArrayList<>();
        for (TbHygiene tbHygiene : tbHygienes) {
            HygieneExcelExportData hygieneExcelExportData = new HygieneExcelExportData();
            BeanUtils.copyProperties(tbHygiene,hygieneExcelExportData);
            returnList.add(hygieneExcelExportData);
        }
        return returnList;
    }
}




