<<<<<<< HEAD
package com.acem.db.config;

import com.acem.db.credential.DbCredential;
import com.acem.db.credential.impl.DbCredentialDotEnvImpl;
import com.acem.db.mapper.RowMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DbConnector {

    private Connection connection;
    private PreparedStatement statement;
    private DbCredential dbCredential;

    public DbConnector(DbCredential dbCredential) {
        this.dbCredential = dbCredential;
    }

    public DbConnector() {
        this.dbCredential = new DbCredentialDotEnvImpl();
    }

    public void connect() throws Exception {
        System.out.println(dbCredential.getUsername());
        String url = "jdbc:mysql://" + dbCredential.getIpAddress() + ":" + dbCredential.getPort() + "/" + dbCredential.getName();
        url += "?useSSL=false&allowPublicKeyRetrieval=true";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, "root", "Electron@2020");
    }

    public void init(String sql) throws Exception {
        statement = connection.prepareStatement(sql);
    }

    public void connectAndInit(String sql) throws Exception {
        connect();
        init(sql);
    }

    public int executeUpdate() throws Exception {
        return statement.executeUpdate();
    }

    public ResultSet executeQuery() throws Exception {
        return statement.executeQuery();
    }

    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public <T> List<T> map(ResultSet resultSet, RowMapper<T> rowMapper) throws Exception {
        List<T> objects = new ArrayList<>();
        while (resultSet.next()) {
            T object = rowMapper.map(resultSet);
            objects.add(object);
        }
        return objects;
    }

    public <T> T mapSingle(ResultSet resultSet, RowMapper<T> rowMapper) throws Exception {
        while (resultSet.next()) {
            T object = rowMapper.map(resultSet);
            return object;
        }
        return null;
    }

    public <T> List<T> executeAndMap(RowMapper<T> rowMapper) throws Exception {
        return map(executeQuery(), rowMapper);
    }

    public <T> T executeAndMapSingle(RowMapper<T> rowMapper) throws Exception {
        return mapSingle(executeQuery(), rowMapper);
    }

    public void mapValue(Object... args) throws Exception {
        int noOfArgs = args.length;
        for (int i = 0; i < noOfArgs; i++) {
            statement.setObject(i + 1, args[i]);
        }
    }

    public <T> List<T> execute(String sql, RowMapper<T> mapper) throws Exception {
        connectAndInit(sql);
        return executeAndMap(mapper);
    }

    public <T> List<T> execute(String sql, RowMapper<T> mapper, Object... args) throws Exception {
        connectAndInit(sql);
        mapValue(args);
        return executeAndMap(mapper);
    }

    public <T> T executeSingle(String sql, RowMapper<T> mapper, Object... args) throws Exception {
        connectAndInit(sql);
        mapValue(args);
        return executeAndMapSingle(mapper);
    }

    public int execute(String sql, Object... args) throws Exception {
        connectAndInit(sql);
        mapValue(args);
        return executeUpdate();
    }
}
=======
package com.acem.db.config;

import com.acem.db.credential.DbCredential;
import com.acem.db.credential.impl.DbCredentialDotEnvImpl;
import com.acem.db.credential.impl.DbCredentialFileImpl;
import com.acem.db.mapper.RowMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DbConnector {

    private Connection connection;
    private PreparedStatement statement;
    private DbCredential dbCredential;

    public DbConnector(DbCredential dbCredential) {
        this.dbCredential = dbCredential;
    }

    public DbConnector() {
        this.dbCredential = new DbCredentialFileImpl();
    }

    public void connect() throws Exception {
        System.out.println(dbCredential.getUsername());
        String url = "jdbc:mysql://" + dbCredential.getIpAddress() + ":" + dbCredential.getPort() + "/" + dbCredential.getName();
        url += "?useSSL=false&allowPublicKeyRetrieval=true";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, dbCredential.getUsername(), dbCredential.getPassword());
    }

    public void init(String sql) throws Exception {
        statement = connection.prepareStatement(sql);
    }

    public void connectAndInit(String sql) throws Exception {
        connect();
        init(sql);
    }

    public int executeUpdate() throws Exception {
        return statement.executeUpdate();
    }

    public ResultSet executeQuery() throws Exception {
        return statement.executeQuery();
    }

    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public <T> List<T> map(ResultSet resultSet, RowMapper<T> rowMapper) throws Exception {
        List<T> objects = new ArrayList<>();
        while (resultSet.next()) {
            T object = rowMapper.map(resultSet);
            objects.add(object);
        }
        return objects;
    }

    public <T> T mapSingle(ResultSet resultSet, RowMapper<T> rowMapper) throws Exception {
        while (resultSet.next()) {
            T object = rowMapper.map(resultSet);
            return object;
        }
        return null;
    }

    public <T> List<T> executeAndMap(RowMapper<T> rowMapper) throws Exception {
        return map(executeQuery(), rowMapper);
    }

    public <T> T executeAndMapSingle(RowMapper<T> rowMapper) throws Exception {
        return mapSingle(executeQuery(), rowMapper);
    }

    public void mapValue(Object... args) throws Exception {
        int noOfArgs = args.length;
        for (int i = 0; i < noOfArgs; i++) {
            statement.setObject(i + 1, args[i]);
        }
    }

    public <T> List<T> execute(String sql, RowMapper<T> mapper) throws Exception {
        connectAndInit(sql);
        return executeAndMap(mapper);
    }

    public <T> List<T> execute(String sql, RowMapper<T> mapper, Object... args) throws Exception {
        connectAndInit(sql);
        mapValue(args);
        return executeAndMap(mapper);
    }

    public <T> T executeSingle(String sql, RowMapper<T> mapper, Object... args) throws Exception {
        connectAndInit(sql);
        mapValue(args);
        return executeAndMapSingle(mapper);
    }

    public int execute(String sql, Object... args) throws Exception {
        connectAndInit(sql);
        mapValue(args);
        return executeUpdate();
    }
}
>>>>>>> f0ec4748da8fd9b4667f85d7e9b8c92d4926e0b0
