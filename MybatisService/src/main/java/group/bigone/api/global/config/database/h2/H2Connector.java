package group.bigone.api.global.config.database.h2;

import org.h2.tools.RunScript;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;


public class H2Connector {
    private static final Logger logger = LoggerFactory.getLogger(H2Connector.class);
    private H2DBConfig h2DBConfig;

    public H2Connector(H2DBConfig h2DBConfig){
        this.h2DBConfig = h2DBConfig;
    }

    public void RunServer() {
        Server server   = null;

        try {
            if( IsConnect() )
                return;

            server = Server.createTcpServer("-tcpAllowOthers","-tcp","-ifNotExists"
                                            ,"-tcpPort",h2DBConfig.getPort()
                                            ,"-key",h2DBConfig.getDbName(),"./" + h2DBConfig.getDbName()
                                            ,"-browser").start();
            logger.info(server.getStatus());

            // Schema Script 실행
            if(Optional.ofNullable(h2DBConfig.getScriptSchema()).isPresent())
                h2RunScript(new ClassPathResource(h2DBConfig.getScriptSchema()));
            // Data Script 실행
            if(Optional.ofNullable(h2DBConfig.getScriptData()).isPresent())
                h2RunScript(new ClassPathResource(h2DBConfig.getScriptData()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void h2RunScript(Resource resource) throws IOException, SQLException
    {
        RunScript.execute(h2DBConfig.createH2ConnectionToString()
                         ,h2DBConfig.getUserName(),h2DBConfig.getPassword()
                         ,resource.getURL().getPath(),Charset.forName(h2DBConfig.getCharSet()),true);
    }

    private boolean IsConnect() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn;
        try {
            conn = DriverManager.getConnection(h2DBConfig.createH2ConnectionToString(), h2DBConfig.getUserName(), h2DBConfig.getPassword());
        } catch (SQLException exception){
            return false;
        }

        if(conn.isClosed())
            return false;

        return true;
    }
}
