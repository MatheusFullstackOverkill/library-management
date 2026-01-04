package com.magicallibrary.app.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseConnection {
    public static Session hibernateSetup() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        System.out.println("Hibernate setup successfully!");
        
        // // SELECT
        // String queryString = "FROM User";
        // Query<User> query = session.createQuery(queryString, User.class);
        // var usersResult = query.list();

        // for (int i = 0; i < usersResult.size(); i++) {
        //     System.out.println(usersResult.get(i).getEmail());
        // };

        // // INSERT
        // User newUser = new User("queryString", "queryString", "queryString", "queryString", null);
        // session.persist(newUser);
        // System.out.println(newUser.getId());

        // UPDATE
        // String updateQueryString = "UPDATE User set firstName = :firstName WHERE id=:id";
        // MutationQuery updateQuery = session.createMutationQuery(updateQueryString);
        // updateQuery.setParameter("firstName", "Newname");
        // updateQuery.setParameter("id", newUser.getId());
        // var updatedUsersCount = updateQuery.executeUpdate();
        // System.out.println(updatedUsersCount);

        // DELETE
        // String deleteQueryString = "DELETE FROM User WHERE id=:id";
        // MutationQuery deleteQuery = session.createMutationQuery(deleteQueryString);
        // deleteQuery.setParameter("id", newUser.getId());
        // var deletedUsersCount = deleteQuery.executeUpdate();
        // System.out.println(deletedUsersCount);

        return session;
    }
}
