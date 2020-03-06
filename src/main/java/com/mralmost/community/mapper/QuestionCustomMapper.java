package com.mralmost.community.mapper;

import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.model.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Lxj
 * @Package com.mralmost.community.mapper
 * @Description TODO QuestionMapper的拓展类
 * @date: 2020/3/5
 */
@Mapper
public interface QuestionCustomMapper {

    /**
     * 查询最热问题
     *
     * @return
     */
    List<Question> selectByHottestQuestion();

    /**
     * 查询最新问题
     *
     * @return
     */
    List<Question> selectByNewsetQuestion();

    /**
     * 查询所有问题,携带用户信息
     *
     * @param search 查询条件
     * @return
     */
    List<QuestionDTO> findQuestionWithUser(String search);

    /**
     * 累加阅读数和修改编辑时间
     *
     * @param question
     */
    void updateViewCountAndModifiedTime(Question question);

    /**
     * 根据标签查询问题
     *
     * @param question
     * @return
     */
    List<Question> selectRelated(Question question);

    /**
     * 累加问题回复数
     *
     * @param question
     * @return
     */
    int incQuestionCommentCount(Question question);

    /**
     * 查询该用户发布的所有问题
     *
     * @param userId
     * @return
     */
    List<QuestionDTO> findByCreator(Long userId);
}
