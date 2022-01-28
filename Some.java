import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lombok.Getter;
import some.otherpackage.office;

public class Some {

    // Get employes which are working in both organizations
    // both lists could contain really HUGE amount of employees and it would be called frequently
    List<Employee> getSharedEmployees(Organization a, Organization b) {

        List<Employee> employees = new ArrayList<>();
        for(Employee e1 : a.getEmployees2()) {
            if(b.getEmployees2().contains(e1)) {
                employees.add(e1);
            }
        }
        return employees;
    }

    /**
     * Calculate the optimal number of managers to avoid too many managers for one employee
     * @param bossN
     * @param emloyeeN
     * @return
     */
    public static int getPreferredNumberOfManagers(int bossN, int emloyeeN) {
        try {
            double coef = bossN/emloyeeN;

            if(coef > 1)
                return bossN/3;
            else
                return bossN;

        } catch (ArithmeticException exception) {
            System.out.println(exception.getStackTrace());
            throw exception;
        } finally {
            return 1;
        }
    }

    static class Employee {
        Employee(String firstname, String lastname) {
            this.firstname = firstname;
            this.lastname = lastname;
        }

        String firstname;
        String lastname;
        int age = 0;
    }

    static class BigBoss {
        BigBoss(String firstname, String lastname) {
            this.firstname = firstname;
            this.lastname = lastname;
        }

        String firstname;
        String lastname;
        int age = 0;
    }

    @Getter
    @Setter
    static class Organization {
        int bossNumber;
        int employeeNumber;

        List<BigBoss> employees1;
        List<Employee> employees2;
        Office office = new Office();

        Organization(int bossN, int emloyeeN) {
            this.bossNumber = bossN;
            this.employeeNumber = emloyeeN;
            if (getPreferredNumberOfManagers(bossN, employeeNumber) != bossN)
                throw new RuntimeException("Плохая структура организации. Начальников должно быть меньше");
        }

        static public Organization loadFromFile(String file, int bossN, int emloyeeN) {
            File f = new File(file);

            Scanner scanner = new Scanner(f);

            // загружаем начальников из файла
            BigBoss boss = new BigBoss;
            for (int i = 0; i < bossN; i++) {
                boss.firstname = scanner.next();
                boss.lastname = scanner.next();
                boss.age = scanner.nextInt();
                employees2.add(boss);
            }

            // загружаем обычных сотрудников из файла
            int i = 0;
            while (!scanner.hasNext() && i < emloyeeN) {
                Employee employee = new Employee;
                boss.firstname = scanner.next();
                boss.lastname = scanner.next();
                boss.age = scanner.nextInt();
                employees2.add(employee);
                i++;
            }
            return null;
        }
    }
}
