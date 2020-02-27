package com.mralmost.community.controller;

import com.mralmost.community.date.DateFormat;
import com.mralmost.community.dto.CommentReceiveDTO;
import com.mralmost.community.dto.CommentReturnDTO;
import com.mralmost.community.dto.ResultDTO;
import com.mralmost.community.enums.CommentTypeEnum;
import com.mralmost.community.exception.ErrorCode;
import com.mralmost.community.model.Comment;
import com.mralmost.community.model.User;
import com.mralmost.community.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author Lxj
 * @Package com.mralmost.community.controller
 * @Description TODO 评论controller
 * @date: 2020/1/30
 */
@Controller
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 发布回复
     *
     * @param commentReceiveDTO 发布回复的DTO模型
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/comment")
    public Object addComment(@RequestBody CommentReceiveDTO commentReceiveDTO,
                             HttpServletRequest request) {
        //当用户未登录时提示用户登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(ErrorCode.NO_LOGIN);
        }
        //评论判空处理
        if (commentReceiveDTO == null || StringUtils.isBlank(commentReceiveDTO.getContent())) {
            return ResultDTO.errorOf(ErrorCode.COMMENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentReceiveDTO.getParentId());
        comment.setType(commentReceiveDTO.getType());
        comment.setContent(commentReceiveDTO.getContent());
        comment.setGmtCreate(DateFormat.dateFormat(new Date()));
        comment.setCommentator(user.getId());
        //插入回复
        commentService.insertSelective(comment, user);
        return ResultDTO.okOf();
    }

    /**
     * 删除回复
     *
     * @param commentId 回复id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/comment")
    public Object delComment(String commentId) {
        commentService.deleteComment(Long.valueOf(commentId));
        return ResultDTO.okOf();
    }

    /**
     * 获取所有二级回复
     *
     * @param id 二级回复id
     * @return
     */
    @ResponseBody
    @GetMapping("/comment/{id}")
    public ResultDTO<List<CommentReturnDTO>> getComments(@PathVariable(name = "id") String id) {
        List<CommentReturnDTO> commentReturnDTOList = commentService.listByTargetId(Long.valueOf(id), CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentReturnDTOList);
    }

}
