import java.awt.EventQueue;
import java.sql.*;
import java.util.ArrayList;


public class DataConnector implements AutoCloseable {
	private Connection con;
	private final static String search = "SELECT * FROM coffee WHERE type=?";
	private final static String update = "UPDATE coffee SET quantityInStock=? WHERE id=?";
	private final static String insert = "INSERT INTO coffee(ID, brand, type, pricePerKg, quantityInStock) VALUES(?, ?, ?, ?, ?)";
	private final static String delete = "DELETE FROM coffee WHERE id=?";
	
	private Connection establishConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/coffeeshop", "root", "root");
	}
	
	public DataConnector() throws SQLException {
		this.con = establishConnection();
	}
	
	@Override
	public void close() throws Exception {
		con.close();
		
	}
	
	public ArrayList<Coffee> displayAll() throws SQLException{
		ArrayList<Coffee> coffeeData = new ArrayList<>();
		PreparedStatement st = con.prepareStatement("SELECT * FROM coffee");
		ResultSet result = st.executeQuery();
		while (result.next()) {
			Coffee coffee = new Coffee(
					result.getInt(1),
					result.getString(2),
					result.getString(3),
					result.getFloat(4),
					result.getFloat(5)
					);
			coffeeData.add(coffee);
		}
		return coffeeData;
	}
	
	public ArrayList<Coffee> searchByType(String getType) throws SQLException{
		ArrayList<Coffee> coffeeData = new ArrayList<>();
		PreparedStatement st = con.prepareStatement(search);
		st.setString(1, getType);
		ResultSet result = st.executeQuery();
		while (result.next()) {
			Coffee coffee = new Coffee(
					result.getInt(1),
					result.getString(2),
					result.getString(3),
					result.getFloat(4),
					result.getFloat(5)
					);
			coffeeData.add(coffee);
		}
		return coffeeData;
	}
	public float updateQuantity (float quantity, int id) throws SQLException {
		PreparedStatement st = con.prepareStatement(update);
		st.setFloat(1, quantity);
		st.setInt(2, id);
		return st.executeUpdate();
	}
	public int insertData(int id, String brand, String type, float price, float quantity) throws SQLException {
		PreparedStatement st = con.prepareStatement(insert);
		st.setInt(1, id);
		st.setString(2, brand);
		st.setString(3, type);
		st.setFloat(4, price);
		st.setFloat(5, quantity);
		return st.executeUpdate();
	}
	public int deleteData(int id) throws SQLException{
		PreparedStatement st = con.prepareStatement(delete);
		st.setInt(1, id);
		return st.executeUpdate();
	}

}
