package config;

import mapper.QuoteMapper;
import mapper.UserMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public class MyBatisConfig {

    public SqlSessionFactory sqlSessionFactory() {
        try {
            // 使用 Resources 从类路径加载 mybatis-config.yaml 配置文件
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.yaml");
            Yaml yaml = new Yaml();
            Map<String, Object> config = yaml.load(inputStream);
            Map<String, String> databaseConfig = (Map<String, String>) config.get("database");

            // 配置数据源
            PooledDataSource dataSource = new PooledDataSource();
            dataSource.setDriver(databaseConfig.get("driver"));
            dataSource.setUrl(databaseConfig.get("url"));
            dataSource.setUsername(databaseConfig.get("username"));
            dataSource.setPassword(String.valueOf(databaseConfig.get("password")));

            // 创建 MyBatis 配置
            org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
            configuration.setEnvironment(new org.apache.ibatis.mapping.Environment("development", new org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory(), dataSource));
            configuration.setMapUnderscoreToCamelCase(true);
            configuration.setCacheEnabled(true);

            // 手动注册 Mapper 接口
            configuration.addMapper(UserMapper.class);
            configuration.addMapper(QuoteMapper.class);

            return new SqlSessionFactoryBuilder().build(configuration);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
