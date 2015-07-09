package gamecore.adventure;

import java.util.ArrayList;
import java.util.List;

public class RoundResult {

    private List<UseMessage> messages;

    public RoundResult() {
	messages = new ArrayList<UseMessage>();
    }

    public String[] getMessagesOfType(MessageType messageType) {
	List<String> toReturn = new ArrayList<String>();
	for (UseMessage message : messages) {
	    if (message.getMessageType() == messageType) {
		toReturn.add(message.getMessageString());
	    }
	}
	return (String[]) toReturn.toArray();
    }

    public List<String> getAllMessages() {
	List<String> toReturn = new ArrayList<String>();

	for (UseMessage message : messages) {
	    toReturn.add(message.getMessageType().toString() + ": " + message.getMessageString());
	}

	return toReturn;
    }

    public void addMessage(MessageType messageType, String message) {
	this.messages.add(UseMessage.newInstance(messageType, message));
    }
    
    public void addMessage(UseMessage useMessage) {
	this.messages.add(useMessage);
    }

    @Override
    public String toString() {
	StringBuilder toReturn = new StringBuilder();
	List<String> allMessages = getAllMessages();
	for (String message : allMessages) {
	    toReturn.append(message + "\n");
	}
	return toReturn.toString();
    }

}
