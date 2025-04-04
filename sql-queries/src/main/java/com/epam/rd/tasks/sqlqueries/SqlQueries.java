package com.epam.rd.tasks.sqlqueries;

/**
 * Implement sql queries like described
 */
public class SqlQueries {
    //Select all employees sorted by last name in ascending order
    //language=HSQLDB
    String select01 = "SELECT * FROM EMPLOYEE ORDER BY LASTNAME;";

    //Select employees having no more than 5 characters in last name sorted by last name in ascending order
    //language=HSQLDB
    String select02 = "SELECT * FROM EMPLOYEE WHERE LENGTH(LASTNAME) <= 5 ORDER BY LASTNAME;";

    //Select employees having salary no less than 2000 and no more than 3000
    //language=HSQLDB
    String select03 = "SELECT * FROM EMPLOYEE WHERE SALARY >= 2000 AND SALARY <= 3000;";

    //Select employees having salary no more than 2000 or no less than 3000
    //language=HSQLDB
    String select04 = "SELECT * FROM EMPLOYEE WHERE SALARY <= 2000 OR SALARY >= 3000;";

    //Select all employees assigned to departments and corresponding department
    //language=HSQLDB
    String select05 = "SELECT * FROM EMPLOYEE INNER JOIN DEPARTMENT ON EMPLOYEE.DEPARTMENT = DEPARTMENT.ID;";

    //Select all employees and corresponding department name if there is one.
    //Name column containing name of the department "depname".
    //language=HSQLDB
    String select06 = "SELECT E.ID, E.FIRSTNAME, E.LASTNAME, E.SALARY, D.NAME AS depname FROM EMPLOYEE E " +
            "LEFT JOIN DEPARTMENT D ON E.DEPARTMENT = D.ID;";

    //Select total salary pf all employees. Name it "total".
    //language=HSQLDB
    String select07 = "SELECT SUM(SALARY) AS total FROM EMPLOYEE;";

    //Select all departments and amount of employees assigned per department
    //Name column containing name of the department "depname".
    //Name column containing employee amount "staff_size".
    //language=HSQLDB
    String select08 = "SELECT ID, NAME as depname, LOCATION, COUNT(EMPLOYEE.ID) AS staff_size " +
            "FROM DEPARTMENT LEFT JOIN EMPLOYEE ON DEPARTMENT.ID = EMPLOYEE.DEPARTMENT " +
            "WHERE EMPLOYEE.DEPARTMENT != 0 GROUP BY DEPARTMENT.ID";

    //Select all departments and values of total and average salary per department
    //Name column containing name of the department "depname".
    //language=HSQLDB
    String select09 = "SELECT ID, NAME as depname, LOCATION, SUM(EMPLOYEE.SALARY) as total, AVG(EMPLOYEE.SALARY) as average " +
            "FROM DEPARTMENT LEFT JOIN EMPLOYEE ON DEPARTMENT.ID = EMPLOYEE.DEPARTMENT " +
            "WHERE EMPLOYEE.DEPARTMENT != 0 GROUP BY DEPARTMENT.ID";

    //Select lastnames of all employees and lastnames of their managers if an employee has a manager.
    //Name column containing employee's lastname "employee".
    //Name column containing manager's lastname "manager".
    //language=HSQLDB
    String select10 = "SELECT E.LASTNAME AS employee, M.LASTNAME AS manager FROM EMPLOYEE E " +
            "LEFT JOIN EMPLOYEE M ON E.MANAGER = M.ID;";


}
