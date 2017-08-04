package com.fsdcyr.park.pipeline;

import com.fsdcyr.park.dao.ParkDao;
import com.fsdcyr.park.model.Park;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Slf4j
@Service
public class DBPipeline implements Pipeline{
    @Autowired
    private ParkDao parkDao;

    public void process(ResultItems resultItems, Task task) {
        try {
            Park park = new Park();
            BeanUtils.populate(park, resultItems.getAll());
            parkDao.insert(park);
        } catch (Exception e) {
            log.error("", e);
        }
    }

}
