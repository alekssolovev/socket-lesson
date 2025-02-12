package study.socket.common;


import java.io.Serializable;
import java.time.ZonedDateTime;

public class Message implements Serializable {
    private final String text;
    private ZonedDateTime sentAT;

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public ZonedDateTime getSentAT() {
        return sentAT;
    }

    public void setSentAt(ZonedDateTime sentAT) {
        this.sentAT = sentAT;
    }


}
