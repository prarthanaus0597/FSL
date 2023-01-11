package com.example.backend.DAO.DAOImplement;

import com.example.backend.Bean.Department;
import com.example.backend.DAO.DepartmentDAO;
import com.example.backend.Util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {
    @Override
    public boolean addDepartment(Department deptObj) {
        try(Session session = HibernateSessionUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(deptObj);
            transaction.commit();
            return true;
        }
        catch (HibernateException exception) {
            System.out.println("Hibernate Exception");
            System.out.print(exception.getLocalizedMessage());
            return false;
        }
    }

    @Override @Deprecated
    public List<Department> getDepartmentList() {
        try (Session session = HibernateSessionUtil.getSession()){
            List<Department> departmentList = new ArrayList<>();
            for (final Object d : session.createQuery("from Department ").list()) {
                departmentList.add((Department) d);
            }
            return departmentList;

        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
    }
}
