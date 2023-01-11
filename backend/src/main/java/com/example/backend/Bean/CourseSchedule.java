package com.example.backend.Bean;

import jakarta.persistence.*;



@Entity
@Table(name ="course_schedule")  //  you can specify MySQL table name it is optional; by-default it takes class name as table name
public class CourseSchedule {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    @Column(name="time",nullable = false)
    private String time;

    @Column(name="day", nullable = false)
    private String day;

    @Column(name="building", nullable = false)
    private String building;

    @Column(name="room", nullable = false)
    private int room;


    @ManyToOne
    @JoinColumn(name="course_id")
    private Course courseschedule;



    public CourseSchedule(int id,String time,String day, String building ,int room,Course course) {
        this.id=id;
        this.time=time;
        this.day=day;
        this.building=building;
        this.room = room;
        this.courseschedule=course;
    }

    public CourseSchedule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //-----------------------------------------------------------------
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int  room) {
        this.room = room;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }



    public Course getCourse() {
        return courseschedule;
    }

    public void setCourse(Course course) {
        this.courseschedule = course;
    }

    public Course getCourseschedule() {
        return courseschedule;
    }

    public void setCourseschedule(Course course) {
        this.courseschedule = courseschedule;
    }



    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +


                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", building='" + building + '\'' +
                ", room='" + room + '\'' +
                ", courseschedule='" + courseschedule + '\'' +
                '}';
    }
}