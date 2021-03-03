package group.bigone.api.global.config.database.h2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class H2DBConfig {
    private String port;
    private String dbName;
    private String userName;
    private String password;
    private String scriptSchema;
    private String scriptData;

    private String charSet;
    private String dbMode;

    public String createH2ConnectionToString()
    {
        return new StringBuilder()
                .append("jdbc:h2:tcp://localhost:").append(this.port)
                .append("/").append(this.dbName)
                .append(";MODE=").append(this.dbMode).append(";")
                .toString();
    }
}

