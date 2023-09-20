package com.sirma.javacourse.networking.multicasting.model.receiver;

import com.sirma.javacourse.networking.multicasting.model.observer.Observable;
import com.sirma.javacourse.networking.multicasting.model.observer.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;

/**
 * A multicast receiver listens for incoming multicast messages.
 */
public class MulticastReceiver
        extends Thread
        implements Observable {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(MulticastReceiver.class);
    private static final int BUFFER_SIZE = 256;

    private final MulticastSocket socket;
    private boolean running;

    private Subscriber appController;

    public MulticastReceiver(int channelPort) throws IOException {
        socket = new MulticastSocket(channelPort);
        joinMulticastGroup(channelPort);
    }

    /**
     * Continuously receives packets and notifies this object's {@code Subscriber} with the received messages.
     */
    @Override
    public void run() {
        running = true;
        DatagramPacket packet;
        while (running) {
            byte[] buf = new byte[BUFFER_SIZE];
            packet = new DatagramPacket(buf, buf.length);

            try {
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                notifySubscribers(message);
            } catch (SocketException e) {
                notifySubscribers("Disconnected from the channel.");
                running = false;
            } catch (IOException e) {
                LOGGER.error("An error occurred while receiving messages in the channel", e);
            }
        }
    }

    @Override
    public void setSubscriber(Subscriber s) {
        appController = s;
    }

    @Override
    public void notifySubscribers(String message) {
        appController.update(message);
        LOGGER.info(message);
    }

    public boolean isConnected() {
        return running && socket != null;
    }

    public void disconnect() {
        socket.close();
        running = false;
    }

    /**
     * Joins the multicast group where the packets will be sent.
     *
     * @param port the port which the socket will listen on.
     */
    private void joinMulticastGroup(int port) {
        try {
            InetAddress broadcastGroup = InetAddress.getByName("230.0.0.1");
            NetworkInterface ni = NetworkInterface.getByInetAddress(broadcastGroup);
            SocketAddress socketAddress = new InetSocketAddress(broadcastGroup, port);
            socket.joinGroup(socketAddress, ni);
        } catch (IOException alreadyJoined) {
            return;
        }
    }
}
