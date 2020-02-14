package com.mralmost.community.controller;

import com.mralmost.community.dto.CommentReceiveDTO;
import com.mralmost.community.dto.CommentReturnDTO;
import com.mralmost.community.dto.ResultDTO;
import com.mralmost.community.enums.CommentTypeEnum;
import com.mralmost.community.exception.ErrorCode;
import com.mralmost.community.model.Comment;
import com.mralmost.community.model.User;
import com.mralmost.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
     * @param commentReceiveDTO
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/comment")
    public Object addComment(@RequestBody CommentReceiveDTO commentReceiveDTO,
                             HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(ErrorCode.NO_LOGIN);
        }
        if (commentReceiveDTO == null || StringUtils.isBlank(commentReceiveDTO.getContent())) {
            return ResultDTO.errorOf(ErrorCode.COMMENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentReceiveDTO.getParentId());
        comment.setType(commentReceiveDTO.getType());
        comment.setContent(commentReceiveDTO.getContent());
        comment.setGmtCreate(new Date());
        comment.setCommentator(user.getId());
        commentService.insertSelective(comment);
        Map<Object, Object> hashMap = new HashMap<>();
        hashMap.put("message", "成功");
        return ResultDTO.okOf();
    }

    @ResponseBody
    @DeleteMapping("/comment")
    public Object delComment(String commentId) {
        commentService.deleteComment(Long.valueOf(commentId));
        return ResultDTO.okOf();
    }

    @ResponseBody
    @GetMapping("/comment/{id}")
    public ResultDTO<List<CommentReturnDTO>> getComments(@PathVariable(name = "id") String id,
                                                         Model model) {
        List<CommentReturnDTO> commentReturnDTOList = commentService.listByTargetId(Long.valueOf(id), CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentReturnDTOList);
    }
}
