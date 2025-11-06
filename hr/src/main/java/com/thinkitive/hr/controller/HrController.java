package com.thinkitive.hr.controller;

import com.thinkitive.hr.model.Hr;
import com.thinkitive.hr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hr")
public class HrController {

        @Autowired
        private HrService hrService;

        @GetMapping
        public List<Hr> getAllHr() {
            return hrService.getAllHr();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Hr> getHrById(@PathVariable Long id) {
            return hrService.getHrById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping
        public ResponseEntity<Hr> createHr(@RequestBody Hr hr) {
            return ResponseEntity.ok(hrService.addHr(hr));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Hr> updateHr(@PathVariable Long id, @RequestBody Hr hr) {
            return ResponseEntity.ok(hrService.updateHr(id, hr));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteHr(@PathVariable Long id) {
            hrService.deleteHr(id);
            return ResponseEntity.noContent().build();
        }
}
