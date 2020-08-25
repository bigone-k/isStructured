package group.bigone.api.global.common.constants;

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
                .orElse(WAIT);
    }

    public boolean hasCode(short stateCode) {
        return this.code.equals(stateCode);
    }
}
