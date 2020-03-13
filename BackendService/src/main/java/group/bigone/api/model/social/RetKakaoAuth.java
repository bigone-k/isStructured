package group.bigone.api.model.social;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetKakaoAuth {
    private  String access_token;
    private  String access_type;
    private  String refresh_token;
    private  long expires_in;
    private  String scope;
    private  long refresh_token_expires_in;
}
