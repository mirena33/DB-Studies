package demos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaDemoMain {
    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("school");
        EntityManager em = emf.createEntityManager();
        User user = new User("John Doe");

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

        User u = em.find(User.class, 1);
        System.out.println("User: " + u);

        em.getTransaction().begin();
        em.createQuery("SELECT u FROM User AS u", User.class)
                .setFirstResult(1)
                .setMaxResults(3)
                .getResultList()
                .stream()
                .forEach(System.out::println);

        em.getTransaction().commit();


        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery();
        Root<User> r = criteria.from(User.class);
        criteria.select(r).where(builder.like(r.get("name"), "J%"));
        em.createQuery(criteria).getResultList()
                .stream().forEach(System.out::println);



    }
}
