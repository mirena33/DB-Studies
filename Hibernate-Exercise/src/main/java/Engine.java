import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Engine implements Runnable {
    private final EntityManager entityManager;
    private final BufferedReader reader;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
//         Ex.1 - done

//         Ex.2
        this.removeObjectsEx();

//         Ex.3
        try {
            this.containsEmployeeEx();
        } catch (IOException e) {
            e.printStackTrace();
        }

//         Ex.4
          this.employeeWithSalaryOver50k();

//         Ex.5
        this.employeesFromDepartmentEx();

//         Ex.6
        try {
            this.addingNewAddressToEmployeeEx();
        } catch (IOException e) {
            e.printStackTrace();
        }

//         Ex.7
         this.addressesWithEmployeeCountEx();

//        Ex.8
        try {
            this.getEmployeeWithProject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void removeObjectsEx() {
        List<Town> towns = this.entityManager.
                createQuery("SELECT t FROM Town AS t " +
                        "WHERE length(t.name) > 5", Town.class)
                .getResultList();

        this.entityManager.getTransaction().begin();
        towns.forEach(this.entityManager::detach);

        for (Town town : towns) {
            town.setName(town.getName().toLowerCase());
        }

        towns.forEach(this.entityManager::merge);
        this.entityManager.flush();
        this.entityManager.getTransaction().commit();

//        Another solution:
//        this.entityManager.createQuery("UPDATE Town AS t SET t.name = upper(t.name) " +
//                "WHERE length(t.name) > 5", Town.class);
    }

    private void containsEmployeeEx() throws IOException {
        System.out.println("Enter employee full name: ");
        String fullName = reader.readLine();

        try {
            Employee employee = this.entityManager
                    .createQuery("SELECT e FROM Employee AS e " +
                            "WHERE CONCAT(e.firstName, ' ', e.lastName) = :name", Employee.class)
                    .setParameter("name", fullName)
                    .getSingleResult();

            System.out.println("Yes");

        } catch (NoResultException e) {
            System.out.println("No");
        }
    }

    private void employeeWithSalaryOver50k() {
        this.entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                        "WHERE e.salary > 50000", Employee.class)
                .getResultStream()
                .forEach(e -> System.out.printf("%s%n", e.getFirstName()));
    }

    private void employeesFromDepartmentEx() {
        this.entityManager.createQuery("SELECT e FROM Employee AS e " +
                "WHERE e.department.name = 'Research and Development' " +
                "ORDER BY e.salary, e.id", Employee.class)
                .getResultStream()
                .forEach(e -> {
                    System.out.printf("%s %s from Research and Development - $%.2f\n",
                            e.getFirstName(),
                            e.getLastName(),
                            e.getSalary());
                });
    }

    private void addingNewAddressToEmployeeEx() throws IOException {
        System.out.println("Enter employee last name:");
        String lastName = this.reader.readLine();

        Employee employee = this.entityManager.createQuery("SELECT e FROM Employee AS e " +
                "WHERE e.lastName = :name", Employee.class)
                .setParameter("name", lastName)
                .getSingleResult();

        Address address = this.createNewAddress("Vitoshka 15");
        this.entityManager.getTransaction().begin();

        this.entityManager.detach(employee);
        employee.setAddress(address);
        this.entityManager.merge(employee);
        this.entityManager.flush();

        this.entityManager.getTransaction().commit();
    }

    private Address createNewAddress(String textContent) {
        Address address = new Address();
        address.setText(textContent);

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(address);
        this.entityManager.getTransaction().commit();

        return address;
    }

    private void addressesWithEmployeeCountEx() {
        List<Employee> employees = this.entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                        "ORDER BY e.address.employees.size DESC ", Employee.class)
                .getResultList();

        employees
                .forEach(e -> {
                    System.out.printf("%s, %s - %d employees\n",
                            e.getAddress().getText(),
                            e.getAddress().getTown().getName(),
                            e.getAddress().getEmployees().size());
                });

    }

    private void getEmployeeWithProject() throws IOException {
        System.out.println("Enter employee id:");
        int id = Integer.parseInt(reader.readLine());

        Employee employee = this.entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                        "WHERE e.projects.size IS NOT NULL AND e.id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();

        System.out.printf("%s %s - %s\n%s",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle(),
                employee.getProjects()
        );
    }
}
