package com.mralmost.community.mapper;

import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Lxj
 * @Package com.mralmost.community.mapper
 * @Description TODO
 * @date: 2020/1/12
 */
@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void insert(Question question);

    @Select("select * from question")
    @Results({
            @Result(property = "user", column = "creator",
                    one = @One(select = "com.mralmost.community.mapper.UserMapper.findById"))
    })
    List<QuestionDTO> findAll();

    @Select("select * from question,user where question.creator=user.id and question.creator=#{id}")
    @Results({
            @Result(property = "user", column = "creator",
                    one = @One(select = "com.mralmost.community.mapper.UserMapper.findById"))
    })
    List<QuestionDTO> findByCreator(Integer id);

    @Select("select * from question where id=#{id}")
    @Results({
            @Result(property = "user", column = "creator",
                    one = @One(select = "com.mralmost.community.mapper.UserMapper.findById"))
    })
    QuestionDTO findById(Integer id);
}
