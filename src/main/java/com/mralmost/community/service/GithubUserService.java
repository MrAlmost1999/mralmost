package com.mralmost.community.service;

import com.mralmost.community.date.DateFormat;
import com.mralmost.community.mapper.GithubUserMapper;
import com.mralmost.community.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Lxj
 * @Package com.mralmost.community.service
 * @Description TODO
 * @date: 2020/1/16
 */
@Service
public class GithubUserService {

    @Autowired
    private GithubUserMapper githubUserMapper;

    public void insert(GithubUser githubUser) {
        githubUserMapper.insert(githubUser);
    }

    public GithubUser findByToken(@Param("token") String token) {
        GithubUserExample example = new GithubUserExample();
        example.createCriteria().andTokenEqualTo(token);
        List<GithubUser> githubUsers = githubUserMapper.selectByExample(example);
        if (githubUsers.size() == 0) {
            return null;
        }
        return githubUsers.get(0);
    }

    public void createOrUpdate(GithubUser githubUser) {
        GithubUserExample example = new GithubUserExample();
        example.createCriteria().andAccountIdEqualTo(githubUser.getAccountId());
        List<GithubUser> githubUsers = githubUserMapper.selectByExample(example);
        //用户数据为空时插入用户数据,不为空时更新用户数据
        if (githubUsers.size() == 0) {
            githubUser.setCreateTime(DateFormat.dateFormat(new Date()));
            githubUser.setModifiedTime(githubUser.getCreateTime());
            githubUserMapper.insert(githubUser);
        } else {
            GithubUser dbUser = githubUsers.get(0);
            GithubUser updateUser = new GithubUser();
            updateUser.setUsername(githubUser.getUsername());
            updateUser.setToken(githubUser.getToken());
            updateUser.setModifiedTime(githubUser.getCreateTime());
            updateUser.setAvatar(githubUser.getAvatar());
            GithubUserExample githubUserExample = new GithubUserExample();
            example.createCriteria().andIdEqualTo(dbUser.getId());
            githubUserMapper.updateByExampleSelective(updateUser, githubUserExample);
        }
    }

}
