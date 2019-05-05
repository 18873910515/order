package com.lesson.mq;

import com.lesson.CommonUtils;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.region.cursors.AbstractPendingMessageCursor;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ASUS on 2019/5/5.
 */
public class Comsumer {

    AtomicInteger count = new AtomicInteger(0);
    ConnectionFactory connectionFactory;
    Connection connection;
    Session session;
    ThreadLocal<MessageConsumer> threadLocal = new ThreadLocal<MessageConsumer>();

    public void init(){
        try{
            connectionFactory = new ActiveMQConnectionFactory(CommonUtils.USERNAME, CommonUtils.PASSWORD,CommonUtils.BROKER_URL);
            connection = connectionFactory.createConnection();
            connection.start();
           // session = connection.createSession(true,Session.SESSION_TRANSACTED);
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getMessage(String disname){
        try{
            Queue queue = session.createQueue(disname);
            MessageConsumer consumer ;
            if(threadLocal.get() != null){
                consumer = threadLocal.get();
            }else{
                consumer = session.createConsumer(queue);
                threadLocal.set(consumer);
            }
//            while (true){
//                Thread.sleep(1000);
//                TextMessage msg = (TextMessage) consumer.receive();
//                if(msg!=null) {
//                    msg.acknowledge();
//                    System.out.println(Thread.currentThread().getName()+": Consumer:我是消费者，我正在消费Msg"+msg.getText()+"--->"+count.getAndIncrement());
//                }else {
//                    break;
//                }
//            }
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    try{
                        //message.acknowledge();
                        Thread.sleep(1000);
                        String text = ((TextMessage)message).getText();
                        saveData(text);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            //connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getMessage2(String disname){
        try{
            Queue queue = session.createQueue(disname);
            MessageConsumer consumer ;
            if (threadLocal.get() != null){
                consumer = threadLocal.get();
            }else {
                consumer = session.createConsumer(queue);
                threadLocal.set(consumer);
            }
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    try{
                        long date = ((MapMessage)message).getLong("create_date");
                        String url = ((MapMessage)message).getString("file_url");
                        System.out.println("file_url = "+url +" , date = "+date);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveData(String text){
        System.out.println("----------------------------------处理消息---------------------"+text);
    }
}
