package group.bigone.api.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class CommonDateEntity {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}