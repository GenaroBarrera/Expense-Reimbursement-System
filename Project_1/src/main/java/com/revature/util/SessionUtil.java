package com.revature.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public abstract class SessionUtil {
    protected static SessionFactory sessionFactory = null;
    public static final Logger logger = LogManager.getLogger(SessionUtil.class.getName());

    public static void config(){
        Configuration config = new Configuration().configure();
        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        sessionFactory = config.buildSessionFactory(builder.build());
    }
}

/* old session class
public class SessionUtil {
    private final Logger logger = LogManager.getLogger(SessionUtil.class.getName()); //set up our logger

    private SessionFactory sf;
    Properties props = new Properties();

    public SessionFactory configSession() {
        try {
            //props.load(new FileReader(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("db.properties")).getFile()));
            FileInputStream fis = new FileInputStream("db.properties");
            props.load(fis);
            fis.close();

            String url = props.getProperty("POSTGRES_DB_URL");
            String username = props.getProperty("POSTGRES_DB_USERNAME");
            String password = props.getProperty("POSTGRES_DB_PASSWORD");

            logger.debug("what's in url "+ url);
            logger.debug("what's in username "+ username);
            logger.debug("what's in password "+ password);

            props.setProperty("hibernate.connection.url", url);
            props.setProperty("hibernate.connection.username", username);
            props.setProperty("hibernate.connection.password", password);
            //are the properties in my xml file redundant now?
            props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
            props.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            props.setProperty("show_sql", "true");
            props.setProperty("hibernate.show_sql", "true");
            props.setProperty("hibernate.format_sql", "true");

            configure(props);
            return this.sf;
        } catch (IOException ex) {
            logger.error(ex);
            return null; //be careful of null pointer exceptions!!!
        }
    }

    private void configure(Properties props){
        Configuration config;
        if(props == null){
            config = new Configuration().configure();
        } else {
            config = new Configuration();
            config.setProperties(props);
            config.addAnnotatedClass(User.class);
            config.addAnnotatedClass(Reimbursement.class);
        }
        if (config != null){
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
            this.sf = config.buildSessionFactory(builder.build());
        }
    }
}
     */
