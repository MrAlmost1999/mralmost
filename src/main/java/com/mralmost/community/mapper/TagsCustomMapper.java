package com.mralmost.community.mapper;

import com.mralmost.community.model.Tags;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Lxj
 * @Package com.mralmost.community.mapper
 * @Description TODO
 * @date: 2020/2/17
 */
public interface TagsCustomMapper {
    List<Tags> findTags();
}
