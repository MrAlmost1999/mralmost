package com.mralmost.community.controller;

import com.mralmost.community.dto.CommentDTO;
import com.mralmost.community.dto.ResultDTO;
import com.mralmost.community.exception.CustomException;
import com.mralmost.community.exception.ErrorCode;
import com.mralmost.community.model.Comment;
import com.mralmost.community.model.User;
import com.mralmost.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lxj
 * @Package com.mralmost.community.controller
 * @Description TODO 评论controller
 * @date: 2020/1/30
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 发布回复
     *
     * @param commentDTO
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object addComment(@RequestBody CommentDTO commentDTO,
                             HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(ErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setContent(commentDTO.getContent());
        comment.setGmtCreate(new Date());
        comment.setCommentator(user.getId());
        commentService.insertSelective(comment);
        Map<Object, Object> hashMap = new HashMap<>();
        hashMap.put("message", "成功");
        return ResultDTO.okOf();
    }
}
