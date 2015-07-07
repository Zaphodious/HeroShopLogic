package gamecore.shop;

import gamecore.ZaphUtil;
import gamecore.item.Item;

import java.util.List;

class DisplayCase {

    private List<Pedestal> CaseContents;

    DisplayCase() {
	CaseContents = ZaphUtil.newList();
    }

    void addItem(Item item, int amount) {
	this.CaseContents.add(new Pedestal(item, amount));
    }

    private class Pedestal {
	Item item;
	int amount;
	int timeStamp;

	Pedestal(Item item, int amount) {
	    this.item = item;
	    this.amount = amount;
	    this.timeStamp = ZaphUtil.getSecondsTimeStamp();
	}

	Item getItem() {
	    return item;
	}

	int getAmount() {
	    return amount;
	}

	int getTimeStamp() {
	    return timeStamp;
	}

    }
}
