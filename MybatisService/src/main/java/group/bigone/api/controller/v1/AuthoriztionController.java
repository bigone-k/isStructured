package group.bigone.api.controller.v1;

import group.bigone.api.config.security.JwtTokenProvider;
import group.bigone.api.entity.IDCard;
import group.bigone.api.model.response.SingleResult;
import group.bigone.api.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Api(tags = {"Outer"})
@RestController
@RequestMapping(value = "/auth")
public class AuthoriztionController {

    private final ResponseService responseService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthoriztionController(ResponseService responseService, JwtTokenProvider jwtTokenProvider) {
        this.responseService = responseService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "AccessToken", required = true, dataType = "String", paramType = "header")})
    @ApiOperation(value = "Get IDCard scraping", notes = "IDCard scraping")
    @PostMapping(value = "/scraping")
    public SingleResult<IDCard> scraping(@RequestBody IDCard reqidCard) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = jwtTokenProvider.resolveToken(request);
        Long userno = Long.valueOf(jwtTokenProvider.getUserPk(token));

        IDCard idCard = IDCard.scrapingBuilder()
                .userNo(userno)
                .name("bigone")
                .birthday("920614")
                .idCardSeqNo("12-11111-233232").build();

        return responseService.getSingleResult(idCard);
    }
}
