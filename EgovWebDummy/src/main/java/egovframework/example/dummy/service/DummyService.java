package egovframework.example.dummy.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import egovframework.example.dummy.dao.DummyDAO;

@Service
public class DummyService {
	@Inject
	private DummyDAO dao;
}
