package com.solvd.persistence.utilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositoryUtility {

    public static <T> void setIdFromDatabase(T object, PreparedStatement preparedStatement, IdSetter<T> idSetter) throws SQLException {
        try (ResultSet key = preparedStatement.getGeneratedKeys()) {
            if (key.next()) {
                idSetter.setId(object, key.getLong(1));
            }
        }
    }
}
