package group.bigone.api.common.constants;

import group.bigone.api.advice.exception.NoDataCodeException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProcessStepCode {
    IDCARD(1),
    TRANSFER(2),
    CERTIFIEDWORD(3),
    ELSE(0);

    private Short code;

    ProcessStepCode(Integer code) {
        this.code = code.shortValue();
    }

    public static ProcessStepCode findByProcessStepCode(short stepCode) {
        return Arrays.stream(ProcessStepCode.values())
                .filter(group -> group.hasCode(stepCode))
                .findAny()
                .orElseThrow(()->new NoDataCodeException());
    }

    public boolean hasCode(short stepCode) {
        return this.code.equals(stepCode);
    }
}
