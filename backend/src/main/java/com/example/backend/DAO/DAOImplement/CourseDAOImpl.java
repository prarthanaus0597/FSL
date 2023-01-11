package com.example.backend.DAO.DAOImplement;

import com.example.backend.Bean.Course;
import com.example.backend.Bean.CourseSchedule;
import com.example.backend.DAO.CourseDAO;
import com.example.backend.DAO.CourseScheduleDAO;
import com.example.backend.Util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    public boolean addCourse(Course course) {
        try(Session session = HibernateSessionUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(course);
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
    public List<Course> getCourseList() {
        try (Session session = HibernateSessionUtil.getSession()){
            List<Course> courseList = new ArrayList<>();
            for (final Object d : session.createQuery("from  Course where empCourse IS NULL ").list()) {
                courseList.add((Course) d);
            }
            return courseList;

        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
    }
  @Deprecated
    public List<Course> getCourseAdded(Integer eid) {
        try (Session session = HibernateSessionUtil.getSession()){
            List<Course> courseList = new ArrayList<>();

            Query query = session.createQuery("from  Course where empCourse.emp_id=:eid");
            query.setParameter("eid", eid);
            for (final Object d : query.list()) {
                courseList.add((Course) d);
            }
            return courseList;

        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
    }
    @Override
    public boolean addCourseToId(Integer cid,Integer eid) {
        try (Session session = HibernateSessionUtil.getSession()) {
            Transaction t = session.beginTransaction();

            Query query = session.createQuery("update Course c set c.empCourse.emp_id=:eid where c.course_id =: cid");
            query.setParameter("eid", eid);
            query.setParameter("cid", cid);

            int count = query.executeUpdate();

            t.commit();
            return true;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return false;
        }
    }


}
