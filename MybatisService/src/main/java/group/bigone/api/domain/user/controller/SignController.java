package group.bigone.api.domain.user.controller;

import group.bigone.api.domain.user.model.SignUpRequest;
import group.bigone.api.global.error.exception.BusinessException;
import group.bigone.api.global.error.exception.ErrorCode;
import group.bigone.api.global.common.constants.GlobalConstants;
import group.bigone.api.global.config.auth.JwtTokenProvider;
import group.bigone.api.domain.user.entity.User;
import group.bigone.api.global.common.model.CommonResult;
import group.bigone.api.global.common.model.SingleResult;
import group.bigone.api.domain.oauth.model.KakaoProfile;
import group.bigone.api.domain.oauth.service.KakaoService;
import group.bigone.api.global.common.service.ResponseService;
import group.bigone.api.domain.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@Api(tags = {"Sign"})
@RestController
@RequestMapping(value = GlobalConstants.PREFIX_URL)
public class SignController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;
    private final KakaoService kakaoService;

    public SignController(UserService userService, JwtTokenProvider jwtTokenProvider, ResponseService responseService, PasswordEncoder passwordEncoder, KakaoService kakaoService) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.responseService = responseService;
        this.passwordEncoder = passwordEncoder;
        this.kakaoService = kakaoService;
    }


    @ApiOperation(value = "SignIn", notes = "Do signIn")
    @GetMapping(value = "/signin")
    public SingleResult<String> signin(@ApiParam(value = "email", required = true) @RequestParam String userId,
                                       @ApiParam(value = "passWord", required = true) @RequestParam String password) {

        User user = userService.selectUserById(userId).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        if ( !passwordEncoder.matches(password, user.getPassword()))
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);

        return responseService.getSingleResult(jwtTokenProvider.createToken(user.getUsername(), user.getRoles()));
    }

    @ApiOperation(value = "SignUp", notes = "Do signUp")
    @PostMapping(value = "/signup")
    public CommonResult signup(@RequestBody @Valid SignUpRequest signUpRequest) {

        userService.insertUser(User.builder()
                .userId(signUpRequest.getUserId())
                .password(passwordEncoder.encode(signUpRequest.getPassWord()))
                .name(signUpRequest.getName())
                .roles(Collections.singletonList("ROLE_USER"))
                .build());

        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "SignIn by Provider", notes = "SignIn by Provider")
    @PostMapping(value = "/signin/{provider}")
    public SingleResult<String> signinByProvider(
            @ApiParam(value = "Service provider", required = true, defaultValue = "kakao") @PathVariable String provider,
            @ApiParam(value = "access_token", required = true) @RequestParam String accessToken) {

        KakaoProfile profile = kakaoService.getKakaoProfile(accessToken);
        User user = userService.selectProviderUser(String.valueOf(profile.getId()), provider).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getUsername()), user.getRoles()));
    }

    @ApiOperation(value = "SignUp by Provider", notes = "SignUp by Provider")
    @PostMapping(value = "/signup/{provider}")
    public CommonResult signupProvider(@ApiParam(value = "Service provider", required = true, defaultValue = "kakao") @PathVariable String provider,
                                       @ApiParam(value = "access_token", required = true) @RequestParam String accessToken,
                                       @ApiParam(value = "name", required = true) @RequestParam String name) {

        KakaoProfile profile = kakaoService.getKakaoProfile(accessToken);

        Optional<User> user = userService.selectProviderUser(String.valueOf(profile.getId()), provider);
        if(user.isPresent())
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);

        userService.insertUser(User.builder()
                .userId(String.valueOf(profile.getId()))
                .provider(provider)
                .name(name)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());

        return responseService.getSuccessResult();
    }
}
