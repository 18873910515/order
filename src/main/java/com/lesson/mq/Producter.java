package com.lesson.mq;

import com.lesson.CommonUtils;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ASUS on 2019/5/5.
 */
public class Producter {
   AtomicInteger count = new AtomicInteger(0);
   ConnectionFactory connectionFactory;
   Connection connection;
   Session session;
   ThreadLocal<MessageProducer> threadLocal = new ThreadLocal<MessageProducer>();
   public void init(){
       try{
           connectionFactory = new ActiveMQConnectionFactory(CommonUtils.USERNAME, CommonUtils.PASSWORD,CommonUtils.BROKER_URL);
           connection = connectionFactory.createConnection();
           connection.start();
           //session = connection.createSession(true,Session.SESSION_TRANSACTED);
           session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
       }catch(JMSException e){
           e.printStackTrace();
       }
   }

//   public void sendMessage(String disname){
//       try{
//           Queue queue = session.createQueue(disname);
//            MessageProducer producer = null;
//            if(threadLocal.get() != null){
//                producer = threadLocal.get();
//            }else{
//                producer = session.createProducer(queue);
//                threadLocal.set(producer);
//            }
//            int num =0;
//            while (num <= 200){
//                Thread.sleep(1000);
//                num = count.getAndIncrement();
//                TextMessage msg = session.createTextMessage(Thread.currentThread().getName()+
//                        "productor:我是大帅哥，我现在正在生产东西！,count:"+num);
//                System.out.println(Thread.currentThread().getName()+
//                        "productor:我是大帅哥，我现在正在生产东西！,count:"+num);
//                //producer.setDeliveryMode(DeliveryMode.PERSISTENT);
//                producer.send(msg);
//                //session.commit();
//            }
//           connection.close();
//
//       }catch (Exception e){
//           e.printStackTrace();
//       }
//   }

    public void sendMessage2(String disname){
       try{
           Queue queue = session.createQueue(disname);
           MessageProducer producer ;
           if (threadLocal.get() != null){
               producer = threadLocal.get();
           }else{
               producer = session.createProducer(queue);
               threadLocal.set(producer);
           }
           MapMessage mapMessage = session.createMapMessage();
           mapMessage.setString("file_url","http://www.baidu.com");
           mapMessage.setLong("create_date",System.currentTimeMillis());
           producer.send(mapMessage);
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
