package com.mralmost.community.dto;

import lombok.Data;

/**
 * @author Lxj
 * @Package com.mralmost.community.dto
 * @Description TODO 文件上传的DTO
 * @date: 2020/2/23
 */
@Data
public class FileDTO {
    /**
     * 0 表示上传失败，1 表示上传成功
     */
    private int success;
    /**
     * 提示的信息，上传成功或上传失败及错误信息等
     */
    private String message;
    /**
     * 图片地址,上传成功时才返回
     */
    private String url;
}
