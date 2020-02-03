package com.mralmost.community.controller;

import com.mralmost.community.dto.CommentDTO;
import com.mralmost.community.model.Comment;
import com.mralmost.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @RequestMapping(value = "/comment",method= RequestMethod.POST)
    public Object addComment(@RequestBody CommentDTO commentDTO){
//        Comment comment = new Comment();
//        comment.setParentId(commentDTO.getParentId());
//        comment.setType(commentDTO.getType());
//        comment.setContent(commentDTO.getContent());
//        comment.setGmtCreate(System.currentTimeMillis());
//        comment.setGmtModified(System.currentTimeMillis());
//        comment.setCommentator(1L);
//        commentService.insert(comment);
        return null;
    }
}
