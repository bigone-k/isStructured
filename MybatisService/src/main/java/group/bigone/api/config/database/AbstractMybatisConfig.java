package group.bigone.api.config.database;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

public abstract class AbstractMybatisConfig {
    @Value("${mybatis.config-location}")
    private String mybatisConfigLocation;

    @Value("${mybatis.mapper-locations}")
    private String mybatisMapperLocation;

    public abstract SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception;
    public abstract SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) throws Exception;

    protected void sqlSessionFactoryConfig(SqlSessionFactoryBean sqlSessionFactoryBean, DataSource dataSource) throws IOException {
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(patternResolver.getResource(mybatisConfigLocation));
        sqlSessionFactoryBean.setMapperLocations(patternResolver.getResources(mybatisMapperLocation));
        sqlSessionFactoryBean.setTypeAliasesPackage("group.bigone.api");
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
    }
}
