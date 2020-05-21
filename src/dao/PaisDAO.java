package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Pais;

public class PaisDAO {
	
	public int criar(Pais pais) {
		String sqlInsert = "INSERT INTO Pais(nome, populacao, area) VALUES (?, ?, ?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.execute();
			String sqlQuery  = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
				ResultSet rs = stm2.executeQuery();) {
				if(rs.next()){
					pais.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pais.getId();
	}
	
	public void atualizar(Pais pais) {
		String sqlUpdate = "UPDATE Pais SET nome=?, populacao=?, area=? WHERE id=?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.setInt(4, pais.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(int id) {
		String sqlDelete = "DELETE FROM Pais WHERE id = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Pais carregar(int id) {
		Pais pais = null;
		String sqlSelect = "SELECT nome, populacao, area FROM Pais WHERE Pais.id = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais = new Pais(id, rs.getString("nome"), rs.getLong("populacao"), rs.getDouble("area"));
				} else {
					pais = new Pais(-1,null,-1,-1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		return pais;
	}
	
	public Pais carregaMaiorPopulacao() {
		Pais pais = null;
		String sqlSelect = "SELECT id,nome, populacao, area FROM Pais ORDER BY populacao DESC LIMIT 1";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais = new Pais(rs.getInt("id"),rs.getString("nome"),rs.getLong("populacao"),rs.getDouble("area"));
				} else {
					pais = new Pais(-1,null,-1,-1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return pais;
	}
	
	public Pais carregarMenorArea() {
		Pais pais = null;
		String sqlSelect = "SELECT id,nome, populacao, area FROM Pais ORDER BY area ASC LIMIT 1";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais = new Pais(rs.getInt("id"),rs.getString("nome"),rs.getLong("populacao"),rs.getDouble("area"));
				} else {
					pais = new Pais(-1,null,-1,-1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return pais;
	}
	
	public ArrayList<Pais> vetorTresPaises() {
		ArrayList<Pais> paises = null;
		System.out.println("3 PAISES");
		String sqlSelect = "SELECT id,nome, populacao, area FROM Pais LIMIT 3";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				paises = new ArrayList<Pais>();
				while(rs.next()) {
					Pais pais = new Pais(
							rs.getInt("id"),
							rs.getString("nome"),
							rs.getLong("populacao"),
							rs.getDouble("area")
						);
					paises.add(pais);
					System.out.println(pais.toString());
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		return paises;
	}
	

}
