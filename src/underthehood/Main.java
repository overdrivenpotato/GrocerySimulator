package underthehood;

import java.util.Scanner;

/**
 * Created by marko on 02/01/14.
 */
public class Main {

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("It's alive!");

        Scenario current;

        Scenario pay = new Scenario(new ActionString(null, new Action() {
            @Override
            public Object call() {
                if(Player.player().getMoney() > 0)
                    return "Here you go!";
                else
                {
                    return "I dont have any money!";
                }
            }
        }), "Let me just get my wallet");
        Scenario countItems = new Scenario(new ActionString(null, new Action() {
            @Override
            public Object call() {
                return "That will be " + Player.player().getCartValue();
            }
        }), "Yes, how much do I owe you?", pay);
        Scenario pointlessConversation = new Scenario(new ActionString(null, new Action() {
            @Override
            public Object call() {
                return new ConverseGen().getQuestion();
            }
        }), "No, I just came to talk to you.");
        Scenario cashier = new Scenario("\"Hi There! Do you have any items you would like to check out?\"", "Talk to the cashier", pointlessConversation, countItems);

        Scenario askForMoney = new Scenario("She doesn't give you money.", "Ask her for money.");
        Scenario woman = new Scenario("\"Hi there little hippo!\"", "Talk to woman in aisle.", askForMoney);
        Scenario pickupItems = new Scenario(new ActionString("You have picked up a grapefruit.", new Action() {
            @Override
            public Object call() {
                Player.player().addGrapefruit();
                return null;
            }
        }), "Pick up grapefruit", woman);
        pickupItems.addOptions(pickupItems);
        Scenario shelf = new Scenario("There are a lot of items on the shelf.", "Go to shelf.", pickupItems, woman);
        Scenario aisle = new Scenario("You have walked into the aisle.", "Walk into aisle.", shelf, woman);

        current = new Scenario("You are in the store main area.", "Go to store main area", aisle, cashier);
        pay.addOptions(current);
        pointlessConversation.addOptions(current, cashier);
        askForMoney.addOptions(shelf, woman, current);
        pickupItems.addOptions(current);
        shelf.addOptions(current);

        while(true)
        {
            System.out.println(current.getTextPrompt());

            if(!current.hasOptions())
                break;

            for(int i = 0; i < current.getOptions().size(); i++)
            {
                System.out.println((char)('A' + i) + ") " + current.getOptions().get(i).getDescription());
            }

            int choice = in.next().toUpperCase().charAt(0) - 'A';

            System.out.println();

            current = current.getOptions().get(choice);
        }
    }
}