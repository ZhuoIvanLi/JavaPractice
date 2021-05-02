package ObjectOrientedDesign;

import java.util.ArrayList;

/*
7.1 Card Game: Design a generic deck of cards.
 */
public class CardGame {
    public enum Suit{
        Club(0), Diamond(1), Heart(2), Spade(3);
        private int value;
        private Suit(int v) { value = v; }
        public int getValue() { return value; }
        public static Suit getSuitFromValue(int v) {...}
    }

    public class Deck <T extends Card>{
        private ArrayList<T> cards; // all cards, dealt or not
        private int dealtIndex = 0; // marks first undealt card

        public void setDeckOfCards(ArrayList<T> deckCards{...}

        public void shuffle(){...}
        public int remainingCards(){
            return cards.size() - dealtIndex;
        }

        public T[] dealHand(int number){...}
        public T dealCard(){...}

    }

    /*
        Abstract class: 1. can have abstract and non-abstract method. Interface methods are public abstract in default.
                        2. can have final and non-final variables. Interface has default final variables.
                        3. can be extended using keyword "extends". Interface use "implements"
                        4. can have implementation in abstract class
                        5. can extends another java class and implement multiples interfaces. Interface can only
                            implement one interface.
                        6. If a class extended abstract class has to implement all the abstract methods
     */
    public abstract class Card{
        private boolean available=true;

        protected int value;
        protected Suit suit;

        public Card(int v, Suit s){
            value = v;
            suit = s;
        }

        /*
            Abstract method: no body (or implementation); If a class extended abstract class has to implement all the
                abstract methods
         */
        public abstract int value();
        public Suit suit(){ return suit; }

        // Check out if the card is available to be given to someone
        public boolean isAvailable(){ return available; }
        public void markAvailable(){ available = true; }
        public void markUnavailable(){ available = false; }
    }

    public class Hand <T extends Card>{
        protected ArrayList<T> cards =  new ArrayList<T>();

        public int score(){
            int score = 0;
            for(T c : cards){
                score += c.value();
            }

            return score;
        }

        public void addCard(T card){
            cards.add(card);
        }
    }

    public class BlackJackDeck extends Deck<BlackJackCard>{

    }

    public class BlackJackHand extends Hand<BlackJackCard>{
        public int score(){
            ArrayList<Integer> scores =  possibleScores();
            int maxUnder= Integer.MIN_VALUE;
            int minOver = Integer. MAX_VALUE;

            for(int score : scores){
                if(score>21 && score < minOver){
                    minOver=score;
                }else if(score <= 21 && score > maxUnder){
                    maxUnder=score;
                }
            }

            return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
        }

        private ArrayList<Integer> possibleScores(){...}
        public boolean busted(){ return score() > 21; }
        public boolean is21(){ return score() ==21; }
        public boolean isBlackJack(){ ... }
    }

    public class BlackJackCard extends Card{
        public BlackJackCard(int c, Suit s){ super(c, s); }
        public int value(){
            if(isAce()) return 1;
            else if (value >= 11 && value <= 13) return 10;
            else return value;
        }

        public int minValue(){
            if(isAce()) return 1;
            else return value();
        }

        public int maxValue(){
            if(isAce()) return 11;
            else return value();
        }

        public boolean isAce(){
            return value==1;
        }

        public boolean isFaceCard(){
            return value >= 11 && value <= 13;
        }
    }

}
