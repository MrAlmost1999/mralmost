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

    public List<QuestionDTO> findAll() {
        return questionMapper.findAll();
    }

    public List<QuestionDTO> findById(Integer id) {
        return questionMapper.findById(id);
    }

    public void insert(Question question){
        questionMapper.insert(question);
    }


}
