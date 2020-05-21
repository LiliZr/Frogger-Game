package frog;

import util.Case;
import util.Direction;
import gameCommons.Game;
import gameCommons.IFrog;

public class Frog implements IFrog{
	
	protected Game game;
	protected  Case position;
	protected Direction dernierMouv;
	protected int score;
	
	public Frog(Game g){
		this.game = g;		
		this.position = new Case(g.width/2, 0);
		this.score=0;
	}

	@Override
	public Case getPosition() {	
		return this.position;
	}

	@Override
	public Direction getDirection() {
		return this.dernierMouv;
	}
	
	@Override
	public void move(Direction key) {
		Case nouvelleCase = this.position;
		
		if(this.game.testLose() || this.game.testWin() ) return;
		
		switch(key){
		case up:
			nouvelleCase = new Case(this.position.absc, this.position.ord+1);
			this.score+=1;
			break;
		case down:
			nouvelleCase = new Case(this.position.absc, this.position.ord-1);
			break;
		case left:
			nouvelleCase = new Case(this.position.absc-1, this.position.ord);
			break;
		case right:
			nouvelleCase = new Case(this.position.absc+1, this.position.ord);
			break;
		}
		if (testCoord(nouvelleCase)){
			this.position = nouvelleCase;
			this.dernierMouv = key;
		}
	}
	public boolean testCoord(Case c){
		return c.absc<game.width && c.absc>=0 && c.ord<game.height && c.ord>=0;

	}

	@Override
	public void modifieLastD(Direction left) {
		this.dernierMouv=left;
		
	}



	@Override
	public int retourneHauteur() {
		return 0;
	}

	@Override
	public int retourneScore() {
		return this.score;
	}

}
