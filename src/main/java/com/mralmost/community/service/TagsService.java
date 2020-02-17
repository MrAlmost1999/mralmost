package com.mralmost.community.service;

import com.mralmost.community.mapper.TagsCustomMapper;
import com.mralmost.community.mapper.TagsMapper;
import com.mralmost.community.model.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lxj
 * @Package com.mralmost.community.service
 * @Description TODO 标签的service
 * @date: 2020/2/17
 */
@Service
public class TagsService {

    @Autowired
    private TagsCustomMapper tagsCustomMapper;

    public List<Tags> findTags() {
        TagsCustomMapper tagsCustomMapper = this.tagsCustomMapper;
        return tagsCustomMapper.findTags();
    }
}
