package group.bigone.api.service;

import group.bigone.api.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    final private SqlSession sqlSession;

    public UserService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Optional<User> selectUserById(String userId) {
        User user = (User) sqlSession.selectOne("user.selectUserById", userId);

        return Optional.of(user);
    }

    public Optional<User> selectUserByNo(Long userNo) {
        User user = (User) sqlSession.selectOne("user.selectUserByNo", userNo);

        return Optional.of(user);
    }

    public Optional<User> selectProviderUser(String userId, String provider) {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId", userId);
        hashMap.put("provider", provider);

        User user = (User) sqlSession.selectOne("user.selectProviderUser", hashMap);

        return Optional.of(user);
    }

    public Optional<List<User>> selectUsers() {

        List<User> userList = (List) sqlSession.selectList("user.selectUsers");

        return Optional.of(userList);
    }

    public void deleteUser(String userId) {
        sqlSession.delete("user.deleteUser", userId);
    }

    public void insertUser(User user) {
        sqlSession.insert("user.insertUser", user);
    }

    public void updateUser(User user) {
        sqlSession.update("user.updateUser", user);
    }
}
