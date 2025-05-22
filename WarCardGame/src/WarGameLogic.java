import java.util.*;

//Manages the logic for the War card game.
public class WarGameLogic {
	private final int HALF_DACK = 26;
	private final int NUM_CARDS_IN_WAR = 3;
    private List<Card> player1Deck;
    private List<Card> player2Deck;
    private StringBuilder gameLog;

    //Initializes the game by splitting the deck between two players.
    public WarGameLogic() {
        Deck fullDeck = new Deck();
        player1Deck = fullDeck.drawCards(HALF_DACK); 
        player2Deck = fullDeck.drawCards(HALF_DACK); 
        gameLog = new StringBuilder();
    }

    

    //Plays a single round of the game.
    public void playRound() {
        List<Card> warPile = new ArrayList<>();
        gameLog.setLength(0);
        gameLog.append("Round starts...\n");
        
        if (player1Deck.isEmpty() || player2Deck.isEmpty()) {
            return; 
        }
        
        Card card1 = player1Deck.remove(0);
        Card card2 = player2Deck.remove(0);
        warPile.add(card1);
        warPile.add(card2);

        gameLog.append("Player 1 plays: ").append(card1).append("\n");
        gameLog.append("Player 2 plays: ").append(card2).append("\n");

        // Compare played cards
        if (card1.getValue() > card2.getValue()) {
            player1Deck.addAll(warPile);
            gameLog.append("Player 1 wins this round!\n")
            .append("Cards left:\n").append("Player 1: ").append(player1Deck.size())
            .append(", Player 2: ").append(player2Deck.size())
            .append("\n");
            
        } else if (card1.getValue() < card2.getValue()) {
            player2Deck.addAll(warPile);
            gameLog.append("Player 2 wins this round!\n")
            .append("Cards left:\n")
            .append("Player 1 - ").append(player1Deck.size())
            .append(", Player 2 - ").append(player2Deck.size())
            .append("\n");
        } else {           
            gameLog.append("War!\n");
            handleWar(warPile, card1, card2); // Pass the tied cards to handleWar
        }
    }


    //Handles the war scenario when both players play equal-value cards.
    private void handleWar(List<Card> warPile, Card previousP1Card, Card previousP2Card) {
        int p1Remaining = player1Deck.size();
        int p2Remaining = player2Deck.size();

        int p1WarCount = Math.min(NUM_CARDS_IN_WAR - 1, p1Remaining);
        int p2WarCount = Math.min(NUM_CARDS_IN_WAR - 1, p2Remaining);
        List<Card> p1WarCards = new ArrayList<>(player1Deck.subList(0, p1WarCount));
        List<Card> p2WarCards = new ArrayList<>(player2Deck.subList(0, p2WarCount));

        warPile.addAll(p1WarCards);
        warPile.addAll(p2WarCards);

        player1Deck.subList(0, p1WarCount).clear();
        player2Deck.subList(0, p2WarCount).clear();        
        
        Card p1FinalCard = !player1Deck.isEmpty() ? player1Deck.remove(0) : previousP1Card;
        Card p2FinalCard = !player2Deck.isEmpty() ? player2Deck.remove(0) : previousP2Card;

        warPile.add(p1FinalCard);
        warPile.add(p2FinalCard);
        gameLog.append("Player 1's war cards: ").append(p1WarCards).append(" and the final card is: ").append(p1FinalCard).append("\n");
        gameLog.append("Player 2's war cards: ").append(p2WarCards).append(" and the final card is: ").append(p2FinalCard).append("\n");

        // Compare final face-up cards
        if (p1FinalCard.getValue() > p2FinalCard.getValue()) {
            player1Deck.addAll(warPile);
            gameLog.append("Player 1 wins the war!\n");
        } else if (p1FinalCard.getValue() < p2FinalCard.getValue()) {
            player2Deck.addAll(warPile);
            gameLog.append("Player 2 wins the war!\n");
        } else {
            gameLog.append("Another tie! War continues...\n");
            handleWar(warPile, p1FinalCard, p2FinalCard); 
        }
    }



    //Gets the game log for review.
    public String getGameLog() {
        return gameLog.toString();
    }
    
    public boolean isGameFinished() {
    	 if (!player1Deck.isEmpty() && !player2Deck.isEmpty()) {
             return false;
         }
         else
         	gameLog.append(player1Deck.isEmpty() ? "Player 2 wins the game!\n" : "Player 1 wins the game!\n");
    	 return true;
    }

    
}
