package group.bigone.api.config.database;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBMybatisConfig extends AbstractMybatisConfig {
    private static final Logger logger = LoggerFactory.getLogger(DBMybatisConfig.class);

    @Override
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean objSqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryConfig(objSqlSessionFactoryBean, dataSource);

        if (logger.isDebugEnabled()) {
            logger.debug(objSqlSessionFactoryBean.toString());
        }

        return objSqlSessionFactoryBean.getObject();
    }

    @Override
    @Bean
    public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
