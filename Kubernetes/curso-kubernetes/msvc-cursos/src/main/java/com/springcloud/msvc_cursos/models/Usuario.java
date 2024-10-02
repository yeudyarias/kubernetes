package com.springcloud.msvc_cursos.models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Usuario {
    private long id;
    private String nombre;
    private String email;
    private String password;
    private Long age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return getId() == usuario.getId() && Objects.equals(getNombre(), usuario.getNombre()) && Objects.equals(getEmail(), usuario.getEmail()) && Objects.equals(getPassword(), usuario.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getEmail(), getPassword());
    }
}

