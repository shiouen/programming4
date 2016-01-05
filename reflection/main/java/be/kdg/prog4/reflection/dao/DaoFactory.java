package be.kdg.prog4.reflection.dao;

import be.kdg.prog4.reflection.HsqlDbManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DaoFactory {
    public static Dao createDao(Class target) {
        InvocationHandler handler = new DaoInvocationHandler(target, new HsqlDbManager());
        Dao dao = (Dao) Proxy.newProxyInstance(Dao.class.getClassLoader(), new Class[] { Dao.class }, handler);
        return dao;
    }
}
