import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:hw_25000.db")) {
            List<Person> people = new SqlitePersonLoader(connection).load();
            for (Person person : people) {
                System.out.println(person);
            }
        }
    }
}
