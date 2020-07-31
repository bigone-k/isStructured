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

    @Override
    public String toString() {
        return "RetKakaoAuth{" +
                "access_token='" + access_token + '\'' +
                ", access_type='" + access_type + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", expires_in=" + expires_in +
                ", scope='" + scope + '\'' +
                ", refresh_token_expires_in=" + refresh_token_expires_in +
                '}';
    }
}
