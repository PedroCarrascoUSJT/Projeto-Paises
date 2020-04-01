
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Pais {

	private int id;
	private String nome;
	private long populacao;
	private double area;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Pais(int id, String nome, long populacao, double area) {
		
		setId(id);
		setNome(nome);
		setPopulacao(populacao);
		setArea(area);
		
	}
	
	public Connection obtemConexao() throws SQLException {
		return DriverManager
				.getConnection("jdbc:mysql://localhost:3306/Exercicio?useTimezone=true&serverTimezone=America/Sao_Paulo&user=root&password=120101");
	}
	
	public void criar() {
		String sqlInsert = "INSERT INTO Pais(nome, populacao, area) VALUES (?, ?, ?)";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, getNome());
			stm.setLong(2, getPopulacao());
			stm.setDouble(3, getArea());
			stm.execute();
			String sqlQuery  = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
				ResultSet rs = stm2.executeQuery();) {
				if(rs.next()){
					setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar() {
		String sqlUpdate = "UPDATE Pais SET nome=?, populacao=?, area=? WHERE id=?";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, getNome());
			stm.setLong(2, getPopulacao());
			stm.setDouble(3, getArea());
			stm.setInt(4, getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluir() {
		String sqlDelete = "DELETE FROM Pais WHERE id = ?";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void carregar() {
		String sqlSelect = "SELECT nome, populacao, area FROM Pais WHERE Pais.id = ?";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setNome(rs.getString("nome"));
					setPopulacao(rs.getLong("populacao"));
					setArea(rs.getDouble("area"));
				} else {
					setId(-1);
					setNome(null);
					setPopulacao(-1);
					setArea(-1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
	}
	
	public void carregaMaiorPopulacao() {
		String sqlSelect = "SELECT id,nome, populacao, area FROM Pais ORDER BY populacao DESC LIMIT 1";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setId(rs.getInt("id"));
					setNome(rs.getString("nome"));
					setPopulacao(rs.getLong("populacao"));
					setArea(rs.getDouble("area"));
				} else {
					setId(-1);
					setNome(null);
					setPopulacao(-1);
					setArea(-1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
	}
	
	public void carregarMenorArea() {
		String sqlSelect = "SELECT id,nome, populacao, area FROM Pais ORDER BY area ASC LIMIT 1";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setId(rs.getInt("id"));
					setNome(rs.getString("nome"));
					setPopulacao(rs.getLong("populacao"));
					setArea(rs.getDouble("area"));
				} else {
					setId(-1);
					setNome(null);
					setPopulacao(-1);
					setArea(-1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
	}
	
	public ArrayList<Pais> vetorTresPaises() {
		ArrayList<Pais> paises = null;
		System.out.println("3 PAISES");
		String sqlSelect = "SELECT id,nome, populacao, area FROM Pais LIMIT 3";
		try (Connection conn = obtemConexao();
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
