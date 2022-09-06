package dao;

import model.Carro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class CarroDAO extends DAO {	
	public CarroDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Carro carro) {
		boolean status = false;
		try {
			String sql = "INSERT INTO carros (id, modelo, quantidade, datafabricacao, datacompra, preco) "
		               + "VALUES (" + carro.getId() + ", '" + carro.getModelo() + "', "
		               + carro.getQuantidade() + ", ?, ?, " + carro.getPreco() + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
		    st.setDate(1, Date.valueOf(carro.getDatafabricacao()));
			st.setDate(2, Date.valueOf(carro.getDatacompra()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Carro get(int id) {
		Carro carro = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM carros WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 carro = new Carro(rs.getInt("id"), rs.getString("modelo"), 
                				   rs.getInt("quantidade"), 
        			               rs.getDate("datafabricacao").toLocalDate(),
        			               rs.getDate("datacompra").toLocalDate(),
        			               (float)rs.getDouble("preco"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return carro;
	}
	
	
	public List<Carro> get() {
		return get("");
	}

	
	public List<Carro> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Carro> getOrderByModelo() {
		return get("modelo");		
	}
	
	
	public List<Carro> getOrderByPreco() {
		return get("preco");		
	}
	
	
	private List<Carro> get(String orderBy) {
		List<Carro> carros = new ArrayList<Carro>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM carros" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Carro p = new Carro(rs.getInt("id"), rs.getString("modelo"), 
			     				    rs.getInt("quantidade"), 
					                rs.getDate("datafabricacao").toLocalDate(),
					                rs.getDate("datacompra").toLocalDate(),
					                (float)rs.getDouble("preco"));
	            carros.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return carros;
	}
	
	
	public boolean update(Carro carro) {
		boolean status = false;
		try {  
			String sql = "UPDATE carros SET modelo = '" + carro.getModelo() + "', " 
					   + "quantidade = " + carro.getQuantidade() + ","
					   + "datafabricacao = ?, " 
					   + "datacompra = ? , "
					   + "preco = " + carro.getPreco() + " WHERE id = " + carro.getId();
			PreparedStatement st = conexao.prepareStatement(sql);
		    st.setDate(1, Date.valueOf(carro.getDatafabricacao()));
			st.setDate(2, Date.valueOf(carro.getDatacompra()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM carros WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}