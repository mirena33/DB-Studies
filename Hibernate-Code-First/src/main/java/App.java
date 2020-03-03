import entities.universitySystem.Person;
import entities.universitySystem.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    private static final String GRINGOTTS_PU = "gringotts";
    private static final String SALES_PU = "sales";
    private static final String UNIVERSITY_PU = "university";
    private static final String HOSPITAL_PU = "hospital";
    private static final String BILLS_PU = "bills";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory(BILLS_PU);

//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Engine engine = new Engine(entityManager);
//        engine.run();
    }
}
