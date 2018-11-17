package digital.mercy.backend.utils;

import com.google.gson.Gson;
import digital.mercy.backend.db.AuthEntity;
import digital.mercy.backend.db.ClientsEntity;
import digital.mercy.backend.db.OrganizationsEntity;
import digital.mercy.backend.db.WalletsEntity;
import digital.mercy.backend.webapi.auth.AuthReq;
import digital.mercy.backend.webapi.account.OrgRequest;
import digital.mercy.backend.webapi.account.PersonRequest;
import digital.mercy.backend.webapi.info.AccInfoResp;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

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

        session.persist(authEntity);
        tx.commit();

        Transaction tx2 = session.beginTransaction();

        clientsEntity.setFirstName(personRequest.getFirstName());
        //client.setBirthDate(personRequest.getBirthDate());
        clientsEntity.setLogin(authEntity.getLogin());
        clientsEntity.setRegion(personRequest.getRegion());
        clientsEntity.setLastName(personRequest.getLastName());
        clientsEntity.setAuthByLogin(authEntity);

        session.persist(clientsEntity);
        tx2.commit();

        Transaction tx3 = session.beginTransaction();
        walletsEntity.setLogin(authEntity.getLogin());
        walletsEntity.setAddress(address);
        walletsEntity.setOwnerType(ownertype);
        walletsEntity.setAuthByLogin(authEntity);


        session.persist(walletsEntity);
        tx3.commit();


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

        organizationsEntity.setLogin(authEntity.getLogin());
        organizationsEntity.setInn(orgRequest.getInn().toString());
        organizationsEntity.setOrganizationName(orgRequest.getOrganizationName());
        organizationsEntity.setAuthByLogin(authEntity);
        session.persist(organizationsEntity);
        tx2.commit();

        Transaction tx3 = session.beginTransaction();
        walletsEntity.setLogin(authEntity.getLogin());
        walletsEntity.setAddress(address);
        walletsEntity.setOwnerType(ownertype);
        walletsEntity.setAuthByLogin(authEntity);

        session.persist(walletsEntity);
        tx3.commit();
    }


    public String getAccInfo(String type, String account) {

        final Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();
        Gson gson = new Gson();

        AccInfoResp accInfoResp = new AccInfoResp();
        switch (type) {
            case "person":{
                //firstName,lastName,region,job,phone
                Query qType = session.getNamedQuery("clientInfo").
                        setParameter("login", account);
                List<Object[]> resultList = qType.getResultList();

                for (Object[] row : resultList){
                    accInfoResp.setAccountName(account);
                    accInfoResp.setFirstName(row[0].toString());
                    accInfoResp.setLastName(row[1].toString());
                    accInfoResp.setRegion(row[2].toString());
                    accInfoResp.setJob(row[3].toString());
                    accInfoResp.setPhone(row[4].toString());
                }

                tx.commit();
                return gson.toJson(accInfoResp);

            }
            case "org":{
                //SELECT organizationName,legalAddress,coalesce(founders,'null'),coalesce(ogrn,'null'), " +
                //                        "coalesce(inn,'null') FROM OrganizationsEntity WHERE login = :login"
                Query qType = session.getNamedQuery("orgInfo").
                        setParameter("login", account);
                List<Object[]> resultList = qType.getResultList();

                for (Object[] row : resultList){
                    accInfoResp.setAccountName(account);
                    accInfoResp.setOrganizationName(row[0].toString());
                    accInfoResp.setLegalAddress(row[1].toString());
                    accInfoResp.setFounders(row[2].toString());
                    accInfoResp.setOgrn(row[3].toString());
                    accInfoResp.setInn(row[4].toString());
                }

                tx.commit();
                return gson.toJson(accInfoResp);

            }
        }
        return "No such";
    }

    public boolean auth(AuthReq authReq) {

        final Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();

        Query qCnt = session.getNamedQuery("checkLogin")
                .setParameter("login", authReq.getAccountName())
                .setParameter("password", authReq.getPassword());
        Long authCnt = (Long) qCnt.getSingleResult();

        tx.commit();

        return authCnt.intValue() == 1;
    }

    public List<String> getOrgList() {
        List<String> list = new LinkedList<>();
        final Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();

        Query qLst = session.getNamedQuery("orgList");
        List<Object[]> resultList =qLst.getResultList();

        int i=0;
        for (Object[] row : resultList){
            i++;
            System.out.println(row[i]);
            list.add(row[i].toString());
        }
        tx.commit();

        return list;
    }

    public String getAccType (String login) {

        final Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();

        Query qType = session.getNamedQuery("walletType").
                setParameter("login", login);
        tx.commit();
        return (String) qType.getSingleResult();
    }

}