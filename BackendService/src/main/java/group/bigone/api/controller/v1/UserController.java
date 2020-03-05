package group.bigone.api.controller.v1;

import com.sun.org.apache.xpath.internal.objects.XString;
import group.bigone.api.Service.ResponseService;
import group.bigone.api.advice.exception.CUserNotFoundException;
import group.bigone.api.domain.User;
import group.bigone.api.model.response.CommonResult;
import group.bigone.api.model.response.ListResult;
import group.bigone.api.model.response.SingleResult;
import group.bigone.api.repo.UserJpaRepo;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class UserController {

    private final UserJpaRepo userJpaRepo;
    private final ResponseService responseService;

    @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")})
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다")
    @GetMapping(value = "/users")
    public ListResult<User> findAllUsers() {
        return responseService.getListResult(userJpaRepo.findAll());
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", dataType = "String", paramType = "header")})
    @ApiOperation(value = "회원 단건 조회", notes = "(token) 회원을 조회한다")
    @GetMapping(value = "/user")
    public SingleResult<User> findUserById() {
        // 결과데이터가 단일건인경우 getBasicResult를 이용해서 결과를 출력한다.// SecurityContext에서 인증받은 회원의 정보를 얻어온다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userJpaRepo.findByUserid(id).orElseThrow(CUserNotFoundException::new));
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")})
    @ApiOperation(value = "회원 입력", notes = "회원을 입력한다")
    @PostMapping(value = "/user")
    public SingleResult<User> save(@ApiParam(value = "useremail", required = true) @RequestParam String userid,
                                   @ApiParam(value = "name", required = true) @RequestParam String username) {
        User user = User.builder()
                .userid(userid)
                .name(username)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")})
    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다")
    @PutMapping(value = "/user")
    public SingleResult<User> modify(
            @ApiParam(value = "userno", required = true) @RequestParam long userno,
            @ApiParam(value = "name", required = true) @RequestParam String username) {
        User user = User.builder()
                .userno(userno)
                .name(username)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")})
    @ApiOperation(value = "회원 삭제", notes = "userId로 회원정보를 삭제한다")
    @DeleteMapping(value = "/user/{userno}")
    public CommonResult delete(
            @ApiParam(value = "userno", required = true) @PathVariable long userno) {
        userJpaRepo.deleteById(userno);
        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
        return responseService.getSuccessResult();
    }
}
