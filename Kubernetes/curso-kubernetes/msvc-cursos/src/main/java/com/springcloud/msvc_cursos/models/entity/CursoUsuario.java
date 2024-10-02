package com.springcloud.msvc_cursos.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "cursos_usuario")
@Getter
@Setter
public class CursoUsuario {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(name = "usuario_id", unique = true)
    private Long usuarioId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CursoUsuario that)) return false;
        return Objects.equals(getUsuarioId(), that.getUsuarioId());
    }
}
