package group.bigone.api.controller.v1;

import group.bigone.api.advice.exception.CUserExistException;
import group.bigone.api.service.ResponseService;
import group.bigone.api.service.UserService;
import group.bigone.api.entity.User;
import group.bigone.api.model.response.ListResult;
import group.bigone.api.model.response.SingleResult;
import io.swagger.annotations.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = {"User"})
@RestController
@RequestMapping(value = "/client")
public class UserController {

    private final UserService userService;
    private final ResponseService responseService;

    public UserController(UserService userService, ResponseService responseService) {
        this.userService = userService;
        this.responseService = responseService;
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "AccessToken", required = true, dataType = "String", paramType = "header")})
    @ApiOperation(value = "Get User", notes = "Select a User by AccessToken")
    @GetMapping(value = "/user")
    public SingleResult<User> findUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userNo = Long.valueOf(authentication.getName());

        User user = userService.selectUserByNo(userNo).orElseThrow(() -> new CUserExistException());

        return responseService.getSingleResult(user);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "AccessToken", required = true, dataType = "String", paramType = "header")})
    @ApiOperation(value = "Get Users", notes = "Select a Users by AccessToken")
    @GetMapping(value = "/users")
    public ListResult<User> findAllUsers() {

        List<User> userList = userService.selectUsers().orElseThrow(() -> new CUserExistException());

        return responseService.getListResult(userList);
    }
}
