package frog;

import util.Case;
import util.Direction;
import gameCommons.Game;

public class FrogInf extends Frog{

	private int hauteur;

	public FrogInf(Game g){
		super(g);	
		this.hauteur=0;
	}
	
	@Override
	public void move(Direction key) {
		Case nouvelleCase = this.position;
		
		if(this.game.testLose() || this.game.testWin() ) return;
		
		switch(key){
		case up:
			this.hauteur+=1;
			if (this.hauteur>this.score) this.score=this.hauteur;
			break;
		case down:
			if (this.hauteur!=0) this.hauteur-=1;
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

	public int retourneHauteur(){
		return this.hauteur;
	}

}
