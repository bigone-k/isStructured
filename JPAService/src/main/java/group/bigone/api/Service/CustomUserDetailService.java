package group.bigone.api.Service;

import group.bigone.api.advice.exception.CUserNotFoundException;
import group.bigone.api.repo.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserJpaRepo userJpaRepo;

    public UserDetails loadUserByUsername(String userPk) {
        return userJpaRepo.findByUserId(userPk).orElseThrow(CUserNotFoundException::new);
    }
}