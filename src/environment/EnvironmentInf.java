package environment;

import util.Direction;
import gameCommons.Game;

public class EnvironmentInf extends Environment{

	public EnvironmentInf(Game g){
		super(g);
	}
	


	/**
	 *	
	 * defile les voies vers le bas si k est vrai ( si la grenouille monte) 
	 * sinon defile vers le haut ( si la grenouille descend)
	 *
	 */
	public void defileVersBas(boolean k) {
		int increment=0;
		if (k==true) increment=-1;	//on defile vers le bas alors l'ordonnée diminue
		else increment=+1;	
		for (Lane l: this.voies){
			l.modifOrd(l.getOrd()+ increment); 
		}
		

	}
	/** 
	 * rajoute une Lane à l'environnement
	 */
	public void addLane() {
		Lane l = new Lane(this.game,directionRandom(),this.game.height, +1);
		this.voies.add(l);
	}

	public void defile(Direction directionFrog, int hauteurFrog){
		if (directionFrog==Direction.up) {
			this.addLane();
			this.defileVersBas(true);
			
		}
		if (directionFrog==Direction.down &&  hauteurFrog!=0) {
			this.defileVersBas(false);
			
		}
		
	}
	



}
