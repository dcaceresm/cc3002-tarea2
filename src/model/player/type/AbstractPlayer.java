package model.player.type;

import java.util.ArrayList;

import controller.IController;
import model.IGameLogic;
import model.card.type.Color;
import model.card.type.ICard;	

public abstract class AbstractPlayer implements IPlayer {
	
	ArrayList<ICard> Hand;
	boolean saidUNO;
	public AbstractPlayer() {
		Hand = new ArrayList<ICard>();
		saidUNO = false;
	}
	@Override
	public void addToHand(ArrayList<ICard> hand) {
		this.Hand.addAll(hand);
		
	}

	@Override
	public boolean hasWon() {
		return this.getHandSize() == 0;
	}

	@Override
	public abstract ICard getCardToPlay(IGameLogic game, IController ctrl);

	@Override
	public abstract Color selectColor(IGameLogic game, IController ctrl);

	@Override
	public int getHandSize() {
		return this.Hand.size();
	}

	@Override
	public boolean hasOneCard() {
		return this.getHandSize() == 1;
	}

	@Override
	public ArrayList<ICard> getHand() {
		return this.Hand;
	}

	@Override
	public void setSaidUNO(boolean status) {
		this.saidUNO = status;
		
	}

	@Override
	public boolean hasSaidUNO() {
		return this.saidUNO;
	}

	@Override
	public void removeCardFromHand(ICard card) {
		this.Hand.remove(card);
		
	}

	@Override
	public boolean needsToDrawCard(ICard currentCard) {
		boolean needs = true;
		int i = 0;
		while(needs && i<this.getHandSize()) {
			needs = !(this.Hand.get(i).isPlayableOver(currentCard));
		}
		return needs;
	}

	@Override
	public ICard getCardFromHand(int number) {
		return Hand.get(number);
	}
	

}
