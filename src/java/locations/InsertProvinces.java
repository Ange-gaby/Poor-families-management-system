package locations;

import java.io.*;
import java.sql.*;
 
public class InsertProvinces {
 
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/pfi";
        String username = "root";
        String password = "123";
 
        String csvFilePath = "C:/Users/Gasana/Desktop/SchoolProjects/PFI/locations/provinces/provinces.csv";
 
        int batchSize = 20;
 
        Connection connection = null;
 
        try {
 
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);
 
            String sql = "INSERT INTO province (id, name) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
 
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;
 
            int count = 0;
 
            lineReader.readLine(); // skip header line
 
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                
                String id = data[0];
                String name = data[1];
                
                statement.setString(1, id);
                statement.setString(2, name);

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