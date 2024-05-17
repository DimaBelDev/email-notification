package com.example.emailnotification.repository;

import com.example.emailnotification.entity.LocalUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Repository
public class LocalUserRepository {

    private DataSource dataSource;

    public LocalUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void addLocalUser(LocalUser localUser) {

        try (Connection connection = dataSource.getConnection()) {
            String sql = "insert into t_user (email, password, firstname, lastname) values(?,?,?,?)";

            try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, localUser.getEmail());
                statement.setString(2, localUser.getPassword());
                statement.setString(3, localUser.getFirstname());
                statement.setString(4, localUser.getLastname());

                int affectedRows = statement.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            long id = generatedKeys.getLong(1);
                            log.info("New record inserted with id: {}", id);
                        }
                    }
                } else {
                    log.error("Insertion failed, no records were modified.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LocalUser findByEmail(String email) {
        LocalUser localUser = null;

        try (Connection connection = dataSource.getConnection()) {
            String sql = "select * from t_user where email = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        localUser = LocalUser.builder()
                                        .id(resultSet.getLong("id"))
                                        .email(resultSet.getString("email"))
                                        .password(resultSet.getString("password"))
                                        .firstname(resultSet.getString("firstname"))
                                        .lastname(resultSet.getString("lastname"))
                                        .build();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return localUser;
    }

}
