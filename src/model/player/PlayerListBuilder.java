package model.player;

import java.util.ArrayList;

import model.player.type.IPlayer;

public class PlayerListBuilder implements IPlayerListBuilder {
	ArrayList<IPlayer> playerList;
	
	public PlayerListBuilder() {
		playerList = new ArrayList<IPlayer>();
	}
	
	@Override
	public void addPlayer(IPlayer player) {
		this.playerList.add(player);
		
	}

	@Override
	public ArrayList<IPlayer> buildPlayerList() {
		ArrayList<IPlayer> copy = (ArrayList<IPlayer>)this.playerList.clone();
		this.playerList = new ArrayList<IPlayer>();
		return copy;
		
	}

}
