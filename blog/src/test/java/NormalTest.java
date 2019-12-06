import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shibo.dao.BlogDao;
import com.shibo.dao.UserDao;
import com.shibo.entity.*;
import com.shibo.service.BlogService;
import com.shibo.service.UserService;
import mockit.*;
import mockit.integration.junit4.JMockit;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.AssertTrue;
import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;


@RunWith(JMockit.class)
@WebAppConfiguration
public class NormalTest {

    @Tested
    UserService userService;

    @Injectable
    UserDao userDao;

    @Test
    public void t() {
        new Expectations() {
            {
                userDao.findByToken(String.valueOf(anyInt));
                result = new User();
            }
        };
        new Expectations() {
            {
                userDao.findByToken(String.valueOf(anyInt));
                result = new User();
            }
        };

        User u = userService.findByToken("1");
        Assert.assertNotNull("返回成功", u);
        User u2 = userService.findByToken("1");
        Assert.assertNotNull("返回失败", u2);
    }

    @Test
    public void test(){
        List<Date> orderList = new ArrayList<>();
        orderList.add(new Date());
        System.out.println(JSONObject.toJSONString(orderList));
    }
}
