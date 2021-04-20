package by.gourianova.apptrainer.db;

import by.gourianova.apptrainer.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {
    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private final static AtomicBoolean CREATE_INSTANCE = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();
    private static ConnectionPool instance;
    private BlockingQueue<ProxyConnection> queue;

    private ConnectionPool() {
        String url = ConfigurationManager.getProperty("dburl");
        String user = ConfigurationManager.getProperty("dbuser");
        String password = ConfigurationManager.getProperty("dbpassword");
        int maxConnections = Integer.parseInt(ConfigurationManager.getProperty("dbmaxconnections"));
        int maxIdle = Integer.parseInt(ConfigurationManager.getProperty("dbmaxIdle"));
        queue = new ArrayBlockingQueue<>(maxConnections);
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            //the first attempt
            for (int i = 0; i < maxConnections; i++) {
                ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(url, user, password));
                queue.put(connection);
            }
            // try again if not get maximum
            if (queue.size() < maxConnections) {
                for (int i = queue.size(); i < maxConnections; i++) {
                    ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(url, user, password));
                    queue.put(connection);
                }
            }
            // if not enough to run app
            if (queue.size() < maxIdle) {
                throw new RuntimeException("Error, when create connection pool");
            }

        } catch (InterruptedException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
        } catch (SQLException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
            throw new RuntimeException("Can`t register sql driver", e);
        }
    }

    public static ConnectionPool getInstance() {
        if (!CREATE_INSTANCE.get()) {
           // lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    CREATE_INSTANCE.set(true);
                }
            } finally {
             //   lock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = queue.take();
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        queue.add(connection);
    }

    public void closeConnectionPool() {
        for (ProxyConnection connection : queue) {
            try {
                connection.connectionClose(queue.take());
            } catch (SQLException | InterruptedException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
            }
        }
        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()){
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
    }
}
