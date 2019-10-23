
public class JoueurVirtuel extends Joueur implements Strategie{
	
	private Strategie strategie;
	private int niveau;
	
	public JoueurVirtuel(int niveau) {
		super();
		this.niveau = niveau;
	}
	
	public void faireOffre() {
		
	}
	
	public void prendreOffre() {
		
	}
	
	public String toString(){
	      return  "Bot de niveau" + niveau;
	}


	public void executerStartegie() {
		// TODO Auto-generated method stub
		
	}
}
