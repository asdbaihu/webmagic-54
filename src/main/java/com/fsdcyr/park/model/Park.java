package com.fsdcyr.park.model;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.monitor.SpiderMonitor;

import javax.management.JMException;

@TargetUrl("http://tiqiuren.com/stadium/\\w+")
@HelpUrl({"http://tiqiuren.com/*stadium*", "http://tiqiuren.com/*stadium?page=\\d+"})
@ExtractBy(value = "//div[@class='mainbody']")
public class Park {
    // 场地标题.
    @ExtractBy(value = "//div[@class='detailbox']/div[@class='detail_tt']/h1/text()", notNull = true)
    private String title;

    // 城市
    @ExtractBy(value = "//div[@class='info']/ol/li[1]/a[1]/text()")
    private String city;

    // 地区
    @ExtractBy(value = "//div[@class='info']/ol/li[1]/a[2]/text()")
    private String region;

    // 场地类型
    @ExtractBy(value = "//div[@class='info']/ol/li[2]/text()")
    private String placeType;

    // 球场灯光
    @ExtractBy(value = "//div[@class='info']/ol/li[3]/text()")
    private String light;

    // 草皮类型
    @ExtractBy(value = "//div[@class='info']/ol/li[4]/text()")
    private String turfType;

    // 草皮质量
    @ExtractBy(value = "//div[@class='info']/ol/li[5]/text()")
    private String turfQuality;

    // 地址
    @ExtractBy(value = "//div[@class='block-info']/dl[1]/dd/text()")
    private String address;

    // 附近交通
    @ExtractBy(value = "//div[@class='block-info']/dl[2]/dd/text()")
    private String traffic;

    // 开放时间
    @ExtractBy(value = "//div[@class='block-info']/dl[3]/dd/text()")
    private String openTime;

    // 收费信息
    @ExtractBy(value = "//div[@class='block-info']/dl[4]/dd/text()")
    private String chargeInfo;

    // 浏览次数
    @ExtractBy(value = "//div[@class='timeinfo']/span[2]/text()")
    private int view;

    // 收藏人数
    @ExtractBy(value = "//div[@class='info']/ul/li[1]/a/text()")
    private int love;


    public static void main(String[] args) throws JMException {
        Site site = Site
                .me()
                .setDomain("tiqiuren.com")
                .setSleepTime(3000)
                .setUserAgent(
                        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

        Spider parkSpider = OOSpider.create(site
                , new ConsolePageModelPipeline(), Park.class)
                .addUrl("http://tiqiuren.com/stadium")
                .thread(20);

        SpiderMonitor monitor = SpiderMonitor.instance();
        monitor.register(parkSpider);
        parkSpider.start();

    }
}
