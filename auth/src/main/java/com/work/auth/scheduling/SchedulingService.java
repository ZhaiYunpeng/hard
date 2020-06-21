package com.work.auth.scheduling;

import com.work.auth.util.DateUtil;
import com.work.auth.webSocket.MyWebSocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;

/**
 * @author ZhaiYunpeng
 */
@Slf4j
@Component
public class SchedulingService {
    @Resource
    private MyWebSocketHandler webSocketHandler;
    /**
     * 表达式  5秒执行一次
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled() {
        log.info("=====>>>>>使用cron  {}", DateUtil.getNowDateTimeStr());
        TextMessage textMessage = new TextMessage("{\"msg\":\"" + DateUtil.getNowDateTimeStr() + "\"}");
        webSocketHandler.sendMessageToAllUsers(textMessage);
    }

//    /**
//     * 上次执行开始后，间隔固定时间执行下一次
//     */
//    @Scheduled(fixedRate = 10000)
//    public void scheduled1() {
//        log.info("=====>>>>>使用fixedRate{}", DateUtil.getNowDateTimeStr());
//    }
//
//    /**
//     * 上次执行完成后，间隔固定时间执行下一次
//     */
//    @Scheduled(fixedDelay = 15000)
//    public void scheduled2() {
//        log.info("=====>>>>>fixedDelay{}", DateUtil.getNowDateTimeStr());
//    }
}
