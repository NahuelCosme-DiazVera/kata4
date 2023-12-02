import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class SqlitePersonLoader implements PersonLoader{
    private static final String QUERY = "SELECT id, height, weight\n" +
            "FROM People\n" +
            "WHERE weight >= 60 AND weight <= 80;";
    private Connection connection;

    public SqlitePersonLoader(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Person> load() {
        try {
            return load(getQuery());
        } catch (SQLException e) {
            return emptyList();
        }
    }

    private List<Person> load(ResultSet resultSet) throws SQLException {
        List<Person> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(personFrom(resultSet));
        }
        return list;
    }

    private static Person personFrom(ResultSet resultSet) throws SQLException {
        return new Person(
                resultSet.getInt("id"),
                resultSet.getDouble("height"),
                resultSet.getDouble("weight")
        );
    }

    private ResultSet getQuery() throws SQLException {
        return connection.createStatement().executeQuery(QUERY);
    }
}
