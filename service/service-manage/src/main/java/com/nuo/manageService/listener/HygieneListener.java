package com.nuo.manageService.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.nuo.manageService.entity.TbHygiene;
import com.nuo.manageService.entity.TbStudent;
import com.nuo.manageService.entity.excel.HygieneExcelData;
import com.nuo.manageService.entity.excel.StudentExcelData;
import com.nuo.manageService.service.TbHygieneService;
import com.nuo.manageService.service.TbStudentService;
import com.nuo.servicebase.config.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class HygieneListener implements ReadListener<HygieneExcelData> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private Collection<HygieneExcelData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private TbHygieneService hygieneService;

    public HygieneListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数

    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public HygieneListener(TbHygieneService hygieneService) {
        this.hygieneService = hygieneService;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(HygieneExcelData data, AnalysisContext context) {
        log.info("解析到一条数据:{}", data);
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        ArrayList<TbHygiene> tbHygienes = new ArrayList<>();
        for (HygieneExcelData hygieneExcelData : cachedDataList) {
            // 进行数据转换，封装为tbStudent对象
            TbHygiene tbHygiene = new TbHygiene();
            BeanUtils.copyProperties(hygieneExcelData,tbHygiene);
            tbHygienes.add(tbHygiene);
        }
        hygieneService.saveBatch(tbHygienes, cachedDataList.size());
        log.info("存储数据库成功！");
    }
}
