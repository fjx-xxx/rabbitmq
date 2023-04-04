package com.fjx.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Fangjx
 * @Date: 21:38  2022/12/3
 */
@Configuration
public class RabbitMQConfig {

    //1.simple模式
    @Bean
    public Queue queue(){

        return new Queue("queue",true);
    }

    //work模式
    @Bean
    public Queue queueWork(){

        return new Queue("queue_work");
    }

    //publish模式

    //准备一个交换机

    @Bean
    public FanoutExchange exchangeFanOut(){
        return new FanoutExchange("fanOutExchange");
    }
    //将交换机和队列进行绑定

    @Bean
    public Binding bindingExchange1(){
        return BindingBuilder.bind(queue()).to(exchangeFanOut());
    }

    @Bean
    public Binding bindingExchange2(){
        return BindingBuilder.bind(queueWork()).to(exchangeFanOut());
    }


    //routing
    @Bean
    public Queue queue1(){

        return new Queue("routing-queue1");
    }
    @Bean
    public Queue queue2(){

        return new Queue("routing-queue2");
    }
    @Bean
    public Queue queue3(){

        return new Queue("routing-queue3");
    }

    @Bean
    public DirectExchange directExchange(){

        return new DirectExchange("directExchange");
    }

    @Bean
    public Binding bindQueue1ToDirectExchange(){
        return BindingBuilder.bind(queue1()).to(directExchange()).with("info");
    }

    @Bean
    public Binding bindQueue2ToDirectExchange(){
        return BindingBuilder.bind(queue2()).to(directExchange()).with("waring");
    }


    @Bean
    public Binding bindQueue3ToDirectExchange(){
        return BindingBuilder.bind(queue3()).to(directExchange()).with("error");
    }



    @Bean
    public Queue queueTopic1(){
        return new Queue("queue_topic1");
    }

    @Bean
    public Queue queueTopic2(){
        return new Queue("queue_topic2");
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("exchange_topic");
    }

    @Bean
    public Binding bindingTopic1(){

        // #匹配多个
        return BindingBuilder.bind(queueTopic1()).to(topicExchange()).with("topic.#");
    }

    @Bean
    public Binding bindingTopic2(){

        // #匹配一个
        return BindingBuilder.bind(queueTopic2()).to(topicExchange()).with("topic.*");
    }






}
