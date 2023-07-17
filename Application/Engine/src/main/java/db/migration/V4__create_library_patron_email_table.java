package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class V4__create_library_patron_email_table extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws SQLException {
        Connection connection = context.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(
                "ALTER TABLE books ADD COLUMN patron_email TEXT")) {
            statement.execute();
        }
    }
}
