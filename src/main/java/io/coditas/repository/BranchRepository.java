package io.coditas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.coditas.model.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

}
