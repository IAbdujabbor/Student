package com.university.repository;


import com.university.entity.Discipline;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;


@ApplicationScoped
public class DisiplineRepository implements PanacheRepository<Discipline> {

    public List<Discipline> getAll (){
        return listAll();
    }



    public void add(Discipline disipline) {
        persist(disipline);


    }



}
