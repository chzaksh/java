

// Represents a playing card with a suit and value.
public class Card {
    private String suit;
    private int value;

   
    //Constructs a Card with a specified suit and value.
    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    // Returns the value of the card.
    public int getValue() {
        return value;
    }

    //Returns a string representation of the card in the format "value of suit".
    public String toString() {
        return value + " of " + suit;
    }
}

