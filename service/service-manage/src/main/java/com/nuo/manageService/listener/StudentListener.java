package com.nuo.manageService.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.nuo.manageService.entity.TbStudent;
import com.nuo.manageService.entity.excel.StudentExcelData;
import com.nuo.manageService.service.TbStudentService;
import com.nuo.servicebase.config.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class StudentListener implements ReadListener<StudentExcelData> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private Collection<StudentExcelData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private TbStudentService studentService;

    public StudentListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数

    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public StudentListener(TbStudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(StudentExcelData data, AnalysisContext context) {
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
        ArrayList<TbStudent> tbStudents = new ArrayList<>();
        for (StudentExcelData studentExcelData : cachedDataList) {
            // 进行数据转换，封装为tbStudent对象
            TbStudent tbStudent = new TbStudent();
            // 判断手机号是否合格
            String phone = studentExcelData.getPhone();
            String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
            if (phone.length() != 11) {
                // 抛出异常
                throw new CustomException(444,"手机号应为11位数");
            } else {
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(phone);
                boolean isMatch = m.matches();
                if (isMatch) {
                    // 格式正确
                    BeanUtils.copyProperties(studentExcelData, tbStudent);
                    // 初始化密码为学号后6位
                    tbStudent.setPassword(studentExcelData.getStudentId().toString().substring(4));
                    tbStudent.setPhone(Long.parseLong(studentExcelData.getPhone()));
                    tbStudents.add(tbStudent);
                } else {
                    // 抛出异常
                    throw new CustomException(444,"您的手机号格式不正确");
                }
            }

        }
        studentService.saveBatch(tbStudents, cachedDataList.size());
        log.info("存储数据库成功！");
    }
}
