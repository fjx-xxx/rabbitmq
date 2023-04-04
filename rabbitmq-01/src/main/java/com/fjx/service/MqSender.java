package com.fjx.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @Author: Fangjx
 * @Date: 21:42  2022/12/3
 */
@Service
@Slf4j
public class MqSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public  void send(Object msg){
        log.info("发送消息"+msg);
        rabbitTemplate.convertAndSend("queue",msg);
    }


    // 2
    public Object workQueueSend(String message){

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);

        //制造多个消息发送操作

        for (int i = 0; i < 10; i++) {

            message = message +i;
            rabbitTemplate.send("queue_work",new Message(message.getBytes(StandardCharsets.UTF_8),messageProperties));
        }

        return "message send : " + message;

    }



    public Object fanOutSend(String message){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);

        //fanout 模式只往exchange里发送消息，分发到exchange下所有queue
        rabbitTemplate.send("fanOutExchange","",new Message(message.getBytes(StandardCharsets.UTF_8),messageProperties));

        return "message send : " + message;
    }


    //routing
    public Object routeSend(String message){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);

        rabbitTemplate.send("directExchange","info",new Message(message.getBytes(StandardCharsets.UTF_8),messageProperties));
        rabbitTemplate.send("directExchange","waring",new Message(message.getBytes(StandardCharsets.UTF_8),messageProperties));
        rabbitTemplate.send("directExchange","error",new Message(message.getBytes(StandardCharsets.UTF_8),messageProperties));
        return "message send : " + message;
    }


    public Object topicSend(String message){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);

        rabbitTemplate.send("exchange_topic","topic.km.topic",new Message(message.getBytes(StandardCharsets.UTF_8),messageProperties));
        rabbitTemplate.send("exchange_topic","topic.km",new Message(message.getBytes(StandardCharsets.UTF_8),messageProperties));
        return "message send : " + message;
    }




}
