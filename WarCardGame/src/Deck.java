import java.util.*;

//Represents a deck of playing cards.
public class Deck {
    private List<Card> cards; // 2-10, J(11), Q(12), K(13), A(14)
    private final int LOW_CARD = 2;
    private final int HIGH_CARD = 14;
    
    //Constructs a standard deck of 52 playing cards and shuffles them.
    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        for (String suit : suits) {
            for (int value = LOW_CARD; value <= HIGH_CARD; value++) { 
                cards.add(new Card(suit, value));
            }
        }
        Collections.shuffle(cards);
    }

    //Draws a single card from the deck.
    public Card drawCard() {
        return cards.isEmpty() ? null : cards.remove(0);
    }

    //Draws multiple cards from the deck.
    public List<Card> drawCards(int count) {
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < count && !cards.isEmpty(); i++) {
            drawnCards.add(drawCard());
        }
        return drawnCards;
    }

    //Adds multiple cards to the deck (used when a player wins a round).
    public void addCards(List<Card> newCards) {
        cards.addAll(newCards);
    }

    //Checks if the deck is empty.
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
