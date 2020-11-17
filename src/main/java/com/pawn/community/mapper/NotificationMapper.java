package com.pawn.community.mapper;

import com.pawn.community.dto.NotificationDTO;
import com.pawn.community.pojo.Notification;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/***
 * description: 用户回复操作
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/7 15:51
 */
@Mapper
@Repository
public interface NotificationMapper {
    /**
     * 添加评论的状态
     *
     * @param notification
     * @return
     */
    @Insert("insert into notification(notifier,receiver,outer_id,type,gmt_create,status)" +
            "values (#{notifier},#{receiver},#{outerId},#{type},#{gmtCreate},#{status})")
    int addStatus(Notification notification);

    /**
     * 查询文章回复条数
     *
     * @param id
     * @return
     */
    @Select("select count(1) from notification  where receiver=#{id}")
    Integer inSumId(Integer id);

    /**
     * 查询回复提醒的数据
     *
     * @param map
     * @return
     */
    List<NotificationDTO> replyToReminder(Map<String, Integer> map);

    /**
     * 修改评论属性为已读
     *
     * @param id
     * @return
     */
    @Update("update notification set status=1 where outer_id=#{id}")
    Integer notViewed(Integer id);

    /**
     * 查询当前回复的状态
     *
     * @param id
     * @return
     */
    @Select("select status from notification where outer_id=#{id}")
    List<Notification> checkStatus(Integer id);

    /**
     * 查询为未查看的回复消息
     *
     * @param receiver
     * @return
     */
    @Select("select  count(1) from notification " +
            "where receiver=#{receiver} and status=0")
    Integer ViewNumber(Integer receiver);
}
