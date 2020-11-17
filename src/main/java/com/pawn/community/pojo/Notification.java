package com.pawn.community.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * description: 我的文章的评论表
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/6 21:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    /**
     * 该评论的id
     */
    private Integer id;
    /**
     * 评论文章的用户的标识符
     */
    private Integer notifier;
    /**
     * 接收评论的标识符
     */
    private Integer receiver;
    /**
     * 当前文章的id
     */
    private Integer outerId;
    /**
     * 区分回复类型
     */
    private Integer type;
    /**
     * 回复时间
     */
    private Long gmtCreate;
    /**
     * 判断回复是否查看（默认为0，表示未查看）
     */
    private Integer status;

    public Notification(Integer notifier, Integer receiver, Integer outerId, Integer type, Long gmtCreate, Integer status) {
        this.notifier = notifier;
        this.receiver = receiver;
        this.outerId = outerId;
        this.type = type;
        this.gmtCreate = gmtCreate;
        this.status = status;
    }
}
