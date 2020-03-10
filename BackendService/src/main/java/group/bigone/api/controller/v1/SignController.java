package group.bigone.api.controller.v1;

import group.bigone.api.Service.ResponseService;
import group.bigone.api.advice.exception.CEmailSigninFailedException;
import group.bigone.api.config.security.JwtTokenProvider;
import group.bigone.api.domain.User;
import group.bigone.api.model.response.CommonResult;
import group.bigone.api.model.response.SingleResult;
import group.bigone.api.repo.UserJpaRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Api(tags = {"Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignController {

    private final UserJpaRepo userJpaRepo;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value = "SignIn", notes = "Do signIn")
    @GetMapping(value = "/signin")
    public SingleResult<String> signin(@ApiParam(value = "email", required = true) @RequestParam String id,
                                       @ApiParam(value = "passWord", required = true) @RequestParam String password) {
        User user = userJpaRepo.findByUserid(id).orElseThrow(CEmailSigninFailedException::new);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new CEmailSigninFailedException();

        return responseService.getSingleResult(jwtTokenProvider.createToken(user.getUsername(), user.getRoles()));
    }

    @ApiOperation(value = "SignUp", notes = "Do signUp")
    @PostMapping(value = "/signup")
    public CommonResult signup(@ApiParam(value = "email", required = true) @RequestParam String id,
                               @ApiParam(value = "passWord", required = true) @RequestParam String password,
                               @ApiParam(value = "name", required = true) @RequestParam String name) {

        userJpaRepo.save(User.builder()
                .userid(id)
                .password(passwordEncoder.encode(password))
                .name(name)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        return responseService.getSuccessResult();
    }
}
