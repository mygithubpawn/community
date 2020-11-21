package com.pawn.community.service;

import com.pawn.community.pojo.Tag;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<Tag> get() {
        List<Tag> tagList = new ArrayList<>();
        Tag program = new Tag();
        program.setCategoryName("前端");
        program.setTags(Arrays.asList("javascript", "html5", "vue.js", "node.js", "go", "jquery", "css3", "es6", "typescript", "npm", "bootstrap", "sass", "less", "angular", "chrome-devtools", "postcss", "dge"));
        tagList.add(program);

        Tag framework = new Tag();
        framework.setCategoryName("后端");
        framework.setTags(Arrays.asList("php", "java", "python", "node.js","数据结构", "golang", "c++", "c#", "spring", "c++", "springboot", "django", "爬虫", "swoole", "rudy", "nap.net", "scala", "rust", "lavarel"));
        tagList.add(framework);

        Tag server = new Tag();
        server.setCategoryName("数据库");
        server.setTags(Arrays.asList("mysql", "redis", "sql", "mogodb", "json", "nosql", "dlasticserch  ", "mariadb"));
        tagList.add(server);

        Tag db = new Tag();
        db.setCategoryName("运维");
        db.setTags(Arrays.asList("linux", "nginx", "dockr", "apache", "centos", "ubuntu", "服务器  ", "负载均衡", "ssh", "vagrant", "jenkins"));
        tagList.add(db);

        Tag tool = new Tag();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git ", "github", "visual-studio-code", "vim", "phpstorm", "eclipse", "webstorm  ", "idea", "svn", "emacs"));
        tagList.add(tool);
        Tag other = new Tag();
        other.setCategoryName("其他");
        other.setTags(Arrays.asList("程序员 ", "http", "https", "安全", "区块链", "智能合约", "比特币  ", "文学", "诗词", "音乐", "电影", "美食", "旅游", "哲学", "名言"));
        tagList.add(other);
        return tagList;
    }

    public static String filterInvalid(String tags) {
        String[] spilt = StringUtils.split(tags, ",");
        List<Tag> tagList = new ArrayList<>();
        List<String> tagLists = tagList.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(spilt).filter(t -> !tagLists.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
