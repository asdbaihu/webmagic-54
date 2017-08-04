package com.fsdcyr.park.extract;

import us.codecraft.webmagic.Page;

public class ParkPageExtractor {

    public static void extrat(Page page) {

        String title = page.getHtml().xpath("//div[@class='detailbox']/div[@class='detail_tt']/h1/text()").toString();
        String city = page.getHtml().xpath("//div[@class='info']/ol/li[1]/a[1]/text()").toString();
    }
}
