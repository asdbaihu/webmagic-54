package com.fsdcyr.park.pageprocessor;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;


@Slf4j
public class ParkPageProcessor implements PageProcessor {

    private static final String URL_SELECT = "http://tiqiuren\\.com/.*stadium.*";

    private static final String URL_INFO = "http://tiqiuren\\.com/stadium/\\w+";

    private static final String URL_PAGE = "http://tiqiuren\\.com/.*stadium\\?page=\\d+";

    private Site site = Site
            .me()
            .setDomain("tiqiuren.com")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public void process(Page page) {
        if (page.getUrl().regex(URL_INFO).match()) {
            System.out.println("info url");
            page.putField("title", page.getHtml().xpath("//div[@class='detailbox']/div[@class='detail_tt']/h1/text()"));

        } else {

            // 添加分页链接
            List<String> pageRequest = page.getHtml().links().regex(URL_PAGE).all();
            page.addTargetRequests(pageRequest);

            // 添加筛选链接
            List<String> selectRequest = page.getHtml().links().regex(URL_SELECT).all();
            page.addTargetRequests(selectRequest);
        }
    }

    public Site getSite() {
        return site;
    }
}
