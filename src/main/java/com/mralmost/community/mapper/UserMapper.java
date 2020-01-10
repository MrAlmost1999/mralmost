package com.mralmost.community.mapper;

import com.mralmost.community.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}
