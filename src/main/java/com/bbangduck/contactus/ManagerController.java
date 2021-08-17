package com.bbangduck.contactus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {

	private ManagerRepository repo;

	@Autowired
	public ManagerController(ManagerRepository repo) {
		this.repo = repo;
	}

	@GetMapping("/contact-us")
	public List<Manager> getManagerInfo() {
		return repo.findAll();
	}
}
