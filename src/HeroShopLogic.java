/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gamecore.GameController;

import java.util.Random;
import java.util.Scanner;

import ui.TextInterface;

/**
 *
 * @author achyt_000
 */
public class HeroShopLogic {

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
	GameController controller = GameController.getInstance();
	Random rand = new Random();
	Scanner scanner = new Scanner(System.in);

	TextInterface textUI = new TextInterface(scanner, rand, controller);

	textUI.startInterface();

    }

}
