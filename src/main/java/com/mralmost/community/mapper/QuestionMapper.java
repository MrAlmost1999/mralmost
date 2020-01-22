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

    //发布问题
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void insert(Question question);

    //查询全部问题关联用户信息
    @Select("select * from question")
    @Results({
            @Result(property = "user", column = "creator",
                    one = @One(select = "com.mralmost.community.mapper.UserMapper.findById"))
    })
    List<QuestionDTO> findAll();

    //查询问题关联用户信息
    @Select("select * from question,user where question.creator=user.id and question.creator=#{id}")
    @Results({
            @Result(property = "user", column = "creator",
                    one = @One(select = "com.mralmost.community.mapper.UserMapper.findById"))
    })
    List<QuestionDTO> findByCreator(Integer id);

    //根据问题id查询用户信息
    @Select("select * from question where id=#{id}")
    @Results({
            @Result(property = "user", column = "creator",
                    one = @One(select = "com.mralmost.community.mapper.UserMapper.findById"))
    })
    QuestionDTO findById(Integer id);

    @Update("update question set title=#{title},description=#{description},tag=#{tag},gmt_modified=#{gmtModified} where id=#{id}")
    void update(Question question);
}
