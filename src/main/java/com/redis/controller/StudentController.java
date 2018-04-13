package com.redis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redis.domain.Student;
import com.redis.repository.StudentRepository;

@RestController
public class StudentController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private StudentRepository studentRepository;

	@Cacheable(value = "users", key = "#id", unless = "#result.year < 2")
	@RequestMapping(value = "findOne", method = RequestMethod.GET)
	public Student findOne(@RequestParam("id") int id) {
		LOG.info("Getting user with ID {}.", id);
		return studentRepository.findOne(id);
	}

	@CachePut(value = "user", key = "#id")
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public Student update(@RequestParam("id") int id) {
		studentRepository.save(studentRepository.findOne(id));
		LOG.info("Updating user with ID {}.", id);
		return studentRepository.findOne(id);
	}

	@CacheEvict(value = "users", allEntries = true)
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public void delete(@RequestParam("id") int id) {
		LOG.info("deleting person with id {}", id);
		studentRepository.delete(id);
	}
}
