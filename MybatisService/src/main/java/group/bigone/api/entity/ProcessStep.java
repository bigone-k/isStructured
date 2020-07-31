package group.bigone.api.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessStep {
    private Long seqNo;

    private Long userNo;

    private short stepType;

    private short stateCode;

    private LocalDateTime regDate;

    // UpdateBuilder
    @Builder(builderClassName = "UpdateBuilder", builderMethodName = "UpdateBuilder")
    public ProcessStep(Long seqNo, short stateCode) {
        this.seqNo = seqNo;
        this.stateCode = stateCode;
    }

    // InsertBuilder
    @Builder(builderClassName = "InsertBuilder", builderMethodName = "InsertBuilder")
    public ProcessStep(Long userNo, short stepType, short stateCode) {
        this.userNo = userNo;
        this.stepType = stepType;
        this.stateCode = stateCode;
    }
}

