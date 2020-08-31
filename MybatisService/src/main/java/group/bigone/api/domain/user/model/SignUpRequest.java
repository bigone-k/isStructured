package group.bigone.api.domain.user.model;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
public class SignUpRequest {
    @Email
    @NotEmpty
    @ApiParam(value = "email")
    private String userId;

    @NotEmpty
    @ApiParam(value = "passWord")
    private String passWord;

    @NotEmpty
    @ApiParam(value = "name")
    private String name;
}
