package com.task.taskManagement.dao;

import com.task.taskManagement.entities.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant,Long> {
    public Page<Participant> findByUsernameContainsOrJobContains(String mc, String mc1, Pageable p);

}
