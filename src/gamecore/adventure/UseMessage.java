package gamecore.adventure;

public class UseMessage {
    private MessageType messageType;
    private String messageString;

    private UseMessage(MessageType messageType, String messageString) {
	super();
	this.messageType = messageType;
	this.messageString = messageString;
    }

    public MessageType getMessageType() {
	return messageType;
    }

    public String getMessageString() {
	return messageString;
    }
    
    public static UseMessage newInstance(MessageType messageType, String messageString) {
	return new UseMessage(messageType, messageString);
    }
}
