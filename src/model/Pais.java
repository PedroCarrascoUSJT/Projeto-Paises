package model;

public class Pais {

	private int id;
	private String nome;
	private long populacao;
	private double area;
	
	public Pais(int id, String nome, long populacao, double area) {
		
		setId(id);
		setNome(nome);
		setPopulacao(populacao);
		setArea(area);
		
	}
	
	public Pais(String nome, long populacao, double area) {
		
		setNome(nome);
		setPopulacao(populacao);
		setArea(area);
		
	}
	
	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}
	
	public double getArea() {
		return area;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public long getPopulacao() {
		return populacao;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setArea(double area) {
		this.area = area;
	}
	
	@Override
	public String toString() {
		return "Pais [id=" + getId() + ", nome=" + getNome() + ", População=" + getPopulacao()
				+ ", email=" + getArea() + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		if (getNome() == null) {
			if (other.getNome() != null)
				return false;
		} else if (!getNome().equals(other.getNome()))
			return false;
		if (getPopulacao() == -1) {
			if (other.getPopulacao() != -1)
				return false;
		} else if (getPopulacao() == other.getPopulacao())
			return true;
		if (getId() != other.getId())
			return false;
		if (getArea() == -1) {
			if (other.getArea() != -1)
				return false;
		} else if (getArea() == other.getArea())
			return true;
		return true;
	}

}
