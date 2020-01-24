package com.mralmost.community.mapper;

import com.mralmost.community.model.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * @author Lxj
 * @Package com.mralmost.community.mapper
 * @Description TODO 用户浏览记录mapper
 * @date: 2020/1/23
 */
@Mapper
public interface RecordMapper {
    /**
     * 添加浏览记录
     *
     * @param record
     */
    @Insert("insert into record(user_id,question_id,record_date) values(#{userId},#{questionId},#{recordDate})")
    void insert(Record record);

    /**
     * 根据用户id和问题id查询是否有该记录
     *
     * @param record
     */
    @Select("select * from record where user_id=#{userId} and question_id=#{questionId}")
    Record select(Record record);

    /**
     * 有浏览记录则修改浏览时间
     *
     * @param record
     */
    @Update("update record set record_date=#{recordDate} where user_id=#{userId} and question_id=#{questionId}")
    void update(Record record);
}
