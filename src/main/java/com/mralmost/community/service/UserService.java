package com.mralmost.community.service;

import com.mralmost.community.date.DateFormat;
import com.mralmost.community.mapper.UserMapper;
import com.mralmost.community.model.User;
import com.mralmost.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Lxj
 * @Package com.mralmost.community.service
 * @Description TODO
 * @date: 2020/3/5
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByAccountId(String accountId) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(accountId);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() != 0) {
            return users.get(0);
        }
        return null;
    }

    /**
     * 创建或修改用户
     *
     * @param user
     */
    public void createOrUpdate(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(example);
        //用户数据为空时插入用户数据,不为空时更新用户数据
        if (users.size() == 0) {
            user.setSign("这个人很懒,什么都没留下~");
            user.setPublishType("github");
            user.setCreateTime(DateFormat.dateFormat(new Date()));
            userMapper.insert(user);
        } else {
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setCode(user.getCode());
            updateUser.setLastDate(DateFormat.dateFormat(new Date()));
            updateUser.setAvatar(user.getAvatar());
            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser, userExample);
        }
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andUsernameEqualTo(username)
                .andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() != 0) {
            return users.get(0);
        }
        return null;
    }

    /**
     * 修改用户的最后一次登录时间
     *
     * @param username
     */
    public void updateLastDate(String username) {
        UserExample register = new UserExample();
        register.createCriteria().andUsernameEqualTo(username);
        User user = new User();
        user.setLastDate(DateFormat.dateFormat(new Date()));
        userMapper.updateByExampleSelective(user, register);
    }

    /**
     * 注册用户
     * registerUserDTO
     *
     * @param
     */
    public void register(User user) {
        User registerUser = new User();
        registerUser.setUsername(user.getUsername());
        registerUser.setPassword(user.getPassword());
        registerUser.setAvatar("default-avatar.png");
        registerUser.setCreateTime(DateFormat.dateFormat(new Date()));
        registerUser.setSign("这个人很懒,什么都没留下~");
        registerUser.setEmail(user.getEmail());
        registerUser.setAccountId(UUID.randomUUID().toString());
        registerUser.setPublishType("register");
        userMapper.insertSelective(registerUser);
    }

    /**
     * 验证用户是否存在
     *
     * @param username
     * @return
     */
    public User existUser(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() != 0) {
            return users.get(0);
        }
        return null;
    }
}
