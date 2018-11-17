package digital.mercy.backend.utils;

import digital.mercy.backend.db.AuthEntity;
import digital.mercy.backend.db.ClientsEntity;
import digital.mercy.backend.db.OrganizationsEntity;
import digital.mercy.backend.db.WalletsEntity;
import digital.mercy.backend.webapi.Auth.AuthReq;
import digital.mercy.backend.webapi.account.OrgRequest;
import digital.mercy.backend.webapi.account.PersonRequest;
import org.hibernate.*;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.cfg.Configuration;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
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

    public void setClient(PersonRequest personRequest, String address, String ownertype) {

        AuthEntity authEntity = new AuthEntity();
        ClientsEntity clientsEntity = new ClientsEntity();
        WalletsEntity walletsEntity = new WalletsEntity();

        final Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();

        authEntity.setLogin(personRequest.getAccountName());
        authEntity.setPassword(personRequest.getPassword());

//        session.persist(authEntity);
//        tx.commit();
//
//        Transaction tx2 = session.beginTransaction();

        clientsEntity.setLoginId(authEntity.getLoginId());
        clientsEntity.setFirstName(personRequest.getFirstName());
        //client.setBirthDate(personRequest.getBirthDate());
        clientsEntity.setRegion(personRequest.getRegion());
        clientsEntity.setLastName(personRequest.getLastName());
        clientsEntity.setAuthByLoginId(authEntity);

        walletsEntity.setAddress(address);
        walletsEntity.setOwnerType(ownertype);
        walletsEntity.setAuthByLoginId(authEntity);

        session.persist(authEntity);
        session.persist(clientsEntity);
        session.persist(walletsEntity);

        tx.commit();
    }

    public void setClient(OrgRequest orgRequest, String address, String ownertype) {

        AuthEntity authEntity = new AuthEntity();
        OrganizationsEntity organizationsEntity = new OrganizationsEntity();
        WalletsEntity walletsEntity = new WalletsEntity();

        final Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();

        authEntity.setLogin(orgRequest.getAccountName());
        authEntity.setPassword(orgRequest.getPassword());
        session.persist(authEntity);
        tx.commit();

        Transaction tx2 = session.beginTransaction();

        organizationsEntity.setLoginId(authEntity.getLoginId());
        organizationsEntity.setLogin(organizationsEntity.getLogin());
        organizationsEntity.setLegalAddress(organizationsEntity.getLegalAddress());
        organizationsEntity.setInn(organizationsEntity.getInn());

        walletsEntity.setAddress(address);
        walletsEntity.setOwnerType(ownertype);
        walletsEntity.setAuthByLoginId(authEntity);

        session.persist(organizationsEntity);
        session.persist(walletsEntity);

        tx2.commit();
    }


    public void getClientInfo(PersonRequest personRequest) {

        ClientsEntity client = new ClientsEntity();

        final Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();

    }

    public boolean auth(AuthReq authReq) {

        final Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();

        Query qCnt = session.getNamedQuery("checkLogin").
                setParameter("login", authReq.getAccountName())
                .setParameter("password", authReq.getPassword());
        Long authCnt = (Long) qCnt.getSingleResult();

        tx.commit();

        return authCnt.intValue() == 1;
    }

}