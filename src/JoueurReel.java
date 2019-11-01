
public class JoueurReel extends Joueur {
	
	private String pseudo;

	public JoueurReel() {
		super();
		this.pseudo = "null";
	}

	public JoueurReel(int id, String username) {
		super();
		this.id = id;
		this.pseudo = username;
	}

	public void faireOffre() {
		
	}
	
	public void prendreOffre() {
		
	}
	
	public String toString(){
	      return  pseudo;
	}
}
