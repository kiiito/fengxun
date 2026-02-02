package test;

import com.hc.mybatis.mapper.userMapper;
import com.hc.mybatis.pojo.user;
import com.hc.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class testUser {
    @Test
    public void testAllSelect(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        userMapper mapper = sqlSession.getMapper(userMapper.class);
        List<user> users = mapper.selectByExample(null);
        users.forEach(user -> {
            System.out.println(user);
        });
    }
}
