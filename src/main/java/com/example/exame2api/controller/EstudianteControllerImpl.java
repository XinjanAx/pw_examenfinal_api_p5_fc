package com.example.exame2api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exame2api.service.IEstudianteService;
import com.example.exame2api.service.TO.EstudianteTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin
@RestController
@RequestMapping(path="/estudiantes")
public class EstudianteControllerImpl {

    @Autowired
    private IEstudianteService estudianteService;

    @GetMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstudianteTO> consultar(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.estudianteService.buscar(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertar(@RequestBody EstudianteTO estTO) {
       this.estudianteService.insertar(estTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EstudianteTO>> consultarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(this.estudianteService.consultarTodo());
    }
    
    
    
}

