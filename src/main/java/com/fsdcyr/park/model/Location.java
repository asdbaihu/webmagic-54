package com.fsdcyr.park.model;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;

@ExtractBy(value = "//div[@class='singleinfo']/dl/dd")
public class Location implements AfterExtractor{

    @ExtractBy(value = "dd[1]/a/text()")
    private String cityName;

    public void afterProcess(Page page) {
        page.putField("cityName", cityName);
    }
}
