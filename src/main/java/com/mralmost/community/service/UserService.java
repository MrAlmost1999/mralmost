package com.mralmost.community.service;

import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.mapper.UserMapper;
import com.mralmost.community.model.Question;
import com.mralmost.community.model.User;
import com.mralmost.community.model.UserExample;
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
        UserExample example = new UserExample();
        example.createCriteria().andTokenEqualTo(token);
        List<User> users = userMapper.selectByExample(example);
        if(users.size()==0){
            return null;
        }
        return users.get(0);
    }

    public User findById(@Param("id") Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public void createOrUpdate(User user,
                               HttpServletResponse response) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        //用户数据为空时插入用户数据,不为空时更新用户数据
        if (users.size() == 0) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setId(dbUser.getId());
            updateUser.setName(dbUser.getName());
            updateUser.setToken(dbUser.getToken());
            updateUser.setGmtModified(dbUser.getGmtModified());
            updateUser.setAvatarUrl(dbUser.getAvatarUrl());
            response.addCookie(new Cookie("token", dbUser.getToken()));
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser, userExample);
        }
    }

}
