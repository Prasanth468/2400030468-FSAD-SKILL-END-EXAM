package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Date;

public class ClientDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Inventory.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {
            // Insert
            session.beginTransaction();
            Inventory inv = new Inventory("Laptop", "Dell Laptop", new Date(), "Available");
            session.save(inv);
            session.getTransaction().commit();
            System.out.println("Inserted ID: " + inv.getId());

            // Delete
            session.beginTransaction();
            Inventory del = session.get(Inventory.class, inv.getId());
            if (del != null) {
                session.delete(del);
                System.out.println("Deleted ID: " + del.getId());
            }
            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }
    }
}
