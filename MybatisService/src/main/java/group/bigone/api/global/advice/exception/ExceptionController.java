package group.bigone.api.global.advice.exception;

import group.bigone.api.global.common.model.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

    @GetMapping(value = "/authenticationentrypoint" )
    public CommonResult authenticationEntryPointException() {
        throw new CustomAuthenticationEntryPointException();
    }

    @GetMapping(value = "/accessdenied" )
    public CommonResult accessdeniedException() {
        throw new AccessDeniedException("");
    }
}
