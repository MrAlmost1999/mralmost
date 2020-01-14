package com.mralmost.community.dto;

import com.mralmost.community.model.User;
import lombok.Data;

/**
 * @author Lxj
 * @Package com.mralmost.community.dto
 * @Description TODO
 * @date: 2020/1/14
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
