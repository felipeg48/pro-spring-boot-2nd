package com.apress.todo.jms;

import com.apress.todo.domain.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class TodoProducer {

    private static final Logger log = LoggerFactory.getLogger(TodoProducer.class);
    private JmsTemplate jmsTemplate;

    public TodoProducer(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }

    public void sendTo(String destination, ToDo toDo) {
        this.jmsTemplate.convertAndSend(destination, toDo);
        log.info("Producer> Message Sent");
    }

}
