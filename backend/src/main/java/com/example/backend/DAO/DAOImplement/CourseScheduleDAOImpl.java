package com.example.backend.DAO.DAOImplement;

import com.example.backend.Bean.Course;
import com.example.backend.Bean.CourseSchedule;
import com.example.backend.DAO.CourseScheduleDAO;
import com.example.backend.Util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CourseScheduleDAOImpl implements CourseScheduleDAO {
    @Override
    public boolean addCourseSchedule(CourseSchedule courseSchedule) {
        try(Session session = HibernateSessionUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(courseSchedule);
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
    public List<CourseSchedule> getCourseScheduleList() {
        try (Session session = HibernateSessionUtil.getSession()){
            List<CourseSchedule> cousescheduleList = new ArrayList<>();
            for (final Object d : session.createQuery("select cs from CourseSchedule cs where courseschedule.empCourse.emp_id IS NULL ").list()) {
                cousescheduleList.add((CourseSchedule) d);
            }
            return cousescheduleList;

        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
    }

    @Deprecated
    public List<CourseSchedule> getCourseSchedules(Integer cid, Integer eid) {
        Session session = HibernateSessionUtil.getSession();
        List<CourseSchedule> courses = new ArrayList<>();
        try {
//            String q=" from CourseSchedule cs join Course c on c.course_id=cs.courseschedule.course_id where c.course_id!=:cid";//JOIN cs.courseschedule c on c.course_id=cs.courseschedule.course_id where c.course_id!=:cid and  c.empCourse.emp_id=:eid and  exists (select c2.course_code from  CourseSchedule c2  where c2.courseschedule.course_id=:cid and c2.time=cs.time and c2.day=cs.day)";
            String q="select cs from Course c JOIN CourseSchedule cs on c.course_id=cs.courseschedule.course_id  where c.course_id!=:cid  and  cs.courseschedule.empCourse.emp_id=:eid and exists (select 1 from CourseSchedule c2  where c2.courseschedule.course_id=:cid and c2.time=cs.time and c2.day=cs.day )";
            Query query = session.createQuery(q);
//            (select * from course c JOIN course_schedule cs on c.course_id=cs.course_id  where course_id!=*cid* and  c.faculty_id=*eid* and  exists
//                    (select (time,day) from  course_schedule c2  where course_id=*cid* and c2.time=cs.time and c2.day=cs.day );) ");
            query.setParameter("cid", cid);
            query.setParameter("eid", eid);


            for (final Object course : query.list()) {
                courses.add((CourseSchedule) course);
            }
            return courses;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }

    }
}
