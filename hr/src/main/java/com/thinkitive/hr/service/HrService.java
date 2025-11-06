package com.thinkitive.hr.service;

import com.thinkitive.hr.model.Hr;
import com.thinkitive.hr.repository.HrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HrService {

    @Autowired
    private HrRepository hrRepository;

    public List<Hr> getAllHr() {
        return hrRepository.findAll();
    }

    public Optional<Hr> getHrById(Long id) {
        return hrRepository.findById(id);
    }

    public Hr addHr(Hr hr) {
        return hrRepository.save(hr);
    }

    public Hr updateHr(Long id, Hr updatedHr) {
        return hrRepository.findById(id)
                .map(existingHr -> {
                    existingHr.setName(updatedHr.getName());
                    existingHr.setEmail(updatedHr.getEmail());
                    existingHr.setDepartment(updatedHr.getDepartment());
                    existingHr.setPosition(updatedHr.getPosition());
                    return hrRepository.save(existingHr);
                })
                .orElseThrow(() -> new RuntimeException("HR not found with id: " + id));
    }

    public void deleteHr(Long id) {
        hrRepository.deleteById(id);
    }
}
