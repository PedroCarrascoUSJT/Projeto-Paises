package service;

import model.Cliente;
import model.Pais;
import dao.PaisDAO;
import java.util.ArrayList;

public class PaisService {
	PaisDAO paisDao = new PaisDAO();
	
	public int criar(Pais pais) {
		return paisDao.criar(pais);
	}
	
	public void atualizar(Pais pais){
		paisDao.atualizar(pais);
	}
	
	public void excluir(int id){
		paisDao.excluir(id);
	}
	
	public Pais carregar(int id){
		return paisDao.carregar(id);
	}
	
	public Pais carregaMaiorPopulacao(){
		return paisDao.carregaMaiorPopulacao();
	}
	
	public Pais carregarMenorArea(){
		return paisDao.carregarMenorArea();
	}
	
	public ArrayList<Pais> vetorTresPaises(){
		return paisDao.vetorTresPaises();
	}

}
