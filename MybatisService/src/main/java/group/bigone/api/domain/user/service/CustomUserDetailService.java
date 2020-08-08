package group.bigone.api.domain.user.service;

import group.bigone.api.advice.exception.BusinessException;
import group.bigone.api.advice.exception.ErrorCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userPk) {
        return userService.selectUserByNo(Long.valueOf(userPk)).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }
}