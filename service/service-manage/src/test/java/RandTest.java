import com.nuo.manageService.utils.RandOfWorkerId;
import org.junit.Test;


public class RandTest {
    @Test
    public void test1(){
        RandOfWorkerId.createWorkerId();
    }

    @Test
    public void test2(){
        String s = "2019052305";
        String substring = s.substring(4);
        System.out.println(substring);
    }
}
