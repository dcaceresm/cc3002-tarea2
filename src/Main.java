import controller.ConsoleController;
import model.GameLogic;
import model.IGameLogic;
import model.card.CardPilesManager;
import model.card.ICardPilesManager;
import model.card.deck.StandardDeckStrategy;
import model.player.IPlayerListBuilder;
import model.player.IPlayerManager;
import model.player.PlayerListBuilder;
import model.player.PlayerManager;
import model.player.type.HumanPlayer;
import model.player.type.RandomPlayer;
import view.ConsoleView;

/**
 * Main class of UNO Game
 * 
 * It instantiates model, view and controller and makes the turn loop
 * while the game hasn't ended.
 * @author eriveros
 *
 */
public class Main {

  public static void main(String[] args) {
    IPlayerListBuilder playerBuilder = new PlayerListBuilder();
    //TODO: Create one human player and 3 random players
    playerBuilder.addPlayer(new HumanPlayer());
    playerBuilder.addPlayer(new HumanPlayer());
    /*playerBuilder.addPlayer(new RandomPlayer());
    playerBuilder.addPlayer(new RandomPlayer());
    playerBuilder.addPlayer(new RandomPlayer());*/
    //TODO: Assign the players to playerBuilder.
    ICardPilesManager cpManager = new CardPilesManager(new StandardDeckStrategy());
    IPlayerManager pManager = new PlayerManager(playerBuilder,cpManager);

    
    IGameLogic game = new GameLogic(pManager, cpManager);
    ConsoleView view = new ConsoleView(game);
    ConsoleController ctrl = new ConsoleController(game, view);
    while (!game.hasEnded()) {
      ctrl.playTurn();
    }
    game.announceWinner(ctrl);
  }
}
