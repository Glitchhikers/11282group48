package com.example.CarpoolConnectBackend.services;

import com.example.CarpoolConnectBackend.model.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

public class ProfileService {

    public int writeProfileToDatabase(Profile profile) {


        SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
        Session session = sFactory.openSession();
        session.beginTransaction();

        if (session.get(Profile.class, profile.getUser()) == null) {
            session.save(profile);
        }
        else {
            session.getTransaction().commit();
            session.close();
            return -1;
        }

        session.getTransaction().commit();
        session.close();

        return 0;
    }

    public Profile checkLogin(Profile profile) {
        String user = profile.getUser();
        String pass = profile.getPass();
        Profile output = profile;

        SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
        Session session = sFactory.openSession();
        session.beginTransaction();

        String hql = "FROM Profile WHERE user = '" + user + "' AND pass = '" + pass + "'";
        Query query = session.createQuery(hql);
        List results = query.list();

        if (results.size() > 0) {
            Iterator it = results.iterator();
            while (it.hasNext()) {
                output = (Profile) it.next();
            }
        }
        else {
            output = null;
        }

        session.getTransaction().commit();
        session.close();

        return output;
    }

}
