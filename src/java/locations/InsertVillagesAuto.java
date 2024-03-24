package locations;

import java.io.*;
import java.sql.*;
 
public class InsertVillagesAuto {
 
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/pfi";
        String username = "root";
        String password = "123";
 
        String csvFilePath = "C:/Users/User/Desktop/PFISMS/locations/villages/villages1.csv";
 
        int batchSize = 20;
 
        Connection connection = null;
 
        try {
 
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);
 
            String sql = "INSERT INTO village (name,cell_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
 
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;
 
            int count = 0;
 
            lineReader.readLine(); // skip header line
 
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String name = data[0];
                String cell_id = data[1];
                
                statement.setString(1, name);
                statement.setString(2, cell_id);
               
 
                statement.addBatch();
                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
 
            lineReader.close();
 
            // execute the remaining queries
            statement.executeBatch();
 
            connection.commit();
            connection.close();
 
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
 
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
 
    }
}