package model;

public class Atsiliepimai {
	private int id ;
	private String miestas;
	private String vardas;
	private String pastas;
	private String atsiliepimai;
	
	public Atsiliepimai() {
		super();
	}
	public Atsiliepimai(int id, String miestas, String vardas, String pastas, String atsiliepimai) {
		super();
		this.id = id;
		this.miestas = miestas;
		this.vardas = vardas;
		this.pastas = pastas;
		this.atsiliepimai = atsiliepimai;
	}
	
	public Atsiliepimai(String miestas, String vardas, String pastas, String atsiliepimai) {
		super();
		this.id = id;
		this.miestas = miestas;
		this.vardas = vardas;
		this.pastas = pastas;
		this.atsiliepimai = atsiliepimai;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMiestas() {
		return miestas;
	}
	public void setMiestas(String miestas) {
		this.miestas = miestas;
	}
	public String getVardas() {
		return vardas;
	}
	public void setVardas(String vardas) {
		this.vardas = vardas;
	}
	public String getPastas() {
		return pastas;
	}
	public void setPastas(String pastas) {
		this.pastas = pastas;
	}
	public String getAtsiliepimai() {
		return atsiliepimai;
	}
	public void setAtsiliepimai(String atsiliepimai) {
		this.atsiliepimai = atsiliepimai;
	}

}
