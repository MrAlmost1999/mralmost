package com.mralmost.community.service;

import com.mralmost.community.date.DateFormat;
import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.mapper.QuestionCustomMapper;
import com.mralmost.community.mapper.QuestionMapper;
import com.mralmost.community.mapper.UserMapper;
import com.mralmost.community.model.Question;
import com.mralmost.community.model.Record;
import com.mralmost.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lxj
 * @Package com.mralmost.community.service
 * @Description TODO
 * @date: 2020/1/14
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionCustomMapper questionCustomMapper;

    @Autowired
    private RecordService recordService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有问题
     *
     * @return
     */
    public List<QuestionDTO> findQuestionWithUser(String search) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setSearch(search);
        List<QuestionDTO> questionDTOList = questionCustomMapper.findQuestionWithUser(questionDTO);
        return questionDTOList;
    }


    /**
     * id为主键自增长列,当id为null时,为插入操作,执行插入方法,不为null时为更新操作,执行更新方法
     *
     * @param question 插入或更新的信息
     */
    public void insertOrUpdate(Question question) {
        if (question.getId() == null) {
            question.setCreateTime(DateFormat.dateFormat(new Date()));
            question.setModifiedTime(question.getModifiedTime());
            questionMapper.insertSelective(question);
        } else {
            questionMapper.updateByPrimaryKeySelective(question);
        }
    }

    /**
     * 根据id查询问题
     *
     * @param id 问题主键id
     * @return
     */
    public QuestionDTO findById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    /**
     * 根据问题id累加阅读数
     *
     * @param record
     */
    public void updateViewCount(Record record) {
        //先查询该用户是否有浏览过此条记录,没有过则累加阅读数,有则修改浏览时间
        boolean flag = recordService.insertOrUpdate(record);
        if (!flag) {
            Question question = new Question();
            question.setModifiedTime(DateFormat.dateFormat(new Date()));
            question.setId(record.getQuestionId());
            questionCustomMapper.updateViewCountAndModifiedTime(question);
        }
    }

    /**
     * 根据标签查询相关的内容
     *
     * @param queryDTO
     * @return
     */
    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if (StringUtils.isBlank(queryDTO.getTag())) {
            return new ArrayList<>();
        }
        Question question = new Question();
        question.setId(queryDTO.getId());
        question.setTag(queryDTO.getTag().replace(",", "|"));
        List<Question> questions = questionCustomMapper.selectRelated(question);
        List<QuestionDTO> questionDTOList = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOList;
    }

    /**
     * 查询最热问题
     *
     * @return
     */
    public List<Question> selectByHottestQuestion() {
        return questionCustomMapper.selectByHottestQuestion();
    }

    /**
     * 查询最新问题
     *
     * @return
     */
    public List<Question> selectByNewsetQuestion() {
        return questionCustomMapper.selectByNewsetQuestion();
    }

    /**
     * 根据id删除问题
     *
     * @param id
     */
    public void deleteByPrimaryKey(Long id) {
        questionMapper.deleteByPrimaryKey(id);
    }

    public List<QuestionDTO> findByCreator(Long userId) {
        List<QuestionDTO> questionDTOList = questionCustomMapper.findByCreator(userId);
        return questionDTOList;
    }
}
