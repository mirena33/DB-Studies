import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class Main {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "minions_db";
    private static Connection connection;
    private static String query;
    private static PreparedStatement statement;
    private static BufferedReader reader;

    public static void main(String[] args) throws SQLException, IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456");

        connection =
                DriverManager.getConnection(CONNECTION_STRING + DATABASE_NAME, properties);


        // 2. Get Villains’ Names
        getVillainsNamesAndMinions();

        // 3. Get Minion Names
        getMinionsNames();

        // 4. Add Minion
        addMinionExercise();

        // 9. Increase Age Stored Procedure
        increaseAgeWithStoredProcedure();

    }

    private static void increaseAgeWithStoredProcedure() throws IOException, SQLException {
        System.out.println("Enter minion id: ");
        int minionId = Integer.parseInt(reader.readLine());
        query = "CALL usp_get_older(?)";

        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setInt(1, minionId);
        callableStatement.execute();
    }

    private static void addMinionExercise() throws IOException, SQLException {
        System.out.println("Enter minion parameters: ");
        String[] minionParameters = reader.readLine().split("\\s+");

        String minionName = minionParameters[0];
        int minionAge = Integer.parseInt(minionParameters[1]);
        String minionTown = minionParameters[2];

        System.out.println("Enter villain's name: ");
        String villainName = reader.readLine();

        if (!checkIfEntityExistsByName(minionTown, "minions")) {

            insertEntityInTown(minionTown);
        }
    }

    private static void insertEntityInTown(String minionTown) throws SQLException {
        query = "INSERT INTO towns (name, country) value(?, ?)";
        statement = connection.prepareStatement(query);
        statement.setString(1, minionTown);
        statement.setString(2, "NULL");

        statement.execute();

    }

    private static boolean checkIfEntityExistsByName(String entity, String table) throws SQLException {
        query = "SELECT * FROM " + table + " WHERE name = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, entity);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }

    private static void getMinionsNames() throws IOException, SQLException {
        System.out.println("Enter villain ID: ");
        int villain_id = Integer.parseInt(reader.readLine());

        if (!checkIfEntityExists(villain_id, "villains")) {
            System.out.printf("No villain with ID %d exists in the database.", villain_id);
            return;
        }

        System.out.printf("Villain: %s\n", getEntityById(villain_id, "villains"));
        getMinionsNameAgeByVillainId(villain_id);
    }

    private static void getMinionsNameAgeByVillainId(int id) throws SQLException {
        query = "SELECT m.name, m.age FROM minions AS m\n" +
                "JOIN minions_villains AS mv ON m.id = mv.minion_id\n" +
                "WHERE mv.villain_id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        int minionNumber = 0;
        while (resultSet.next()) {
            System.out.printf("%d. %s %d\n",
                    ++minionNumber,
                    resultSet.getString("name"),
                    resultSet.getInt("age"));
        }
    }

    private static String getEntityById(int id, String table) throws SQLException {
        query = "SELECT name FROM " + table + " WHERE id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next() ? resultSet.getString("name") : null;
    }

    private static boolean checkIfEntityExists(int id, String table) throws SQLException {
        query = "SELECT * FROM " + table + " WHERE id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }

    private static void getVillainsNamesAndMinions() throws SQLException {
        query = "SELECT v.name, COUNT(mv.minion_id) AS 'count'\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains AS mv\n" +
                "ON v.id = mv.villain_id\n" +
                "GROUP BY v.name\n" +
                "HAVING `count` > 15\n" +
                "ORDER BY `count` DESC";

        statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d\n",
                    resultSet.getString("name"),
                    resultSet.getInt("count"));
        }
    }
}
