package com.lesson;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ASUS on 2019/5/5.
 */
public class CommonUtils {
    //默认的用户名
    public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //默认的密码
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //broker地址
    public static final String BROKER_URL = "failover://tcp://192.168.190.137:61616";

}
