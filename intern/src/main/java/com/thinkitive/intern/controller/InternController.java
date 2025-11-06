package com.thinkitive.intern.controller;

import com.thinkitive.intern.model.Intern;
import com.thinkitive.intern.service.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interns")
public class InternController {

    @Autowired
    private InternService internService;

    @GetMapping
    public List<Intern> getAllInterns() {
        return internService.getAllInterns();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Intern> getInternById(@PathVariable Long id) {
        return internService.getInternById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Intern> createIntern(@RequestBody Intern intern) {
        return ResponseEntity.ok(internService.addIntern(intern));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Intern> updateIntern(@PathVariable Long id, @RequestBody Intern intern) {
        return ResponseEntity.ok(internService.updateIntern(id, intern));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIntern(@PathVariable Long id) {
        internService.deleteIntern(id);
        return ResponseEntity.noContent().build();
    }
}
