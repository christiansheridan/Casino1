package io.zipcoder.casino.CardGame.Solitaire;

import io.zipcoder.casino.CardGame.Card;

import java.util.Stack;

import static io.zipcoder.casino.CardGame.Solitaire.Solitaire.lastStack;
import static io.zipcoder.casino.CardGame.Solitaire.Solitaire.tempStack;
import static io.zipcoder.casino.CardGame.Solitaire.Solitaire.unCover;

public class Tableau {
    //consider making an undo method.
    //or better yet, create method that lets you know where you can place. highlighted card in the UI.

    public Stack<Card> stack;


    public Tableau(){
        this.stack = new Stack<>();
    }

    public Integer size() { return stack.size(); }

    public void add(Card c){
        stack.push(c);
    }

    //c = the top card of the subStack you want to pull - ex. 6D, 5C, 4H, 3S, 2D, AS; if you want to pull 4H and down, c = 4H.
    public void pull(Card c){
        while(!stack.peek().equals(c))  tempStack.push(stack.pop());
        tempStack.push(stack.pop());
        }

    //does not need parameter. with a stack representing each column, will simply call 'stack'.place() to drop the tempStack on top of it.
    public void place(){
        if (this.canReceive(tempStack.peek())){
            while(tempStack.iterator().hasNext()){
                add(tempStack.pop());
                if (lastStack.size() > 0) unCover(lastStack);
            }
            if (lastStack.size() > 0) unCover(lastStack);
        } else { lastStack.push(tempStack.pop()); }
    }

    //checks whether 'top' card of stack is opposite color and 1 above passed card
    private boolean canReceive(Card c) {
        if(size() > 0) {
            if ((this.stack.peek().getFace().getPrimaryValue() - 1) == c.getFace().getPrimaryValue()
                    && (this.stack.peek().isBlack() != c.isBlack())) {
                return true;
            } else {
                System.out.println("Can't match " + stack.peek().toString() + " and " + c.toString());
                return false;
            }
        } else {
            if (c.getFace().getPrimaryValue() == 13) return true; //primVal of 13 is KING
        } return false;
    }
}
