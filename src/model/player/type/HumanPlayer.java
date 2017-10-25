package model.player.type;

import controller.IController;
import model.IGameLogic;
import model.card.type.Color;
import model.card.type.ICard;

public class HumanPlayer extends AbstractPlayer {

	@Override
	public ICard getCardToPlay(IGameLogic game, IController ctrl) {
		int i = ctrl.AskForCardFromHand(game.getCurrentPlayer());
		if(game.getCurrentPlayer().getHandSize() == i) {
			ICard c = game.drawOneCard(game.getCurrentPlayer());
			ctrl.showMessage("Robas un "+c.toString());
			return c;
		}
		return this.getCardFromHand(i);
	}

	@Override
	public Color selectColor(IGameLogic game, IController ctrl) {
		Color c = ctrl.askForColor();
		game.changeColor(ctrl, c);
		return c;
	}

}
