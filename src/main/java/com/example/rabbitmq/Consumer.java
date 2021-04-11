package com.example.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

import static com.example.rabbitmq.Utils.QUEUE;

/**
 * Consumer class of Rabbit MQ messages.
 *
 * @author Sonya
 * @since 11.04.2021
 *
 * */
public class Consumer {

    /**
     * Consumes the messages that are put in the queue.
     * */
    public static void main(String[] args) {
        Connection connection = Utils.createConnection();
        if (connection != null) {
            Channel channel = Utils.createChannel(connection);
            if (channel != null) {
                try {
                    channel.queueDeclare(QUEUE, false, false, false, null);
                    channel.basicConsume(QUEUE, true, new DeliverCallback() {
                        public void handle(String consumerTag, Delivery message) throws IOException {
                            String readMessage = new String(message.getBody(), "UTF-8");
                            System.out.println("The received message is: " + readMessage);

                        }
                    }, new CancelCallback() {
                        public void handle(String s) {
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
