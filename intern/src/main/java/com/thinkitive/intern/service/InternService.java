package com.thinkitive.intern.service;

import com.thinkitive.intern.model.Intern;
import com.thinkitive.intern.repository.InternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternService {

    @Autowired
    private InternRepository internRepository;

    public List<Intern> getAllInterns() {
        return internRepository.findAll();
    }

    public Optional<Intern> getInternById(Long id) {
        return internRepository.findById(id);
    }

    public Intern addIntern(Intern intern) {
        return internRepository.save(intern);
    }


    public void deleteIntern(Long id) {
        internRepository.deleteById(id);
    }

    public Intern updateIntern(Long id, Intern intern) {
        return null;
    }
}
