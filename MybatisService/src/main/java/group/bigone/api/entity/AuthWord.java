package group.bigone.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthWord {
    private Long userNo;

    private String authWord;

    private LocalDateTime regDate;
}
