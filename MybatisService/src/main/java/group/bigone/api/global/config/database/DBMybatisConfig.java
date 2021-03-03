package group.bigone.api.global.config.database;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBMybatisConfig extends AbstractMybatisConfig {
    @Override
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean objSqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryConfig(objSqlSessionFactoryBean, dataSource);

        return objSqlSessionFactoryBean.getObject();
    }

    @Override
    @Bean
    public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
