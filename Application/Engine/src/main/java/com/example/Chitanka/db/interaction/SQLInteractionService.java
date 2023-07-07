package com.example.Chitanka.db.interaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class SQLInteractionService {
    @Autowired
    private final DataSource dataSource;

    public boolean getBookLoanStatusById(UUID bookId) {
        boolean loaned = false;

        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT loaned FROM books WHERE id = ?")) {
            statement.setObject(1, bookId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    loaned = resultSet.getBoolean("loaned");
                    log.info("Book {} is loaned: {}", bookId, loaned );
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(String.format("Book: %s has not been found in the database.", bookId));
        }

        return loaned;
    }

    public boolean loanBookById(UUID bookId) {
        boolean loaned = false;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT loaned FROM books WHERE id = ?");
            selectStatement.setObject(1, bookId);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    loaned = resultSet.getBoolean("loaned");
                }
            }

            PreparedStatement updateStatement = connection.prepareStatement("UPDATE books SET loaned = ? WHERE id = ?");
            updateStatement.setBoolean(1, !loaned);
            updateStatement.setObject(2, bookId);
            updateStatement.executeUpdate();
            log.info("Book {} is loaned: {}", bookId, !loaned);

        } catch (SQLException e) {
            throw new IllegalArgumentException(String.format("Book: %s has not been found in the database.", bookId));
        }
        return !loaned;
    }


}
