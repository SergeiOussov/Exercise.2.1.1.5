package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

public class Util {
    private static Connection connection;
    static final String hostName = "localhost";
    static final String dbName = "dbusers";
    static final String userName = "root1";
    static final String password = "root1";
    static final String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
    static final String sessionFactoryURL = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false";
    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String dialect = "org.hibernate.dialect.MySQLDialect";
    static final String showSQL = "true";
    static final String currentSessionContextClass = "thread";
    static final String HBMtoDDLAuto = "";

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, driver);
                settings.put(Environment.URL, sessionFactoryURL);
                settings.put(Environment.USER, userName);
                settings.put(Environment.PASS, password);
                settings.put(Environment.DIALECT, dialect);
                settings.put(Environment.SHOW_SQL, showSQL);
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, currentSessionContextClass);
                settings.put(Environment.HBM2DDL_AUTO, HBMtoDDLAuto);
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void closeFactory() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(connectionURL, userName, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (
                    SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
