package Utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static final SessionFactory sessionFactory = buildSessionFactory();


    public static Configuration getHibernateConfiguration() {

        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(Models.AccountingSeat.class);


        return configuration.configure("hibernate.cfg.xml");
    }


    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return getHibernateConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
