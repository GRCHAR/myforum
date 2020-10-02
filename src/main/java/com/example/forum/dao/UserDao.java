package com.example.forum.dao;

import com.example.forum.bo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

/**
 * @author genghaoran
 */

public interface UserDao {

    /**
     * 用户注册
     * @param user 添加的用户对象
     * @return 返回成功或失败代码
     */
    @Insert("INSERT INTO user(name, password) VALUES(#{name}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int registerUser(User user);

    /**
     * 通过用户ID获取用户
     * @param userId 用户ID
     * @return 用户对象
     */
    @Select("SELECT * FROM user WHERE id=#{userId}")
    public User getUser(int userId);

    /**
     * 通过用户名和密码查询用户
     * @param name 用户名
     * @param password 密码
     * @return 用户对象
     */
    @Select("SELECT * FROM user WHERE name=#{name} AND password=#{password}")
    public User getUserByNameAndPassword(String name, String password);


    /**
     * 用户更新
     * @param id 用户ID
     * @param name 用户姓名
     * @param password 用户密码
     * @return 返回结果代码
     */
    @UpdateProvider(type = UserSqlBuilder.class)
    public int updateUser(int id, String name, String password);


    class UserSqlBuilder implements ProviderMethodResolver {
        public String updateUser(int id, final String name, final String password){
            return new SQL(){{
                UPDATE("user");
                if(name != null){
                    SET("name = #{name}");
                }
                if(password != null){
                    SET("password = #{password}");
                }
                WHERE("id = #{id}");
            }
            }.toString();
        }
    }
}
