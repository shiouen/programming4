package be.kdg.prog4.reflection.dao;

import be.kdg.prog4.reflection.HsqlDbManager;
import be.kdg.prog4.reflection.annotations.Id;
import be.kdg.prog4.reflection.annotations.Storable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DaoInvocationHandler implements InvocationHandler {
    private final Class target;
    private final Field primaryKey;
    private final HsqlDbManager hsqlDbManager;
    private final String table;

    public DaoInvocationHandler(Class target, HsqlDbManager hsqlDbManager) throws IllegalArgumentException {
        this.target = target;
        this.hsqlDbManager = hsqlDbManager;

        if (this.getPrimaryKeyAmount() != 1) {
            throw new IllegalArgumentException("Untolerated amount of primary keys.");
        }
        if (this.target.getAnnotationsByType(Storable.class).length != 1) {
            throw new IllegalArgumentException("Untolerated amount of Storable annotations.");
        }

        this.table = ((Storable) target.getAnnotation(Storable.class)).table();
        this.primaryKey = this.getPrimaryKey();
        this.primaryKey.setAccessible(true);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Object instance;

        switch (methodName) {
            case "createTable":
                this.createTable();
                break;
            case "create":
                instance = args[0];
                this.create(instance);
                break;
            case "retrieve":
                instance = args[0];
                return this.retrieve(instance.toString());
            case "update":
                instance = args[0];
                this.update(instance);
                break;
            case "delete":
                instance = args[0];
                this.delete(instance);
                break;
        }
        return null;
    }

    private void createTable() {
        StringBuilder query = new StringBuilder(
                String.format("CREATE TABLE IF NOT EXISTS %s ( ", this.table)
        );

        for (Field field : this.target.getDeclaredFields()) {
            query.append(field.getName());
            query.append(" VARCHAR(256)");

            if (field.isAnnotationPresent(Id.class)) {
                query.append(" PRIMARY KEY");
            }

            query.append(",");
        }

        query.deleteCharAt(query.length() - 1);
        query.append(");");

        this.hsqlDbManager.execute(query.toString());
    }

    private void create(Object instance) throws IllegalAccessException {
        StringBuilder query = new StringBuilder(
                String.format("INSERT INTO %s ", this.table)
        );

        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("VALUES (");

        for (Field field : this.target.getDeclaredFields()) {
            field.setAccessible(true);

            String column = field.getName();
            String value = field.get(instance).toString();

            columns.append(String.format("%s,", column));
            values.append(String.format("'%s',", value));
        }

        columns.deleteCharAt(columns.length() - 1);
        values.deleteCharAt(values.length() - 1);

        columns.append(")");
        values.append(");");

        query.append(columns);
        query.append(values);

        this.hsqlDbManager.execute(query.toString());
    }

    private Object retrieve(String primaryKey) throws IllegalAccessException, InstantiationException, NoSuchFieldException, SQLException {
        String statement = "SELECT * FROM %s WHERE %s = '%s';";
        String column =  this.primaryKey.getName();
        String query = String.format(statement, this.table, column, primaryKey);

        ResultSet result = this.hsqlDbManager.read(query.toString());
        result.next();
        ResultSetMetaData metaData = result.getMetaData();

        Object retrieved = this.target.newInstance();

        try {
            for (int i = 1; i <= metaData.getColumnCount(); ++i) {
                Field field = this.target.getDeclaredField(metaData.getColumnName(i).toLowerCase());
                field.setAccessible(true);

                if (field.getType().equals(String.class)) {
                    field.set(retrieved, result.getString(i));
                } else {
                    field.setInt(retrieved, Integer.parseInt(result.getString(i)));
                }
            }

            return retrieved;
        } catch (SQLException e) {
            return null;
        }
    }

    private void update(Object instance) throws IllegalAccessException {
        StringBuilder query = new StringBuilder(
                String.format("UPDATE %s SET ", this.table)
        );

        for (Field field : this.target.getDeclaredFields()) {
            field.setAccessible(true);

            String column = field.getName();
            String value = field.get(instance).toString();

            query.append(String.format("%s = '%s',", column, value));
        }

        query.deleteCharAt(query.length() - 1);

        String column = this.primaryKey.getName();
        String value = this.primaryKey.get(instance).toString();
        query.append(String.format(" WHERE %s = '%s';", column, value));

        this.hsqlDbManager.execute(query.toString());
    }

    private void delete(Object instance) throws IllegalAccessException {
        String statement = "DELETE FROM %s WHERE %s = '%s';";
        String column = this.primaryKey.getName();
        String value = this.primaryKey.get(instance).toString();

        String query = String.format(statement, this.table, column, value);
        this.hsqlDbManager.execute(query);
    }

    private Field getPrimaryKey() {
        for (Field field : target.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }
        return null;
    }
    private int getPrimaryKeyAmount() {
        int amount = 0;
        for (Field field : target.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                ++amount;
            }
        }
        return amount;
    }

}
