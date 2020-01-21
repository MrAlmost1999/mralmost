package com.mralmost.community.mapper;

import com.mralmost.community.model.User;
import org.apache.ibatis.annotations.*;

/**
 * @author Lxj
 * @Package com.mralmost.community.mapper
 * @Description TODO
 * @date: 2020/1/10
 */
@Mapper
public interface UserMapper {

    //插入用户信息,用于用户登录时插入用户信息
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    //根据token查询用户信息,用于拦截器生效时获取用户信息
    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    //根据id查询用户信息
    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer id);

    //根据account_Id查询用户信息,用于根据登录用户信息查询数据库中是否有该数据
    @Select("select * from user where account_id=#{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    //修改用户信息,用于用户登录时更新用户信息
    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where id=#{id}")
    void update(User user);

}
