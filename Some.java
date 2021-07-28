import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Some {

    // both lists could contain really HUGE amount of employees
    List<Employee> getSharedEmployees(List<Employee> employees1, List<Employee> employees2) {

        List<Employee> employees = new ArrayList<>();
        for(Employee e1 : employees1) {
            if(employees2.contains(e1)) {
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

    static class Office {

    }

    static class Configurations {
        Configurations(int bossN, int emloyeeN) {
            this.bossNumber = bossN;
            this.employeeNumber = emloyeeN;
        }

        int bossNumber;
        int employeeNumber;
    }

    static class Организация {
        Организация(int bossN, int emloyeeN) {
            this.bossNumber = bossN;
            this.employeeNumber = emloyeeN;
            this.office = new Office();
            if (getPreferredNumberOfManagers(bossN, employeeNumber) != bossN)
                throw new RuntimeException("Плохая структура организации. Начальников должно быть меньше");
        }

        Office получитьОфис() {
            return office;
        }

        int bossNumber;
        int employeeNumber;

        List<BigBoss> employees1;
        List<Employee> employees2;
        Office office;

        static Configurations loadFromFile(String file, int bossN, int emloyeeN) {
            Configurations conf = new Configurations(bossN, emloyeeN);

            File f = new File(file);

            Scanner scanner = new Scanner(f);

            BigBoss boss = new BigBoss;
            for (int i = 0; i < bossN; i++) {
                boss.firstname = scanner.next();
                boss.lastname = scanner.next();
                boss.age = scanner.nextInt();
                employees2.add(boss);
            }

            int i = 0;
            while (!scanner.hasNext() && i < emloyeeN) {
                Employee employee = new Employee;
                boss.firstname = scanner.next();
                boss.lastname = scanner.next();
                boss.age = scanner.nextInt();
                employees2.add(employee);
                i++;
            }
        }
    }

    ;
}
