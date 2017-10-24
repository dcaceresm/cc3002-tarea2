package model.player.type;

import controller.IController;
import model.IGameLogic;
import model.card.type.Color;
import model.card.type.ICard;
import model.card.type.NullCard;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer extends AbstractPlayer {

	@Override
	public ICard getCardToPlay(IGameLogic game, IController ctrl) {
		ICard ret = new NullCard();
		Random rand = new Random();
		ArrayList<ICard> hand = game.getCurrentPlayer().getHand();
		ArrayList<ICard> playable = new ArrayList<ICard>();
		for(ICard c : hand) {
			if(c.isPlayableOver(game.getCurrentPlayedCard()))
				playable.add(c);
		}
		if(!playable.isEmpty()) {
			int i = rand.nextInt(playable.size());
			ret = playable.get(i);
		}	
		return ret;
		
	}

	@Override
	public Color selectColor(IGameLogic game, IController ctrl) {
		Color[] c = Color.getColors();
		Random rand = new Random();
		int i = rand.nextInt(4);
		return c[i];	
	}

}
