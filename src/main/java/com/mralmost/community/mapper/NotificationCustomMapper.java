package com.mralmost.community.mapper;

import com.mralmost.community.dto.NotificationDTO;

import java.util.List;

public interface NotificationCustomMapper {

    /**
     * 根据用户查询通知
     *
     * @param userId 用户id
     * @return
     */
    List<NotificationDTO> selectByUser(Long userId);
}