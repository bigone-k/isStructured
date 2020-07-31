package group.bigone.api.model.social;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoProfile {
    private Long id;
    private Properties properties;

    @Getter
    @Setter
    @ToString
    private  static  class Properties {
        private String nickname;
        private String thumbnail_iamge;
        private String profile_image;
    }
}
