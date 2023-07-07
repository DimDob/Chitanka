package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class V3__create_loaned_table extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws SQLException {
        Connection connection = context.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(
                "ALTER TABLE books ADD COLUMN loaned BOOLEAN DEFAULT FALSE")) {
            statement.execute();
        }
    }
}
