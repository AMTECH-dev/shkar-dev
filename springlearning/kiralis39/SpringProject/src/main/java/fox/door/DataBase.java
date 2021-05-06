package fox.door;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;


public class DataBase {
	private static Connection conn;
	

    public DataBase() throws ClassNotFoundException {
        try {
        	conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "shkar", "");
        	
        	System.out.println("Data base opened!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void example() {
	    try (Statement statmt = conn.createStatement()) {
			int tInt = statmt.executeQuery("SELECT * FROM type WHERE typename = '';").getInt("id");
			
			if (tInt < 0) {
				System.err.println("DataBase: addNewData(): ERR: tInt is " + tInt);
				return;
			}
	
			try {
				statmt.execute("INSERT INTO 'aids' ("
				   + "'name', 'type', 'description', "
				   + "'modificКРС', 'modificМРС', 'modificHrs', 'modificPig', 'modificPAn', 'modificBrd', 'modificDgz', 'modificCts', 'picpath'"
				   + ") VALUES ("
				   + "'" + "', '" + tInt + "', '" + "', '" + "');");
			} catch (SQLException e) {
				System.out.println("Не удалось создать элемент. Возможно, он уже есть.");
				System.out.println("Попытка обновить запись id #");
				
				Statement updStatmt = conn.createStatement();				
				updStatmt.execute("UPDATE aids SET "
						+ "name='" + "', "
						+ "type='" + tInt + "', "
						+ "description='" + "', "
						+ "modific='" + "', "
						+ "picpath='" + "' "
						+ "WHERE id=" + ";");
				
				updStatmt.close();
			}
			
		} catch (SQLException e) {e.printStackTrace();}
	}
    
    public void removeFromDB(String removableItemName) {
		try (Statement statmt = conn.createStatement()) {
			int correct = statmt.executeQuery("SELECT EXISTS (SELECT * FROM aids WHERE name='" + removableItemName + "' LIMIT 1);").getInt(1);
			
			if (correct != 0) {
				statmt.execute("DELETE FROM aids WHERE aids.name='" + removableItemName + "';");
				JOptionPane.showConfirmDialog(null, 
						"Удаление препарата '" + removableItemName + "' завершено.", 
						"Выполнено!", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showConfirmDialog(null, 
						"Не существует '" + removableItemName + "' в базе!", 
						"Ошибка:", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {e.printStackTrace();}		
	}
    
    public ArrayList<String> getTypeList() {
		ArrayList<String> result = new ArrayList<String>();
		
		try (
				Statement tmp = conn.createStatement(); 
				ResultSet resSet = tmp.executeQuery("SELECT * FROM 'type';")
		) {
			while(resSet.next()) {result.add(resSet.getString("typename"));}
		} catch (SQLException e) {e.printStackTrace();}
		
		return result;
	}
    
    public ArrayList<String> getElementsOfType(String typeChosen) {
		ArrayList<String> result = new ArrayList<String>();
		
		try (
				Statement tmp = conn.createStatement(); 
				ResultSet resSet = tmp.executeQuery("SELECT * FROM aids WHERE type=(SELECT id FROM type WHERE typename='" + typeChosen + "');")
		) {
			while(resSet.next())	{result.add(resSet.getString("name"));}
		} catch (SQLException e) {e.printStackTrace();}
		
		System.out.println("getElementsOfType() result: " + Arrays.asList(result));
		return result;
	}
    
    
    public static void close() {
    	try {    		
    		if (conn != null) {conn.close();}	    	
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	System.out.println("Data base closed!");
    }
}
