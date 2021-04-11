package com.example.rabbitmq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Utility class containing the creation
 * of the connection and channels which are
 * needed for producing and consuming messages.
 *
 * @author Sonya
 * @since 11.04.2021
 */
public class Utils {

    public static final String QUEUE = "messages";

    private Utils() {
    }

    /**
     * Creates a new Rabbit MQ connection.
     *
     * @return connection of type {@link Connection} or null
     * if an exception occurred
     */
    public static Connection createConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            return factory.newConnection();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a new Channel for the given Rabbit MQ Connection.
     *
     * @param connection the current {@link Connection}
     * @return channel of type {@link Channel} or null
     * if an exception occurred
     */
    public static Channel createChannel(Connection connection) {
        try {
            return connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
