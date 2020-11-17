package com.pawn.community.dto;

import com.pawn.community.pojo.User;
import lombok.Data;

@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_urt;
    private String state;
    private User user;
}
