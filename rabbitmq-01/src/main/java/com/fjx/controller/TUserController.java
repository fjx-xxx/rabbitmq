package com.fjx.controller;

import com.fjx.service.MqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * @Author: Fangjx
 * @Date: 21:47  2022/12/3
 */
@RestController
@RequestMapping("/user")
public class TUserController {


    @Autowired
    private MqSender mqSender;

    @RequestMapping("/mq/{message}")
    @ResponseBody
    public Object mq(@PathVariable("message") String message){
        mqSender.send(message );
        return "message send " + message;
    }

    @RequestMapping("/mq_work")
    public void mqWork(){
        mqSender.workQueueSend("方俊雄");
    }

    @RequestMapping("/mq_fanout")
    public void mqFanout(){
        mqSender.fanOutSend("方俊雄-fanout");
    }

    @RequestMapping("/mq_routing")
    public void mqRoute(){
        mqSender.routeSend("message-routing");
    }

    @RequestMapping("/mq_topic")
    public void mqTopic(){
        mqSender.topicSend("message-topic");
    }





}
