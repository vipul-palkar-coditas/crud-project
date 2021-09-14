package io.coditas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.coditas.model.Branch;
import io.coditas.service.BranchService;

@RestController
@RequestMapping("/branch")
public class BranchController {
	@Autowired
	private BranchService branchService;
	
	@GetMapping("/branches")
	public List<Branch> getAllBranch()
	{
		return branchService.getAllBranches();
	}
	
	@PostMapping("save")
	public Branch addBranch(@RequestBody Branch branch)
	{
		return branchService.addBranch(branch);
	}
}
