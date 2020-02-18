package com.mralmost.community.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mralmost.community.dto.NotificationDTO;
import com.mralmost.community.enums.NotificationEnum;
import com.mralmost.community.mapper.NotificationMapper;
import com.mralmost.community.model.Notification;
import com.mralmost.community.model.NotificationExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lxj
 * @Package com.mralmost.community.service
 * @Description TODO
 * @date: 2020/2/18
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    /**
     * 查询当前用户所有通知
     *
     * @param userId 用户id
     * @return
     */
    public PageInfo findAll(Integer pageNum, Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId);
        //设置起始页码和每页最大显示数量
        List<Notification> notifications = notificationMapper.selectByExample(notificationExample);
        PageHelper.startPage(pageNum, 6);
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Notification n : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(n, notificationDTO);
            notificationDTO.setType(NotificationEnum.nameOfType(n.getType()));
            notificationDTOS.add(notificationDTO);
        }
        //设置连续显示的页数
        PageInfo<NotificationDTO> pageInfo = new PageInfo<NotificationDTO>(notificationDTOS, 5);
        return pageInfo;
    }
}
