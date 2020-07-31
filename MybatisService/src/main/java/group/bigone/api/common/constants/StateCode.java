package group.bigone.api.common.constants;

import group.bigone.api.advice.exception.NoDataCodeException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum StateCode {
    WAIT(1),
    SUCCESS(2),
    TIMEOUT(3),
    FAILURE(4);

    private Short code;

    StateCode(Integer code) {
        this.code = code.shortValue();
    }

    public static StateCode findByStateCode(short stateCode) {
        return Arrays.stream(StateCode.values())
                .filter(group -> group.hasCode(stateCode))
                .findAny()
                .orElseThrow(() -> new NoDataCodeException());
    }

    public boolean hasCode(short stateCode) {
        return this.code.equals(stateCode);
    }
}
