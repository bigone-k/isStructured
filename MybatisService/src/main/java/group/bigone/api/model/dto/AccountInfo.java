package group.bigone.api.model.dto;

import group.bigone.api.entity.IDCard;
import group.bigone.api.entity.PreAgree;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfo {

    private PreAgree preAgree;

    private IDCard idCard;

}
