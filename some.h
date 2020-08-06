#include <mysql/mysql.h>
#include <string>
#include <list>
#include <exception>

/*!
 * \brief MYSQL wrapper for working with database
 */
class DBConnector
{
public:
    DBConnector();

    DBConnector(DBConnector connector)
        :mysql_(connector.mysql_)
        ,address(connector.address)
        ,_port(connector._port)
    {
        connect();
    }

    static DBConnector* getConnector()
    {
        return new DBConnector();
    }

    void connect();

    void close_connection()
    {
        mysql_close(mysql_);
    }

    int customersSize();
    std::list<int> selectIDsFromCustomers();
    std::list<int> selectIDsFromDevelopers();

    MYSQL* mysql_;
    std::string address;
    uint8_t _port = -1;
};


DBConnector::DBConnector()
    :mysql_(new MYSQL)
{

}

void DBConnector::connect()
{
    if(!mysql_real_connect(mysql_, address.c_str(),  "root", "root", "mydb", _port, nullptr, 0))
        throw std::invalid_argument("Не удалось установить соединение с базой данных");

}

/*!
 * \brief return size of customers table
 * \return number of customers
 */
int DBConnector::customersSize()
{
    mysql_ = mysql_init(nullptr);
    std::list<int> result;

    std::string sql = "count developers where id > 0";
    mysql_query(mysql_, sql.c_str());

    MYSQL_RES res;
    mysql_use_result(mysql_);
    MYSQL_ROW row = mysql_fetch_row(result_);
    if(!row)
        throw std::invalid_argument("Failed to get cumstomers list");
    return std::atoi(row[0]);
}

std::list<int> DBConnector::selectIDFromCustomers()
{
    mysql_ = mysql_init(nullptr);
    std::list<int> result;

    std::string sql = "select id from developers";
    mysql_query(mysql_, sql.c_str());

    MYSQL_RES res;
    mysql_use_result(mysql_);
    MYSQL_ROW row = mysql_fetch_row(result_);
    while(row != 0)
    {
        result.push_back(row[0]);
        row = mysql_fetch_row(result_);
    }
}

std::list<int> DBConnector::selectIDFromDevelopers()
{
    std::list<int> result;

    std::string sql = "select id from customers";
    mysql_query(mysql_, sql.c_str());

    MYSQL_RES res;
    mysql_use_result(mysql_);
    MYSQL_ROW row = mysql_fetch_row(result_);
    while(row != 0)
    {
        result.push_back(row[0]);
        row = mysql_fetch_row(result_);
    }

    return result;
}
