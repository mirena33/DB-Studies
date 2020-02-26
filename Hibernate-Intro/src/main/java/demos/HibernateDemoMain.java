package demos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateDemoMain {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();

        try (SessionFactory sessionFactory = cfg.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            Student student = new Student("Ivan Petkanov");

            try {
                session.beginTransaction();
                session.save(student);
                session.getTransaction().commit();

            } catch (Exception e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
                throw e;
            }

            session.beginTransaction();
            session.createQuery("FROM Student ", Student.class)
                    .setFirstResult(0)
                    .setMaxResults(4)
                    .getResultStream()
                    .forEach(System.out::println);

            session.getTransaction().commit();

        }

    }
}
