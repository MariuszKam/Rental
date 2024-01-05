package com.solvd.persistence.utilities;

import com.solvd.persistence.connection.MyBaitsConfig;
import com.solvd.persistence.persons.customer.CustomerRepository;
import org.apache.ibatis.session.SqlSession;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class RepositoryUtility {

    public static <T> void setIdFromDatabase(T object, PreparedStatement preparedStatement, IdSetter<T> idSetter) throws SQLException {
        try (ResultSet key = preparedStatement.getGeneratedKeys()) {
            if (key.next()) {
                idSetter.setId(object, key.getLong(1));
            }
        }
    }

    public static <T, R> R executeTypeSQL(Class<T> repositoryClass, Function<T, R> sqlOperation) {
        try (SqlSession sqlSession = MyBaitsConfig.getSqlSessionFactory().openSession()) {
            T repository = sqlSession.getMapper(repositoryClass);
            return sqlOperation.apply(repository);
        }
    }

    public static <T> void executeVoidSQL(Class<T> repositoryClass, Consumer<T> sqlOperation) {
        try (SqlSession sqlSession = MyBaitsConfig.getSqlSessionFactory().openSession()) {
            T repository = sqlSession.getMapper(repositoryClass);
            sqlOperation.accept(repository);
            sqlSession.commit();
        }
    }

    public static <T> List<T> executeListSQL(Class<T> repositoryClass, Function<T, List<T>> sqlOperation) {
        try (SqlSession sqlSession = MyBaitsConfig.getSqlSessionFactory().openSession()) {
            T repository = sqlSession.getMapper(repositoryClass);
            return sqlOperation.apply(repository);
        }
    }
}
