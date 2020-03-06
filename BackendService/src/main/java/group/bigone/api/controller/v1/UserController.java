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

@Api(tags = {"User"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class UserController {

    private final UserJpaRepo userJpaRepo;
    private final ResponseService responseService;

    @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "AccessToken", required = true, dataType = "String", paramType = "header")})
    @ApiOperation(value = "Get User", notes = "Select a User by AccessToken")
    @GetMapping(value = "/user")
    public SingleResult<User> findUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        return responseService.getSingleResult(userJpaRepo.findByUserid(id).orElseThrow(CUserNotFoundException::new));
    }

//    @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "AccessToken", required = true, dataType = "String", paramType = "header")})
//    @ApiOperation(value = "회원 입력", notes = "회원을 입력한다")
//    @PostMapping(value = "/user")
//    public SingleResult<User> save(@ApiParam(value = "useremail", required = true) @RequestParam String userid,
//                                   @ApiParam(value = "name", required = true) @RequestParam String username) {
//        User user = User.builder()
//                .userid(userid)
//                .name(username)
//                .build();
//        return responseService.getSingleResult(userJpaRepo.save(user));
//    }

    @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "AccessToken", required = true, dataType = "String", paramType = "header")})
    @ApiOperation(value = "Update User name", notes = "Update User name Information")
    @PutMapping(value = "/user")
    public SingleResult<User> modify(
            @ApiParam(value = "userno", required = true) @RequestParam long userno,
            @ApiParam(value = "name", required = true) @RequestParam String username) {

        User originUser = userJpaRepo.findById(userno).orElseThrow(CUserNotFoundException::new);
        // JPA
        User user = User.builder()
                   .name(username)
                   .build();

        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "AccessToken", required = true, dataType = "String", paramType = "header")})
    @ApiOperation(value = "Delete User", notes = "Delete User by userNo")
    @DeleteMapping(value = "/user/{userno}")
    public CommonResult delete(
            @ApiParam(value = "userno", required = true) @PathVariable long userno) {
        userJpaRepo.deleteById(userno);
        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "AccessToken", required = true, dataType = "String", paramType = "header")})
    @ApiOperation(value = "Get Users", notes = "Select a Users by AccessToken")
    @GetMapping(value = "/users")
    public ListResult<User> findAllUsers() {
        return responseService.getListResult(userJpaRepo.findAll());
    }
}
