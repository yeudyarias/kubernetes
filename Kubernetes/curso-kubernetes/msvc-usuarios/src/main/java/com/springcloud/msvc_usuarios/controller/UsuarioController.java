package com.springcloud.msvc_usuarios.controller;

import com.springcloud.msvc_usuarios.models.entity.Usuario;
import com.springcloud.msvc_usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping

    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> porId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.porId(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
        public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
        if (!usuario.getEmail().isEmpty() && usuarioService.porEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().
                    body(Collections.
                            singletonMap("Error", "Ya existe un usuario con ese email"));
        }
        if (bindingResult.hasErrors()) {
            return validar(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario, BindingResult bindingResult, @PathVariable Long id) {
        System.out.println("Prueba");
        if (bindingResult.hasErrors()) {
            return validar(bindingResult);
        }
        Optional<Usuario> o = usuarioService.porId(id);
        if (o.isPresent()) {
            Usuario usuarioDb = o.get();
            if (!usuario.getEmail().isEmpty() &&
                    !usuario.getEmail().equalsIgnoreCase(usuarioDb.getEmail()) &&
                    usuarioService.porEmail(usuario.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().
                        body(Collections.
                                singletonMap("Error", "Ya existe un usuario con ese email"));
            }
            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setEmail(usuario.getEmail());
            usuarioDb.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuarioDb));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Usuario> o = usuarioService.porId(id);
        if (o.isPresent()) {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().
                body(Collections.
                        singletonMap("Error", "Usuario a eliminar NO existe"));

    }

    @GetMapping("/usuarios-por-curso")
    public ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam  List<Long> ids){
        return ResponseEntity.ok(usuarioService.listarPorIds(ids));
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult bindingResult) {
        Map<String, String> errores = new HashMap<>();
        bindingResult.getFieldErrors().forEach(err -> {
            errores.put("Error", "El campo "+err.getField() + " " +err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }


}
