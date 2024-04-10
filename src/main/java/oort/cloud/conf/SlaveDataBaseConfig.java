package oort.cloud.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan(value="oort.cloud.mapper.slave", sqlSessionFactoryRef="slaveSqlSessionFactory")
@EnableTransactionManagement
public class SlaveDataBaseConfig {

    @Bean(name="slaveDataSource")
    @ConfigurationProperties(prefix="spring.slave.datasource")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="slaveSqlSessionFactory")
    public SqlSessionFactory slave1SqlSessionFactory(@Qualifier("slaveDataSource") DataSource slaveDataSource, ApplicationContext applicationContext) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(slaveDataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mappers/slave/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name="slaveSqlSessionTemplate")
    public SqlSessionTemplate slave1SqlSessionTemplate(SqlSessionFactory slaveSqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(slaveSqlSessionFactory);
    }

}
