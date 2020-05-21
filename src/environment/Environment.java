package environment;

import java.util.ArrayList;

import util.Direction;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment{
	protected Game game;
	protected ArrayList<Lane> voies = new ArrayList<>();

	public Environment(Game g){
		this.game=g;
		for(int i=0; i<game.height; i++){
			Lane l = new Lane(g,directionRandom(),i, game.randomGen.nextInt(1)+1);
			voies.add(l);
		}
	}
	
	public boolean directionRandom(){
		if (game.randomGen.nextDouble()>=0.5) return true;
		else return false;
		
	}

	@Override
	public boolean isSafe(util.Case c) {
		Lane l = this.voies.get(c.ord);
		if(l.isSafe(c)) return true; // si la voie à l'ordonnée ou est la case est safe
		return false;
	}

	@Override
	public boolean isWinningPosition(util.Case c) {
		return c.ord==game.height-1;
	}

	@Override
	public void update() {
		for (int i=1; i<this.voies.size()-1; i++){
			this.voies.get(i).update();
		}
		
	}



	/*Partie 3*/
	
	@Override
	public void defile(Direction directionFrog, int hauteurFrog) {

	}


}
