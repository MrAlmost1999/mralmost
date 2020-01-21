package com.mralmost.community.service;

import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.mapper.UserMapper;
import com.mralmost.community.model.Question;
import com.mralmost.community.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lxj
 * @Package com.mralmost.community.service
 * @Description TODO
 * @date: 2020/1/16
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void insert(User user) {
        userMapper.insert(user);
    }

    public User findByToken(@Param("token") String token) {
        return userMapper.findByToken(token);
    }

    public User findById(@Param("id") Integer id) {
        return userMapper.findById(id);
    }

    public void createOrUpdate(User user,
                               HttpServletResponse response){
        User byAccountId = userMapper.findByAccountId(user.getAccountId());
        //用户数据为空时插入用户数据,不为空时更新用户数据
        if(byAccountId==null){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            user.setId(byAccountId.getId());
            user.setName(byAccountId.getName());
            user.setToken(byAccountId.getToken());
            user.setGmtModified(byAccountId.getGmtModified());
            user.setAvatarUrl(byAccountId.getAvatarUrl());
            response.addCookie(new Cookie("token",user.getToken()));
            userMapper.update(user);
        }
    }

}
