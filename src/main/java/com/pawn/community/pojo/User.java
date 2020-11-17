package com.pawn.community.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: 用户表
 *
 * @param
 * @author:美茹冠玉
 * @Return
 * @date 2020/10/29 22:18
 */
@Data
@NoArgsConstructor
public class User {
    /**
     * id自增
     */
    private Integer id;
    /**
     * 唯一的标识符id
     */
    private String accountId;
    /**
     * `
     * github用户名
     */
    private String name;
    /**
     * 请求头
     */
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    /**
     * 头像
     */
    private String avatarUrl;

    public User(String accountId, String name, String token, Long gmtCreate, Long gmtModified, String avatarUrl) {
        this.accountId = accountId;
        this.name = name;
        this.token = token;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.avatarUrl = avatarUrl;
    }
}
