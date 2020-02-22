import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        String appConfigPath = Main.class.getClassLoader().getResource("db.properties").getPath();

        try {
            props.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //1. Load jdbc driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Driver loaded successfully");

        //2. Connect to DB
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni?useSSL=false", props);

        System.out.println("Connected successfully");


        PreparedStatement stmt =
                connection.prepareStatement("SELECT * FROM employees WHERE salary > ?");

        System.out.println("Enter minimal salary(default 20 000): ");
        String salaryStr = scanner.nextLine().trim();
        double salary = salaryStr.equals("") ? 20000 : Double.parseDouble(salaryStr);

        stmt.setDouble(1, salary); //the index of the question mark in the statement, SQL style
        ResultSet rs = stmt.executeQuery(); // ResultSet has no connection to the db; read-only

        while (rs.next()) {
            System.out.printf("| %-15.15s | %-15.15s | %-10.2f |\n",
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDouble("salary"));
            //we can get the data by index of columns
        }

        connection.close();

    }
}
