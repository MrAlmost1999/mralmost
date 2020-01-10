package com.mralmost.community.mapper;

import com.mralmost.community.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Lxj
 * @Package com.mralmost.community.mapper
 * @Description TODO
 * @date: 2020/1/10
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(name,account_id,token,create_date,modified_date) values(#{name},#{accountId},#{token},#{createDate},#{modifiedDate})")
    void insert(User user);

    @Select("select name,token from user where token=#{token}")
    User findByToken(@Param("token") String token);
}
