package group.bigone.api.domain.user.entity;

import org.junit.Assert;
import org.junit.Test;

class UserTest {

    User testuser = User.builder()
                        .userNo(1)
                        .userId("bigone")
                        .name("bigone")
                        .build();
    @Test
    void getUsername() {
        Assert.assertEquals(testuser.getUsername(), "1");
    }
}