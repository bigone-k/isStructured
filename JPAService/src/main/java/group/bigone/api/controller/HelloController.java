package group.bigone.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"HelloWord"})
@RestController
@RequestMapping(value = "/helloworld")
public class HelloController {

    @ApiOperation(value = "String", notes = "print String")
    @ResponseBody
    public String helloworldString() {
        return "helloworld";
    }

    @ApiOperation(value = "Json", notes = "print Json")
    @ResponseBody
    public Hello helloworldJson() {
        Hello hello = new Hello();
        hello.message = "helloworld";
        return hello;
    }

    @ApiOperation(value = "Page", notes = "print Page")
    @GetMapping(value = "/page")
    public String helloworld() {
        return "helloworld";
    }

    @Setter
    @Getter
    public static class Hello {
        private String message;
    }
}