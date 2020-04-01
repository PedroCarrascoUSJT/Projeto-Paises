import static org.junit.Assert.assertEquals;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.util.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTest {

	Pais pais, copia;
	static int id = 0;

	
	@Before
	public void setUp() throws Exception {
		System.out.println("setup");
		pais = new Pais(id, "Vietnã", 90623000, 331212);
		copia = new Pais(id, "Vietnã", 90623000, 331212);
		System.out.println(pais);
		System.out.println(copia);
		System.out.println(id);
	}
	
	@Test
	public void test00Carregar() {
		System.out.println("carregar");
		Pais fixture = new Pais(1, "Brasil", 190755799, 8514876.00 );
		Pais novo = new Pais(1, null, -1, -1);
		System.out.println("ID ANTES DA CONSULTA:"+novo.getId());
		novo.carregar();
		System.out.println(fixture);
		System.out.println(novo);
		assertEquals("testa inclusao", novo, fixture);
	}

	@Test
	public void test01Criar() {
		System.out.println("criar");
		pais.criar();
		id = pais.getId();
		System.out.println(id);
		copia.setId(id);
		assertEquals("testa criacao", pais, copia);
	}

	@Test
	public void test02Atualizar() {
		System.out.println("atualizar");
		pais.setPopulacao(12342312135623123L);
		copia.setPopulacao(12342312135623123L);		
		pais.atualizar();
		pais.carregar();
		assertEquals("testa atualizacao", pais, copia);
	}

	@Test
	public void test03Excluir() {
		System.out.println("excluir");
		copia.setId(-1);
		copia.setNome(null);
		copia.setPopulacao(-1);
		copia.setArea(-1);
		pais.excluir();
		pais.carregar();
		assertEquals("testa exclusao", pais, copia);
	}
	
	@Test
	public void test04CarregarMaiorPopulacao() {
		System.out.println("carregar pais com maior populacao");
		copia.setId(6);
		copia.setNome("Vietnã");
		copia.setPopulacao(12342312135623123L);
		copia.setArea(331212.00);
		pais.carregaMaiorPopulacao();
		assertEquals("testa carregar maior populacao", pais, copia);
	}
	
	@Test
	public void test05CarregarMenorArea() {
		System.out.println("carregar pais com menor area");
		copia.setId(6);
		copia.setNome("Vietnã");
		copia.setPopulacao(12342312135623123L);
		copia.setArea(331212.00);
		pais.carregarMenorArea();
		assertEquals("testa carregar menor area", pais, copia);
	}
	
	@Test
	public void test05vetorTresPaises() {
		System.out.println("carregar vetor 3 paises");
		Object[] paisesComparar = new Object[3];
		paisesComparar[0] = new Pais(1, "Brasil", 190755799, 8514876.00);
		paisesComparar[1] = new Pais(2, "Japão", 127300000, 377899.00);
		paisesComparar[2] = new Pais(3, "Canadá", 35546000, 9984670.00);
		
		Object[] paises = pais.vetorTresPaises().toArray();
		
		
		assertArrayEquals("testa carregar vetor 3 paises", paises, paisesComparar);
	}
}
