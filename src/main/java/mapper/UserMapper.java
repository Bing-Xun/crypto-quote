package mapper;

import entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users")
    List<User> getAllUsers();

    // 插入用户
    @Insert("INSERT INTO users(name, email) VALUES(#{name}, #{email})")
    void insertUser(User user);

    // 删除用户
    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteUser(@Param("id") int id);

}

