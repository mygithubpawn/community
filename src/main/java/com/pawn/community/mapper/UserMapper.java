package com.pawn.community.mapper;

import com.pawn.community.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * description:  * 用户信息
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/6 15:59
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 添加用户
     *
     * @param user
     */
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified,avatar_url) " +
            "values(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    /**
     * 查询是否以存在当前请求用户的token（）
     *
     * @param token
     * @return
     */
    @Select("select * from user where token=#{token}")
    User finByToken(@Param("token") String token);

    /**
     * 查询数据库是否存在该请求用户的唯一id（防止数据重复）
     *
     * @param accountId
     * @return
     */
    @Select("select * from user where account_id=#{accountId}")
    User FindById(@Param("accountId") String accountId);

    /**
     * 查询所有的用户（用于判断用户状态）
     *
     * @return
     */
    @Select("select account_id from user")
    List<User> findUserId();

    /**
     * 更新已有数据的用户再次登陆的时间
     *
     * @param user
     */
    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl}" +
            "where id=#{id}")
    void UserUpdate(User user);
}
