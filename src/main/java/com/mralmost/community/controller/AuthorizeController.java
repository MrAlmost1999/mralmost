package com.mralmost.community.controller;

import com.mralmost.community.Model.User;
import com.mralmost.community.dto.AccessTokenDTO;
import com.mralmost.community.mapper.UserMapper;
import com.mralmost.community.provider.GithubProvider;
import com.mralmost.community.provider.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author Lxj
 * @Package com.mralmost.controller
 * @Description TODO
 * @date: 2020/1/9
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = gitHubProvider.getUser(accessToken);
        if (githubUser != null) {
            User user = new User();
            user.setName(githubUser.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setCreateDate(System.currentTimeMillis());
            user.setModifiedDate(user.getCreateDate());
            userMapper.insert(user);
            //登录成功
            request.getSession().setAttribute("user", githubUser);
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }
    }
}
