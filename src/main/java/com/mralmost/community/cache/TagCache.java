package com.mralmost.community.cache;

import com.mralmost.community.dto.TagDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lxj
 * @Package com.mralmost.community.cache
 * @Description TODO 简陋的标签库
 * @date: 2020/2/18
 */
public class TagCache {

    /**
     * 获取标签
     *
     * @return
     */
    public static List<TagDTO> getTags() {
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("java", "javascript", "C", "C#", "C++", "python", "php", "vue.js", "css", "html5", "node.js", "jquery", "css3", "go", ".net"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("spring", "springboot", "mybatis", "springmvc", "hibernate", "struts2", "springcloud", "vue", "bootstrap"));
        tagDTOS.add(framework);

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux", "nginx", "tomcat", "unix", "apache", "负载均衡"));
        tagDTOS.add(server);

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql", "redis", "oracle", "sqlserver", "mongodb", "nosql memcached"));
        tagDTOS.add(db);

        TagDTO develop = new TagDTO();
        develop.setCategoryName("开发工具");
        develop.setTags(Arrays.asList("git", "github", "maven", "IDEA", "eclipse", "HBuilder", "myeclipse", "postman"));
        tagDTOS.add(develop);

        TagDTO rests = new TagDTO();
        rests.setCategoryName("其他");
        rests.setTags(Arrays.asList("其他"));
        tagDTOS.add(rests);

        return tagDTOS;
    }

}
