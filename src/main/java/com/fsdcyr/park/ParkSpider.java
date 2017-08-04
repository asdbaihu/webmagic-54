package com.fsdcyr.park;

import com.fsdcyr.park.pageprocessor.ParkPageProcessor;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;

import javax.management.JMException;

public class ParkSpider {
    public static void main(String[] args) throws JMException {
        Spider spider = Spider.create(new ParkPageProcessor())
                .addUrl("http://tiqiuren.com/stadium")
//                .addPipeline(new FilePipeline("D:\\webmagic"))
                .thread(20);
        SpiderMonitor monitor = SpiderMonitor.instance();
        monitor.register(spider);
        spider.start();
    }
}
