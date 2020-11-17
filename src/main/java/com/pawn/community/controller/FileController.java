package com.pawn.community.controller;

import com.pawn.community.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/***
 * description: 图片上传Controller
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/9 8:52
 */

@Controller
public class FileController {
    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request){
        FileDTO fileDTO=new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/images/zsh.jpg");
        return fileDTO;
    }
}
