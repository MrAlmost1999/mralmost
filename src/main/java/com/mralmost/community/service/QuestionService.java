package com.mralmost.community.service;

import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.mapper.QuestionMapper;
import com.mralmost.community.mapper.UserMapper;
import com.mralmost.community.model.Question;
import com.mralmost.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    private UserMapper userMapper;

    public List<QuestionDTO> findAll() {
        List<Question> questions = questionMapper.findAll();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
