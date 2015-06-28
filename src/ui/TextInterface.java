/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import gamecore.GameController;
import gamecore.Reference;
import gamecore.item.Food;
import gamecore.item.Item;
import gamecore.item.ItemType;
import gamecore.item.Material;
import gamecore.item.Weapon;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author achyt_000
 */
public class TextInterface {

    Scanner scanner;
    Random rand;
    GameController controller;

    String breakSpace;

    public TextInterface(Scanner scanner, Random rand, GameController controller) {
	this.scanner = scanner;
	this.rand = rand;
	this.controller = controller;
	this.breakSpace = ".-*-.-*-.-*-.-*-.-*-.-*-.-*-.";
    }

    public void startInterface() {
	String command = "";
	while (true) {
	    System.out.println("Please select from the following choices (use the number):");
	    System.out.println("1:Character, 2:Inventory, 3:Adventure, 4:Shop, 5:Exit");
	    System.out.print(">");

	    command = scanner.nextLine();

	    switch (command) {
	    case "1":
		System.out.println("calling the hero function.");
		this.manageCharacter();
		break;
	    case "2":
		System.out.println("calling the inventory function.");
		this.manageInventory();
		break;
	    case "3":
		System.out.println("calling the adventure function.");
		this.adventure();
		break;
	    case "4":
		System.out.println("calling the shop function.");
		this.manageShop();
		break;
	    case "5":
		System.out.println("Exiting. Bye now!");
		return;
	    default:
		System.out.println("Sorry, that's not a valid command.");
		break;
	    }

	    System.out.println(this.breakSpace);
	}
    }

    public void manageCharacter() {
	System.out.println(this.breakSpace);

	while (true) {
	    System.out.println("Hero menu. Please select from the following options:");
	    System.out.println("1:View Hero, 2:Change Hero Name, 3:Re-Roll Hero, 4:Return to main menu");
	    System.out.print(">");
	    String command = scanner.nextLine();

	    switch (command) {
	    case "1":
		System.out.println("Your Hero:");
		System.out.println(controller.getPlayerCharacter().toString());
		break;
	    case "2":
		System.out.println("What should the hero's name be?");
		System.out.print(">");
		this.controller.nameThePlayer(scanner.nextLine());
		break;
	    case "3":
		System.out.println("Making a new hero!");
		System.out.println("What should the hero's name be?");
		System.out.print(">");
		this.controller.reRoll(scanner.nextLine());
		break;
	    case "4":
		System.out.println("Return to main menu");
		System.out.println(this.breakSpace);
		return;
	    default:
		System.out.println("Sorry, that's not a valid command.");
		break;
	    }
	}

    }

