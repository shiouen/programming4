package be.kdg.prog4.reflection.dao;

public interface Dao {
    void createTable();
    void create(Object object);
    Object retrieve(String key);
    void update(Object object);
    void delete(Object object);
}
