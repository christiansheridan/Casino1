package io.zipcoder.casino.CardGame.Solitaire;

import io.zipcoder.casino.CardGame.Card;
import io.zipcoder.casino.CardGame.CardGame;
import io.zipcoder.casino.CardGame.Deck;
import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Console;
import io.zipcoder.casino.Player;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

import static io.zipcoder.casino.CardGame.Card.toCard;
import static io.zipcoder.casino.CardGame.Solitaire.Foundation.*;

public class Solitaire extends CardGame {

//    public static void main(String[] args){
//        Solitaire s = new Solitaire(new Player("Bill"));
//        s.start();
//    }
    Casino casino = Casino.getInstance();
    Console console = new Console(System.in, System.out);

    Scanner in = new Scanner(System.in);
    //clean up.

    //create setting for 3 card draw. which will affect the draw method.
    private Player player;
    public Tableau tab1, tab2, tab3, tab4, tab5, tab6, tab7;
    public Stack<Card> wastePile;
    public Tableau[] arrayTabs;
    public static Stack<Card> tempStack = new Stack<>();
    public static Stack<Card> lastStack = null;

    public Solitaire(Player player) {
        this.player = player;
        wastePile = new Stack<>();
        tab1 = new Tableau();
        tab2 = new Tableau();
        tab3 = new Tableau();
        tab4 = new Tableau();
        tab5 = new Tableau();
        tab6 = new Tableau();
        tab7 = new Tableau();
        arrayTabs = new Tableau[]{tab1, tab2, tab3, tab4, tab5, tab6, tab7};
    }

    public static Deck solitaireDeck = new Deck();


    public void start(){
        System.out.println("Welcome");
        help();
        resetDeck();
        wastePile.removeAllElements();
        tempStack.removeAllElements();
        clubStack.removeAllElements();
        diamondStack.removeAllElements();
        spadeStack.removeAllElements();
        heartStack.removeAllElements();
        shuffle();
        deal();
        print();
        takeATurn();
    }

    public void help(){
        console.println("\n\nInstructions:\n%s\n%s\n%s\n%s\n%s\n%s\n\n","To draw a card, enter \'DRAW\'","To pick up card from draw pile, enter \'P\'","To place card on column, enter column number. If card goes into foundation, enter \'8\'","To pull card down, type in card code (i.e. \'7H\' for Seven of Hearts","To quit, enter \'QUIT\'", "If you need help, just enter 'HELP'");
    }

    public void shuffle(){
        solitaireDeck.shuffle();
    }

    public void deal() {
        for (int i = 0; i < arrayTabs.length; i++) {
            for (int j = 0; j < arrayTabs.length; j++) {
                if (j >= i) arrayTabs[j].add(draw());
                if (j != i) arrayTabs[j].stack.peek().setCovered(true);
            }
        }
        for(Tableau tab : arrayTabs) tab.stack.peek().setCovered(false);
    }

    public void refillDeck(){
        if (solitaireDeck.deckOfCards.size()<1){
            while(wastePile.iterator().hasNext())
                solitaireDeck.deckOfCards.push(wastePile.pop());
        }
    }

    public void drawCard(){
        wastePile.push(solitaireDeck.draw());
    }

    public Stack<Card> pickUp(){
        tempStack.push(wastePile.pop());
        lastStack = wastePile;
        return tempStack;
    }

    public void dropToTab(char key){
        switch (key){
            case '1':
                tab1.place();
                break;
            case '2':
                tab2.place();
                break;
            case '3':
                tab3.place();
                break;
            case '4':
                tab4.place();
                break;
            case '5':
                tab5.place();
                break;
            case '6':
                tab6.place();
                break;
            case '7':
                tab7.place();
                break;
            case '8':
                whichSuit(tempStack);
                break;
            default:
                System.out.println("Not a valid entry. Try again");
                dropToTab(in.next().charAt(0));
        }
    }

    public void pull(String cardCode){
        char f = cardCode.toUpperCase().charAt(0);
        char s = cardCode.toUpperCase().charAt(1);
        Card c = toCard(f,s);
        findTab(c).pull(c);
    }

