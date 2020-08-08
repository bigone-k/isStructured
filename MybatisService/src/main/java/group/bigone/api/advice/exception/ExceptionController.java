package group.bigone.api.advice.exception;

import group.bigone.api.common.model.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

    @GetMapping(value = "/entrypoint" )
    public CommonResult entrypointException() {
        throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED);
    }

    @GetMapping(value = "/accessdenied" )
    public CommonResult accessdeniedException() {
        throw new AccessDeniedException("");
    }
}
