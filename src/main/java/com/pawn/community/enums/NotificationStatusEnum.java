package com.pawn.community.enums;

/***
 * description:  * 是否查看状态判断枚举
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/7 10:12
 */
public enum NotificationStatusEnum {
    UNREAD(0), READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
