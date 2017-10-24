package model.card.type;

import controller.IController;
import model.IGameLogic;

public class InvertCard extends Card {

	public InvertCard(Color aColor, Symbol aSymbol) {
		super(aColor, aSymbol);
		// TODO Auto-generated constructor stub
	}
	
	public void executeAction(IGameLogic game, IController ctrl) {
		game.invertDirection();
	}

}
