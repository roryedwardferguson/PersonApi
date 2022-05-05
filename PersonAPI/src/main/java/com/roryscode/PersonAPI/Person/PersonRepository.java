package com.roryscode.PersonAPI.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {


    Optional<Person> findPersonById(Long personId);

    @Query("Select p from Person p WHERE p.email = ?1")
    Optional<Person> findPersonByEmail(String email);
}