    public Tableau findTab(Card c){
        for (Tableau tab : arrayTabs)
            if (tab.stack.contains(c)) {
                lastStack = tab.stack;
                return tab;
            }
        return null;
    }

    public void peekWaste(){
        if(wastePile.size()>0) System.out.println("\n\nDraw pile: " + wastePile.peek().toString2());
    }

    public static void unCover(Stack<Card> lastStack){
        if (lastStack.size() > 0 && lastStack.peek().isCovered()) lastStack.peek().setCovered(false);
    }

    //you've got a temp stack. so when you pull a card, show it. if it doesn't go, put it back.
    //fix empty stack exceptions
    //draw shouldn't reprint every time. only print top of wastePile
    //build console class for solitaire printouts
    public void takeATurn() {
            console.println("\n\nReady? Let's Play");
            while (!allFoundsFull() || !gameOver()) {
                String command = console.getInputString("\nWhat now?");
                switch (String.valueOf(command)) {
                    case "DRAW":
                        drawCard();
                        print();
                        //console.println("\nDraw pile: " + wastePile.peek().toString2());
                        break;
                    case "P":
                        //try, catch, continue
                        try {
                            pickUp();
                            console.println("\nYou just picked up " + tempStack.peek().toString2());
                                dropToTab(console.getDropTab().charAt(0));
                            print();
                            break;
                        } catch (EmptyStackException e) {
                            console.println("\nCan't pull from an empty draw pile");
                            break;
                        }
                    case "HELP":
                        help();
                        break;
                    case "QUIT":
                        gameOver();
//                    case "FOO":
//                        cheatFoundations();
                    default:
                        try {
                            pull(String.valueOf(command));
                            console.println("\nYou just pulled " + tempStack.peek().toString2());
                            dropToTab(console.getDropTab().charAt(0));
                        } catch (NullPointerException e) {
                            console.println("Card does not exist. Try again :)\n");
                            break;
                        }
                        print();
                        break;
                }
                if (solitaireDeck.deckOfCards.size()<1) refillDeck();
            }
    }

    public void addPlayer(Player player) {

    }

    public void removePlayer(Player player) {

    }

    public Card draw() {
        return solitaireDeck.draw();
    }

    public void resetDeck(){
        solitaireDeck = new Deck();
    }
    Foundation found = new Foundation();

    public void print(){
        System.out.println("CLUBS\t\tDIAMONDS\t\tHEARTS\t\tSPADES");
        System.out.println("------\t\t--------\t\t------\t\t------");

        if (Foundation.clubStack.size() == 0){
            System.out.print("  --  \t\t");
        } else {
            System.out.print("  " + Foundation.clubStack.peek().toString2() + "  \t\t");
        }

        if (Foundation.diamondStack.size() == 0){
            System.out.print("   --  \t\t\t");
        } else {
            System.out.print("  " + Foundation.diamondStack.peek().toString2() + "  \t\t\t");
        }

        if (Foundation.heartStack.size() == 0){
            System.out.print("  --  \t\t");
        } else {
            System.out.print("  " + Foundation.heartStack.peek().toString2() + "  \t\t");
        }
        if (Foundation.spadeStack.size() == 0){
            System.out.println("  --  \t\t");
        } else {
            System.out.print("  " + Foundation.spadeStack.peek().toString2() + "  \t\t\n");
        }

        int i = 1;
        for (Tableau tab : arrayTabs) {
            System.out.println("\nCOLUMN " + i); i++;
            tab.stack.forEach(e -> System.out.print(e.toString2() + " " + "\t"));
        }
        peekWaste();
    }

    public Boolean gameOver(){
        casino.chooseGame();
        //if(console.getInputString("Are you sure you want to quit?\nEnter Y to quit").equals("Y")) return true;
        return false;
    }

    public void end() {
        System.out.println("Congratulations!");
        System.out.println("Enter \'N\' to play again or press \'Q\' to quit");
        String command = in.next().toUpperCase();
        while (!command.equals("Q") || !command.equals("N")){
            if (command.equals("Q"));
            else if (command.equals("N")) start();
            else System.out.println("Invalid. Enter \'N\' to play again or press \'Q\' to quit");
        }
    }
}