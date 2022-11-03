#include <string>
#include <list>
#include <exception>
#include <fstream>

class Employee
{
    Employee(std::string firstname, std::string lastname)
        :fistname(firstname)
        ,lastname(lastname)
    {}
    std::string firstname;
    std::string lastname;
    int age = 0;
};

class BigBoss
{
    BigBoss(std::string firstname, std::string lastname)
        :fistname(firstname)
        ,lastname(lastname)
    {}
    std::string firstname;
    std::string lastname;
    int age = 0;
};

class Office; // some external class. Content is irrelevant

class Organization
{
public:
    Organization(int bossN, int emloyeeN)
        :bossNumber(bossN)
        ,employeeNumber(employeeN)
        ,office(new Office)
    {
        if(bossN > employeeNumber)
            throw std::runtime_error("Schlechte Organisationsstruktur. Es sollte weniger Bosse geben");
    }

    Office& getOffice()
    {
        return *office;
    }

    int bossNumber;
    int employeeNumber;

    std::list<BigBoss*> employees_1;
    std::list<Employee*> employees_2;
    Office* office;

    static Organization* loadFromFile(const std::string& file, int bossN, int emloyeeN)
    {
        Organization* org = new Organization(bossN, emloyeeN);
        std::ifstream ifs;
        ifs.open(file);
        if(!ifs.is_open())
            throw std::invalid_argument("Failed to open file");

        BigBoss* boss = new BigBoss;
        for(int i = 0; i < bossN; i++)
        {
            ifs >> boss.firstname;
            ifs >> boss.lastname;
            ifs >> boss.age;
            org.employees_2.push_back(boss);
        }
        int i = 0;
        while(!ifs.eof() && i < emloyeeN)
        {
            Employee* employee = new Employee;
            ifs >> boss.firstname;
            ifs >> boss.lastname;
            ifs >> boss.age;
            org.employees_2.push_back(employee);
            i++;
        }
    }
};
