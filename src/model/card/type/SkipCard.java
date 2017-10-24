package model.card.type;

import controller.IController;
import model.IGameLogic;

public class SkipCard extends Card {

	public SkipCard(Color aColor, Symbol aSymbol) {
		super(aColor, aSymbol);
	}
	
	@Override
	public void executeAction(IGameLogic game, IController ctrl) {
		game.skipPlayer();
	}

}
