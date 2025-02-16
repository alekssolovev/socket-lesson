package study.socket.common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.ZonedDateTime;
import java.util.Objects;

public class ConnectionService implements AutoCloseable {
    private final Socket socket;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;

    public ConnectionService(Socket socket) throws IOException {
        this.socket = Objects.requireNonNull(socket, "socket is null");
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void writeMessage(Message message) throws IOException {
        message.setSentAt(ZonedDateTime.now());
        outputStream.writeObject(message);
        outputStream.flush();
    }

    public Message readMessage() throws IOException {
        try {
            return (Message) inputStream.readObject();
        }  catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void close() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Ошибка во время закрытия ресурсов");
        }
    }

    }