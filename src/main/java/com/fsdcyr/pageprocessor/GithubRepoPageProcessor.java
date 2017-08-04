package com.fsdcyr.pageprocessor;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.management.JMException;

@Slf4j
public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public void process(Page page) {
        System.out.println(page.getHtml().links().all());
        log.info("{}", page.getHtml().links().all());
        log.info("dasdasdasda");

        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name") == null){
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws JMException {
        Spider spider = Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://github.com/code4craft")
                .addPipeline(new JsonFilePipeline("D:\\webmagic"))
                .thread(5);
        SpiderMonitor.instance().register(spider);
        spider.start();
    }
}