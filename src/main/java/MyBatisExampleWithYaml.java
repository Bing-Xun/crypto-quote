import config.MyBatisConfig;
import entity.User;

import java.util.List;

import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class MyBatisExampleWithYaml {

    public static void main(String[] args) {
        // 使用 Java 配置创建 SqlSessionFactory
        MyBatisConfig myBatisConfig = new MyBatisConfig();
        SqlSessionFactory sqlSessionFactory = myBatisConfig.sqlSessionFactory();

        // 
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);

            // 插入用户
            User newUser = new User();
            newUser.setName("John Doe");
            newUser.setEmail("john.doe@example.com");
            mapper.insertUser(newUser);
            session.commit();
            System.out.println("User inserted!");

            // 获取所有用户并打印
            List<User> users = mapper.getAllUsers();
            for (User user : users) {
                System.out.println(user);
            }

            // 删除用户（假设我们删除 ID 为 1 的用户）
//            mapper.deleteUser(1);
//            session.commit();
//            System.out.println("User deleted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
