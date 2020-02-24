package group.bigone.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResult {
    @ApiModelProperty(value = "Response success or failure : true/false")
    private  boolean success;

    @ApiModelProperty(value = "Response Code : o success")
    private  int ResultCode;

    @ApiModelProperty(value = "Response Message")
    private  String ResultMessage;
}
