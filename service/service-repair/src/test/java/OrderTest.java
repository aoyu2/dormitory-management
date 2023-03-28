import com.nuo.repairService.utils.DateTimeUtils;
import com.nuo.repairService.utils.OrderNumberGenerator;
import org.junit.Test;

import java.util.Date;

public class OrderTest {
    @Test
    public void test1(){
        String s = OrderNumberGenerator.generateOrderNumber();
        long l = Long.parseLong(s);
        System.out.println(l);
    }

    @Test
    public void test2(){
        Date date = new Date();
        // 获取当天的起始时间
        Date beginOfDay = DateTimeUtils.getBeginOfDay(date);
        // 获取当天结束时间
        Date endOfDay = DateTimeUtils.getEndOfDay(date);
        System.out.println(beginOfDay);
        System.out.println(endOfDay);
    }
}
