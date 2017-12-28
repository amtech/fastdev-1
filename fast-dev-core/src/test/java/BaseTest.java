//import cn.lucode.fastdev.StartApplication;
//import cn.lucode.fastdev.util.BeanUtil;
//import cn.lucode.fastdev.util.JsonUtil;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//
///**
// * @author yunfeng.lu
// * @create 2017/12/14.
// */
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes=StartApplication.class,webEnvironment= WebEnvironment.NONE)
//public class BaseTest {
//
//    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
//
//    public void  print(Object o){
//        LOGGER.info("=================================================================");
//        String s= JsonUtil.map2JsonWriteMapNullValue(BeanUtil.bean2Map(o));
//        System.out.println(s);
//        LOGGER.info(s);
//        LOGGER.info("=================================================================");
//    }
//
//}
