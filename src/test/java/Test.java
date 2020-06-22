import com.yang.blog.YangBlogApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yangyi
 * @date 2020/6/22 16:45
 * @description：
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YangBlogApplication.class)
@AutoConfigureMockMvc
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
public class Test {

    @org.junit.Test
    public void yang() {
        System.out.println("12332234");
    }
}