    public void manageInventory() {
	System.out.println(this.breakSpace);
	String command = "";
	while (true) {
	    Item[] playerInventory = this.controller.getPlayerCharacter().getInventory().getItems();
	    System.out.println("Inventory Menu. Please select from the following:");
	    System.out.println("1:View inventory, 2:Add test data, 3:Remove Item, 4:Equip Weapon, 5:Return to main menu");
	    System.out.print(">");

	    command = scanner.nextLine();

	    switch (command) {
	    case "1":
		System.out.println("The hero's inventory:");

		if (playerInventory.length == 0) {
		    System.out.println("The inventory is empty");
		} else {
		    int counter = 0;
		    System.out.println("player inventory has " + playerInventory.length + " items in it.");
		    for (Item item : playerInventory) {

			System.out.println(counter + 1 + ": " + item.toString());
			counter++;
		    }

		    /*
		     * for (int i = 0; i < playerInventory.length; i++) { try {
		     * System.out.println(i+1 + ": " +
		     * playerInventory[i].toString()); } catch (Exception e) {
		     * // TODO Auto-generated catch block e.printStackTrace(); }
		     * }
		     */
		}

		break;
	    case "2":
		System.out.println("Adding a whole mess of items!");
		this.controller.getPlayerCharacter().getInventory().addItem(new Food("Cheese, Bowl of Cottage", rand.nextInt(Reference.DEFAULT_MAX_STACK_SIZE)));
		this.controller.getPlayerCharacter().getInventory().addItem(new Material("Wood", rand.nextInt(Reference.DEFAULT_MAX_STACK_SIZE)));
		this.controller.getPlayerCharacter().getInventory().addItem(new Food("Random Crafting Item", rand.nextInt(Reference.DEFAULT_MAX_STACK_SIZE)));
		this.controller.getPlayerCharacter().getInventory().addItem(new Food("Cheese, Cheddar", rand.nextInt(Reference.DEFAULT_MAX_STACK_SIZE)));
		this.controller.getPlayerCharacter().getInventory().addItem(new Food("Vanilla Beans", rand.nextInt(Reference.DEFAULT_MAX_STACK_SIZE)));
		this.controller.getPlayerCharacter().getInventory().addItem(new Material("Pile of Shiny Rocks", rand.nextInt(Reference.DEFAULT_MAX_STACK_SIZE)));
		this.controller.getPlayerCharacter().getInventory().addItem(new Material("Funky-Smelling Cloth", rand.nextInt(Reference.DEFAULT_MAX_STACK_SIZE)));
		this.controller.getPlayerCharacter().getInventory().addItem(new Weapon("Battle Axe", rand.nextInt(12), 64));
		this.controller.getPlayerCharacter().getInventory().addItem(new Weapon("Long Sword", rand.nextInt(12), 64));
		break;
	    case "3":

		while (true) {
		    try {
			System.out.println("At which position?");
			System.out.println(">");
			int itemIndex = Integer.parseInt(scanner.nextLine());
			itemIndex--;
			this.controller.getPlayerCharacter().getInventory().removeItem(playerInventory[itemIndex]);
			break;
		    } catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Sorry, that didn't work. Please enter a value between 1 and " + this.controller.getPlayerCharacter().getInventory().getItems().length);
		    }
		}
		System.out.println("Item successfully removed.");

		break;
	    case "4":
		System.out.println("Weapons:");
		if (this.controller.getPlayerCharacter().getInventory().getItems(ItemType.WEAPON).length == 0) {
		    System.out.println("The inventory is empty");
		} else {
		    int count = 1;
		    for (Item item : this.controller.getPlayerCharacter().getInventory().getItems(ItemType.WEAPON)) {
			System.out.println(count + ": " + item.toString());
			count++;
		    }

		    System.out.println("Which weapon do you want to equip?");
		    try {
			System.out.println(">");
			int itemIndex = Integer.parseInt(scanner.nextLine());
			itemIndex--;
			this.controller.getPlayerCharacter().equipWeapon((Weapon) this.controller.getPlayerCharacter().getInventory().getItems(ItemType.WEAPON)[itemIndex]);
			break;
		    } catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Sorry, that didn't work. Please enter a value between 1 and " + this.controller.getPlayerCharacter().getInventory().getItems().length);
		    }
		}
		break;
	    case "5":
		System.out.println("Returning you to the main menu.");
		return;
	    default:
		System.out.println("Sorry, that's not a valid command.");
		break;
	    }
	}
    }

    public void adventure() {
	System.out.println(this.breakSpace);
	/*
	while (true) {
	    System.out.println("Adventure! This is the map:");
	    String[] gameMap = this.controller.getArea().getMap();
	    
	    for (String string : gameMap) {
		System.out.println(string);
	    }
	    
	    System.out.println();
	    
	    
	    
	    System.out.println("1:View Hero, 2:Change Hero Name, 3:Re-Roll Hero, 4:Return to main menu");
	    System.out.print(">");
	    String command = scanner.nextLine();

	    switch (command) {
	    case "1":
		System.out.println("Your Hero:");
		System.out.println(controller.getPlayerCharacter().toString());
		break;
	    case "2":
		System.out.println("What should the hero's name be?");
		System.out.print(">");
		this.controller.nameThePlayer(scanner.nextLine());
		break;
	    case "3":
		System.out.println("Making a new hero!");
		System.out.println("What should the hero's name be?");
		System.out.print(">");
		this.controller.reRoll(scanner.nextLine());
		break;
	    case "4":
		System.out.println("Return to main menu");
		System.out.println(this.breakSpace);
		return;
	    default:
		System.out.println("Sorry, that's not a valid command.");
		break;
	    }
	    break;
	}*/
    }

    public void manageShop() {

    }

}
