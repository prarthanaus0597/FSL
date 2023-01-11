package com.example.backend.DAO.DAOImplement;

import com.example.backend.Bean.Employee;
import com.example.backend.DAO.EmployeeDAO;
import com.example.backend.Util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    public boolean addEmployee(Employee employee) {
        try(Session session = HibernateSessionUtil.getSession()){
            Transaction transaction = session.beginTransaction();

            session.persist(employee);
            transaction.commit();
            return true;
        }
        catch (Exception exception) {
            System.out.println("Hibernate Exception");
            System.out.print(exception.getLocalizedMessage());
            return false;
        }
    }

    @Override @Deprecated
    public List<Employee> getEmployeeList() {
        try (Session session = HibernateSessionUtil.getSession()){
            List<Employee> employeeList = new ArrayList<>();
            for (final Object d : session.createQuery("from Employee ").list()) {
                employeeList.add((Employee) d);
            }
            return employeeList;

        } catch (Exception exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
    }


    @Override
    public Employee login(Employee emp) {

        try(Session session = HibernateSessionUtil.getSession()) {

            Query query = session.createQuery("from Employee where email=:email and password=:password");
            query.setParameter("email", emp.getEmail());
            query.setParameter("password", emp.getPassword());
            Employee e = null;
            if (query.getResultList().size() == 1) {
                e = (Employee) query.list().get(0);

            }
            return e;
        } catch (Exception exception) {
            System.out.print(exception.getLocalizedMessage());

        }
        return new Employee();
    }


    @Override
    public Employee getEmployeeDetailsById(Integer id) {
        try (Session session = HibernateSessionUtil.getSession()) {
            Query query = session.createQuery("from Employee where emp_id="+id);
            Employee e = null;
            if (query.getResultList().size() == 1) {
                e = (Employee) query.list().get(0);

            }
            return e;
//            return session.get(Employee.class, id);
        } catch (Exception exception) {
            System.out.print(exception.getLocalizedMessage());
        }
        return null;
    }
}
