package gamecore.adventure;

import java.util.ArrayList;
import java.util.List;

public class RoundResult {

    private List<CombatMessage> messages;

    public RoundResult() {
	messages = new ArrayList<CombatMessage>();
    }

    public String[] getMessagesOfType(MessageType messageType) {
	List<String> toReturn = new ArrayList<String>();
	for (CombatMessage message : messages) {
	    if (message.getMessageType() == messageType) {
		toReturn.add(message.getMessageString());
	    }
	}
	return (String[]) toReturn.toArray();
    }

    public List<String> getAllMessages() {
	List<String> toReturn = new ArrayList<String>();

	for (CombatMessage message : messages) {
	    toReturn.add(message.getMessageType().toString() + ": " + message.getMessageString());
	}

	return toReturn;
    }

    public void addMessage(MessageType messageType, String message) {
	this.messages.add(new CombatMessage(messageType, message));
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
