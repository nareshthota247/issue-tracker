package com.pinguin.issuetracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pinguin.issuetracker.model.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {

	Optional<Developer> findByDeveloperName(String developerName);
	
}
