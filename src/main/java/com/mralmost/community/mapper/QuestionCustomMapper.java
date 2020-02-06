package com.mralmost.community.mapper;

import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.model.Question;

import java.util.Date;
import java.util.List;

/**
 * @author Lxj
 * @Package com.mralmost.community.model
 * @Description TODO QuestionMapper的拓展mapper
 * @date: 2020/2/3
 */
public interface QuestionCustomMapper {

    /**
     * 查询所有问题和相关作者信息
     *
     * @return
     */
    List<QuestionDTO> selectQuestionWithUser();

    /**
     * 查询所有问题和相关作者信息
     *
     * @return
     */
    List<QuestionDTO> selectQuestionWithUserByCreator(Long creator);

    /**
     * 修改阅读时间和阅读数
     *
     * @param question
     */
    int updateViewCountAndGmtModified(Question question);

    int incCommentCount(Question question);
}
