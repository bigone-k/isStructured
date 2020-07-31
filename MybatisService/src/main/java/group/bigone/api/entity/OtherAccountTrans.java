package group.bigone.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OtherAccountTrans {
    private String accountName;

    private String accountNum;

    private String bankCode;

    private short stateCode;

    private LocalDateTime regDate;
}
