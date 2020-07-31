package group.bigone.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PreAgree {
    private Long userNo;

    private Boolean agree;

    private String accountPassword;
}
