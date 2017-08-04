package com.fsdcyr.park.model;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.Formatter;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl("http://tiqiuren.com/*stadium/*")
@ExtractBy(value = "//div[@class='mainbody']", multi = true)
public class Park {

    // 场地标题
    @ExtractBy(value = "//div[@class='singleinfo']/dl/dt/a[@class='side_name']/@title", notNull = true)
    private String title;

    // 地区
    @ExtractBy(value = "//div[@class='singleinfo']/dl/dd")
    private Location location;

    // 地址
    private String address;

    // 场地类型
    private String placeType;

    // 草皮类型
    private String turfType;

    // 草皮质量
    private String turfQuality;

    // 球场灯光
    private String light;

    // 浏览次数
    private int view;

    // 收藏人数
    private int love;

    // 回复人数
    private int reply;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000)
                , new ConsolePageModelPipeline(), Park.class)
                .addUrl("http://tiqiuren.com/stadium")
                .thread(5)
                .run();
    }
}
