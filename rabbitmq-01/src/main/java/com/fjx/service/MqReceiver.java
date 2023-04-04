package com.fjx.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Author: Fangjx
 * @Date: 21:44  2022/12/3
 */
@Service
@Slf4j
public class MqReceiver {

    @RabbitListener(queues = "queue")
    public void receive(String message) {
//        log.info(("接收消息："+msg));
        System.out.println("quequ_simple: received  message : " + message);

    }


    @RabbitListener(queues = "queue_work")
    public void queueWork(String message) {
        System.out.println("quequ_work: received  message : " + message);
    }

    @RabbitListener(queues = "queue_work")
    public void queueWork01(String message) {
        System.out.println("quequ_work01: received  message : " + message);
    }



    //routing


    @RabbitListener(queues = "routing-queue1")
    public void routingQueue1(String message) {
        System.out.println("routing-queue1: received info  message : " + message);
    }



    @RabbitListener(queues = "routing-queue2")
    public void routingQueue2(String message) {
        System.out.println("routing-queue2: received waring message : " + message);
    }

    @RabbitListener(queues = "routing-queue3")
    public void routingQueue3(String message) {
        System.out.println("routing-queue3: received error  message : " + message);
    }


    //
    @RabbitListener(queues = "queue_topic1")
    public void topic1Queue3(String message) {
        System.out.println("queue_topic1: received   message : " + message);
    }

    @RabbitListener(queues = "queue_topic2")
    public void topic2Queue3(String message) {
        System.out.println("queue_topic2: received   message : " + message);
    }

















}
