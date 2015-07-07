package gamecore.adventure;

public class CombatMessage {
    private MessageType messageType;
    private String messageString;

    public CombatMessage(MessageType messageType, String messageString) {
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
}
