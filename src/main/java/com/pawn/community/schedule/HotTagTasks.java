package com.pawn.community.schedule;

import com.pawn.community.mapper.ArticleMapper;
import com.pawn.community.pojo.Article;
import com.pawn.community.pojo.Details;
import com.pawn.community.service.DetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/***
 * description: 定时任务
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/10 23:14
 */
@Component
@Slf4j
public class HotTagTasks {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    DetailsServiceImpl detailsService;
    @Autowired
    HotTagCache hotTagCache;

    @Scheduled(cron  = "0/2 * * * * ?" )
//    @Scheduled(cron  = "0 0 12 ? * WED " )
    public void reportCurrentTime() {
        int offset = 0;
        int limit = 5;
//        log.info("The time is now {}", new Date());
        Map<String, Integer> priorities = new HashMap();
        List<Article> list = new ArrayList();
        List<Article> articles = articleMapper.AllTags();
        while (offset == 0 || list.size() == limit) {
            for (Article article : articles) {
                List<Details> details = detailsService.PopularData(article.getId());
                String[] tags = StringUtils.split(article.getTag(), ",");
                for (Details detail : details) {
                    Integer detailsCount = detail.getReplyCount();
                    for (String tag : tags) {
                        Integer priority = priorities.get(tag);
                        if (priority != null) {
                            priorities.put(tag, priority + 5 + detailsCount);
                        } else {
                            priorities.put(tag, 5 + 20);
                        }
                    }
                }
            }
            offset += limit;
        }
        hotTagCache.updateTags(priorities);
    }
}
