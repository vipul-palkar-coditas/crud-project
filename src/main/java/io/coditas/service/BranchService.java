package io.coditas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.coditas.model.Branch;
import io.coditas.repository.BranchRepository;

@Service
public class BranchService {
	@Autowired
	private BranchRepository branchRepository;

	public List<Branch> getAllBranches() {
		return branchRepository.findAll();
	}

	public Branch addBranch(Branch branch) {
		return branchRepository.save(branch);
	}

}
