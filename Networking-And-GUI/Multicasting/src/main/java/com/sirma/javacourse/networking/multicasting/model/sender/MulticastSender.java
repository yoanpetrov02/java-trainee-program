package com.sirma.javacourse.networking.multicasting.model.sender;

import com.sirma.javacourse.networking.multicasting.model.mediator.Component;
import com.sirma.javacourse.networking.multicasting.model.mediator.Mediator;

import java.io.IOException;
import java.net.*;

/**
 * A multicast sender uses multicasting to send packets.
 */
public class MulticastSender implements Component {

    private Mediator mediator;
    private DatagramSocket socket;
    private boolean connected;

    public MulticastSender() {
        connected = openSocket();
    }

    @Override
    public void setMediator(Mediator m) {
        mediator = m;
    }

    @Override
    public String getName() {
        return "sender";
    }

    /**
     * Constructs a packet with the given message and sends it to the given port.
     *
     * @param message the message.
     * @param port the port to send the message to.
     * @throws IOException if an i/o error occurs while sending the packet.
     */
    public void sendPacket(String message, int port) throws IOException {
        if (connected) {
            byte[] data = message.getBytes();
            InetAddress group = InetAddress.getByName("230.0.0.1");
            DatagramPacket packet = new DatagramPacket(data, data.length, group, port);

            socket.send(packet);
        }
    }

    /**
     * Finds a free port for this object's socket and opens it.
     *
     * @return true if the socket was successfully opened, false otherwise.
     */
    private boolean openSocket() {
        for (int i = 7000; i <= 7020; i++) {
            try {
                socket = new DatagramSocket(i);
                return true;
            } catch (SocketException portNotFree) {
                continue;
            }
        }
        return false;
    }
}
