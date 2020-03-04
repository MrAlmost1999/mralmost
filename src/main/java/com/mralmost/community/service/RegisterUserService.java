package com.mralmost.community.service;

import com.mralmost.community.date.DateFormat;
import com.mralmost.community.dto.RegisterUserDTO;
import com.mralmost.community.mapper.RegisterUserMapper;
import com.mralmost.community.model.RegisterUser;
import com.mralmost.community.model.RegisterUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Lxj
 * @Package com.mralmost.community.service
 * @Description TODO 注册用户的service
 * @date: 2020/3/3
 */
@Service
public class RegisterUserService {

    @Autowired
    private RegisterUserMapper registerUserMapper;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    public RegisterUser login(String username, String password) {
        RegisterUserExample registerUserExample = new RegisterUserExample();
        registerUserExample.createCriteria()
                .andUsernameEqualTo(username)
                .andPasswordEqualTo(password);
        List<RegisterUser> registerUsers = registerUserMapper.selectByExample(registerUserExample);
        if (registerUsers.size() != 0) {
            return registerUsers.get(0);
        }
        return null;
    }

    /**
     * 根据标记查询用户信息
     *
     * @param username
     * @return
     */
    public RegisterUser findByUsername(String username) {
        RegisterUserExample example = new RegisterUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<RegisterUser> registerUsers = registerUserMapper.selectByExample(example);
        if (registerUsers.size() != 0) {
            return registerUsers.get(0);
        }
        return null;
    }

    public void register(RegisterUserDTO registerUserDTO) {
        RegisterUser registerUser = new RegisterUser();
        registerUser.setUsername(registerUserDTO.getUsername());
        registerUser.setPassword(registerUserDTO.getPassword());
        registerUser.setAvatar("default-avatar.png");
        registerUser.setCreateTime(DateFormat.dateFormat(new Date()));
        registerUser.setSign("这个人很懒,啥都没写~");
        registerUser.setEmail(registerUserDTO.getEmail());
        registerUserMapper.insertSelective(registerUser);
    }

    /**
     * 验证用户是否存在
     *
     * @param username
     * @return
     */
    public RegisterUser existUser(String username) {
        RegisterUserExample register = new RegisterUserExample();
        register.createCriteria().andUsernameEqualTo(username);
        List<RegisterUser> registerUsers = registerUserMapper.selectByExample(register);
        if (registerUsers.size() != 0) {
            return registerUsers.get(0);
        }
        return null;
    }

    /**
     * 修改用户的最后一次登录时间
     *
     * @param username
     */
    public void updateLastDate(String username) {
        RegisterUserExample register = new RegisterUserExample();
        register.createCriteria().andUsernameEqualTo(username);
        RegisterUser record = new RegisterUser();
        record.setLastDate(DateFormat.dateFormat(new Date()));
        registerUserMapper.updateByExampleSelective(record, register);
    }
}
