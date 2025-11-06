package com.thinkitive.hr.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hr")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "HR name is required")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    private String department;

    private String position;
}
