package digital.mercy.backend.utils;

import digital.mercy.backend.db.AuthEntity;
import digital.mercy.backend.db.ClientsEntity;
import digital.mercy.backend.webapi.account.PersonRequest;
import org.hibernate.*;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.PersistenceException;
import javax.persistence.metamodel.EntityType;

import java.sql.SQLException;
import java.util.Map;

public class HibernateUtils {

    private static final SessionFactory ourSessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public void setClient(PersonRequest personRequest){

        AuthEntity authEntity = new AuthEntity();
        ClientsEntity client = new ClientsEntity();

        final Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();

        authEntity.setLogin(personRequest.getAccountName());
        authEntity.setPassword(personRequest.getPassword());

        client.setFirstName(personRequest.getFirstName());

        session.persist(authEntity);
        session.persist(client);

        try {
            tx.commit();
        } catch (ConstraintViolationException e ) {
            System.out.println("Test"+e.getConstraintName());
        }



    }
}