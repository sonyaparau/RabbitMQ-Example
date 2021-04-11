package com.example.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

import static com.example.rabbitmq.Utils.QUEUE;

/**
 * Producer class of Rabbit MQ messages.
 *
 * @author Sonya
 * @since 11.04.2021
 *
 * **/
public class Producer {

    private static final String MESSAGE = "This is the first message";
    private static final String MESSAGE_SUCCESSFULLY_SENT = "Message has been successfully sent!";
    private static final String MESSAGE_NOT_SENT = "No message has been sent due to an error!";

    /**
     * Produces the messages that are put in the queue.
     * */
    public static void main(String[] args) {
        Connection connection = Utils.createConnection();
        if (connection != null) {
            Channel channel = Utils.createChannel(connection);
            if (channel != null) {
                try {
                    channel.queueDeclare(QUEUE, false, false, false, null);
                    channel.basicPublish("", QUEUE, false, null, MESSAGE.getBytes());
                    System.out.println(MESSAGE_SUCCESSFULLY_SENT);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(MESSAGE_NOT_SENT);
                }
            }
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
