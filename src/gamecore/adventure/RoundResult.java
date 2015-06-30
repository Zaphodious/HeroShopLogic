package gamecore.adventure;

import java.util.ArrayList;
import java.util.List;

public class RoundResult {

    private List<MessageContainer> messages; 
    
    public RoundResult() {
	messages = new ArrayList<MessageContainer>();
    }

    public String[] getMessagesOfType(MessageType messageType) {
	List<String> toReturn = new ArrayList<String>();
	for (MessageContainer message : messages) {
	    if (message.getMessageType() == messageType) {
		toReturn.add(message.getMessageString());
	    }
	}
	return (String[]) toReturn.toArray();
    }
    
    public List<String> getAllMessages() {
	List<String> toReturn = new ArrayList<String>();
	
	for (MessageContainer message : messages) {
	    toReturn.add(message.getMessageType().toString() + ": " + message.getMessageString());
	}
	
	return toReturn;
    }
    
    public void addMessage(MessageType messageType, String message) {
	this.messages.add(new MessageContainer(messageType, message));
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

class MessageContainer {
    private MessageType messageType;
    private String messageString;
    public MessageContainer(MessageType messageType, String messageString) {
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