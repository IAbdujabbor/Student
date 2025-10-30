package com.university.repository;


import com.university.entity.Attendance;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import com.university.entity.Student;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AttendanceRepository implements PanacheRepository<Attendance> {

    public List<Attendance> getAll() {
        return listAll();
    }

    @Transactional
    public Attendance add(Attendance attendance) {
        persist(attendance);
        return attendance;
    }

    @Transactional
    public Optional<Attendance> updateAttendance(Long id, Attendance attendanceParameter) {
        return findByIdOptional(id).map(attendanceExist -> {
            attendanceExist.setStudent(attendanceParameter.getStudent());
            attendanceExist.setDiscipline(attendanceParameter.getDiscipline());
            persist(attendanceParameter);
            return attendanceExist;
        });
    }

    @Transactional
    public Optional<Attendance> deleteAttendanceById(Long id) {
        return findByIdOptional(id).map(attendanceExist -> {
            delete(attendanceExist);
            return attendanceExist;
        });
    }


}
