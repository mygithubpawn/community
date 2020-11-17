package com.pawn.community.service;

import com.pawn.community.mapper.AdMapper;
import com.pawn.community.pojo.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/***
 * description: 广告实现
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/12 14:06
 */

@Service
public class AdService {
    @Autowired
    AdMapper adMapper;

    public List<Ad> list() {
        List<Ad> store=new ArrayList<>();
        List<Ad> ads = adMapper.SelectAd(1);
        for (Ad ad : ads) {
            while (ad.getGmtModified()>System.currentTimeMillis()) {
                store=ads;
                break;
            }
//           if (ad.getGmtModified()>System.currentTimeMillis()){
//               store=ads;
//                break;
//           }else {
//               return null;
//           }
    }

        return store;
    }
}
