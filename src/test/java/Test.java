import com.qf.dao.RoleInfoMapper;
import com.qf.pojo.MenuInfo;
import com.qf.pojo.UserInfo;
import com.qf.service.MenuInfoService;
import com.qf.service.RoleInfoService;
import com.qf.vo.MenuInfoVO;
import com.qf.vo.RoleInfo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:spring-mybatis.xml","classpath:spring-service.xml"} )
public class Test {
    @Autowired
    RoleInfoService roleInfoService;
    MenuInfoService menuInfoService;

    @org.junit.Test
    public void getUserInfoById(){
        List<RoleInfo> roleInfos = roleInfoService.selectUserById(1);
        System.out.println(roleInfos);
    }

    @org.junit.Test
    public void ListMenuByRoleid(){
        List<MenuInfoVO> menuInfoVOS = menuInfoService.ListMenuByRoleid(1);
        System.out.println(menuInfoVOS);
    }
     //展现所有用户
    @org.junit.Test
    public void showRoleInfo(){
        List<com.qf.pojo.RoleInfo> roleInfos = roleInfoService.showRoleInfo();
        System.out.println(roleInfos);
    }

    //用户登录成功后，，在user.html页面的左侧展现出用户的角色
    @org.junit.Test
    public void showUserRoleMenu(){
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername("王涛");
        userInfo.setPassword("123456");
        List<MenuInfo> menuInfos = roleInfoService.showUserRoleMenu(userInfo);
        System.out.println(menuInfos);
    }
}
