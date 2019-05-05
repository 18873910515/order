package com.lesson.mq;

import com.lesson.CommonUtils;

/**
 * Created by ASUS on 2019/5/5.
 */
public class TestMq {

    public static void main(String[] args){
        Producter producter = new Producter();
        producter.init();
        TestMq testMq = new TestMq();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Thread 1
        new Thread(testMq.new ProductorMq(producter)).start();
        //Thread 2
        new Thread(testMq.new ProductorMq(producter)).start();
        //Thread 3
        new Thread(testMq.new ProductorMq(producter)).start();
        //Thread 4
        new Thread(testMq.new ProductorMq(producter)).start();
        //Thread 5
        new Thread(testMq.new ProductorMq(producter)).start();
    }

    private class ProductorMq implements Runnable{
        Producter producter;
        public ProductorMq(Producter producter){
            this.producter = producter;
        }

        //@Override
        public void run() {
            while(true){
                try {
                    producter.sendMessage2("Jaycekon-MQ2");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
