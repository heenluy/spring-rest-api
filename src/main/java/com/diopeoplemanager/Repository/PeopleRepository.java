package com.diopeoplemanager.Repository;

import java.util.List;

import com.diopeoplemanager.model.People;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long>{
    List<People> findByName(String name);
}
