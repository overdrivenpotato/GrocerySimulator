package underthehood;

import java.util.ArrayList;
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
                if(Player.player().getMoney() > Player.player().getCartValue()) {
                    Player.player().buyCart();
                    return "Here you go!";
                }
                else if(Player.player().getMoney() <= 0)
                    return "I don't have any money!";
                else
                    return "I don't have enough money!";
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

        Scenario shank = new Scenario(new ActionString("You have shanked her, she has dropped 32000 monies.", new Action() {
            @Override
            public Object call() {
                Player.player().addMoney(32000);
                Player.player().shankWoman();
                return null;
            }
        }), "Shank her.", new Action() {
            @Override
            public Object call() {
                return Player.player().hasKnife();
            }
        });
        Scenario askForMoney = new Scenario("She doesn't give you money.", "Ask her for money.");
        Scenario woman = new Scenario("\"Hi there little hippo!\"", "Talk to woman in aisle.", new Action() {
            @Override
            public Object call() {
                return !Player.player().hasShanked();
            }
        }, askForMoney, shank);
        Scenario knife = new Scenario(new ActionString("You have picked up a knife.", new Action() {
            @Override
            public Object call() {
                Player.player().setKnife(true);
                return null;
            }
        }), "Pick up knife", new Action() {
            @Override
            public Object call() {
                return !Player.player().hasKnife();
            }
        });
        Scenario grapefruit = new Scenario(new ActionString("You have picked up a grapefruit.", new Action() {
            @Override
            public Object call() {
                Player.player().addGrapefruit();
                return null;
            }
        }), "Pick up grapefruit");
        grapefruit.addOptions(grapefruit, knife);
        Scenario shelf = new Scenario("There are a lot of items on the shelf.", "Go to shelf.", grapefruit, knife);
        Scenario aisle = new Scenario("You have walked into the aisle.", "Walk into aisle.", shelf, woman);

        current = new Scenario("You are in the store main area.", "Go to store main area", aisle, cashier);
        pay.addOptions(current);
        knife.addOptions(grapefruit, current, aisle);
        pointlessConversation.addOptions(current, cashier);
        askForMoney.addOptions(shelf, woman, current);
        shank.addOptions(shelf, current);
        grapefruit.addOptions(current, aisle);
        shelf.addOptions(current, aisle);
        aisle.addOptions(current);

        while(true)
        {
            System.out.println(current.getTextPrompt());

            if(!current.hasOptions())
                break;

            ArrayList<Scenario> optionList = new ArrayList<Scenario>();
            for(Scenario scenario : current.getOptions())
                if(scenario.isVisible())
                    optionList.add(scenario);

            for(int i = 0; i < optionList.size(); i++)
                System.out.println((char)('A' + (i)) + ") " + optionList.get(i).getDescription());


            int choice = in.next().toUpperCase().charAt(0) - 'A';
            System.out.println();

            current = optionList.get(choice);
        }
    }
}