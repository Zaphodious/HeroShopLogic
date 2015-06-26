/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import gamecore.Food;
import gamecore.GameController;
import gamecore.Item;
import gamecore.Reference;

import java.util.List;
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
	    System.out.println("Inventory Menu. Please select from the following:");
	    System.out.println("1:View inventory, 2:Add test data, 3:Remove Item, 4:Return to main menu");
	    System.out.print(">");

	    command = scanner.nextLine();

	    switch (command) {
	    case "1":
		System.out.println("The hero's inventory:");

		if (this.controller.getPlayerCharacter().getInventory().isEmpty()) {
		    System.out.println("The inventory is empty");
		} else {
		    int count = 1;
		    for (Item item : this.controller.getPlayerCharacter().getInventory()) {
			System.out.println(count + ": " + item.toString());
			count++;
		    }
		}

		break;
	    case "2":
		System.out.println("Adding a whole mess of items!");
		this.controller.getPlayerCharacter().addToInventory(new Food("Cheese, Bowl of Cottage", rand.nextInt(Reference.DEFAULT_MAX_STACK_SIZE)));
		this.controller.getPlayerCharacter().addToInventory(new Food("Wood", rand.nextInt(Reference.DEFAULT_MAX_STACK_SIZE)));
		this.controller.getPlayerCharacter().addToInventory(new Food("Random Crafting Item", rand.nextInt(Reference.DEFAULT_MAX_STACK_SIZE)));
		this.controller.getPlayerCharacter().addToInventory(new Food("Cheese, Cheddar", rand.nextInt(Reference.DEFAULT_MAX_STACK_SIZE)));
		this.controller.getPlayerCharacter().addToInventory(new Food("Vanilla Beans", rand.nextInt(Reference.DEFAULT_MAX_STACK_SIZE)));
		this.controller.getPlayerCharacter().addToInventory(new Food("Pile of Shiny Rocks", rand.nextInt(Reference.DEFAULT_MAX_STACK_SIZE)));
		this.controller.getPlayerCharacter().addToInventory(new Food("Funky-Smelling Cloth", rand.nextInt(Reference.DEFAULT_MAX_STACK_SIZE)));
		break;
	    case "3":
		System.out.println("At which position?");
		System.out.println(">");
		int itemIndex = Integer.parseInt(scanner.nextLine());
		itemIndex--;
		this.controller.getPlayerCharacter().getInventory().remove(itemIndex);

		break;
	    case "4":
		System.out.println("Returning you to the main menu.");
		return;
	    default:
		System.out.println("Sorry, that's not a valid command.");
		break;
	    }
	}
    }

    public void adventure() {

    }

    public void manageShop() {

    }

}
