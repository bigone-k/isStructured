package group.bigone.api.domain.user.service;

import group.bigone.api.domain.user.entity.User;
import io.jsonwebtoken.lang.Assert;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

class UserServiceTest {
    @Autowired
    private SqlSession sqlSession;

    @Test
    void selectUsers() {
        List<User> userList = (List) sqlSession.selectList("user.selectUsers");

        Assert.isNull(Optional.of(userList).orElse(null));
    }
}