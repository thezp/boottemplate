package com.thezp.listener;

import com.thezp.dao.comm.vars.entity.VarsEntity;
import com.thezp.service.vars.IVarsService;
import com.thezp.util.VarsUtil;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by zhangpeng on 2018/1/4.
 */
@Slf4j
@Component
public class VarsInitListener {

    @Autowired
    private IVarsService varsService;

    @EventListener
    public void initVars(ApplicationReadyEvent e) {
        List<VarsEntity> list = varsService.selectEntities(null);
        VarsUtil.initVars(list);
        log.info("init vars done!");
    }

}
