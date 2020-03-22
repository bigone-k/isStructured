package group.bigone.api.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ParamsPost {
    @NotEmpty
    @Size(min=2, max=50)
    @ApiModelProperty(value = "author", required = true)
    private String author;

    @NotEmpty
    @Size(min=2, max=100)
    @ApiModelProperty(value = "title", required = true)
    private String title;

    @Size(min=2, max=500)
    @ApiModelProperty(value = "content", required = true)
    private String content;
}
