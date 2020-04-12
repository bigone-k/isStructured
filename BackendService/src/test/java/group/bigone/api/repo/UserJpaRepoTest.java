package group.bigone.api.repo;

import group.bigone.api.entity.User;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserJpaRepoTest {
    @Autowired
    private UserJpaRepo userJpaRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void when_findByUserId_thenReturnUser() {
        String userid = "trium10@gmail.com";
        String password = "eo1ok12";
        String name = "bigone";
        // given
        userJpaRepo.save(User.builder()
                .userid(userid)
                .password(passwordEncoder.encode(password))
                .name(name)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        // when
        Optional<User> user = userJpaRepo.findByUserId(userid);
        // then
        Assert.assertNotNull(user);// user객체가 null이 아닌지 체크
        Assert.assertTrue(user.isPresent()); // user객체가 존재여부 true/false 체크
        Assert.assertEquals(user.get().getName(), name); // user객체의 name과 name변수 값이 같은지 체크
    }
}
