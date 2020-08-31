package group.bigone.api.global.error.controller;

import group.bigone.api.global.error.ErrorResponse;
import group.bigone.api.global.error.exception.CustomAuthenticationEntryPointException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

    @GetMapping(value = "/authenticationentrypoint" )
    public ResponseEntity<ErrorResponse> authenticationEntryPointException() {
        throw new CustomAuthenticationEntryPointException();
    }

    @GetMapping(value = "/accessdenied" )
    public ResponseEntity<ErrorResponse> accessdeniedException() {
        throw new AccessDeniedException("");
    }
}
