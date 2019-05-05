package com.lesson.mq;

/**
 * Created by ASUS on 2019/5/5.
 */
public class TestConsumer {

    public static void main(String[] args){
        Comsumer comsumer = new Comsumer();
        comsumer.init();
        TestConsumer testConsumer = new TestConsumer();
        //comsumer.getMessage("Jaycekon-MQ2");
        new Thread(testConsumer.new ConsumerMq(comsumer)).start();
        new Thread(testConsumer.new ConsumerMq(comsumer)).start();
        new Thread(testConsumer.new ConsumerMq(comsumer)).start();
        new Thread(testConsumer.new ConsumerMq(comsumer)).start();
    }

    private class ConsumerMq implements Runnable{
        Comsumer comsumer;
        public ConsumerMq(Comsumer comsumer){
            this.comsumer = comsumer;
        }

        //@Override
        public void run() {
            while(true){
                try {
                    comsumer.getMessage2("Jaycekon-MQ2");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
