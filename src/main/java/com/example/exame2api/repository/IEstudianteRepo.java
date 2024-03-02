package com.example.exame2api.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.exame2api.repository.model.Estudiante;


@Repository
public interface IEstudianteRepo {
    
    public void insertar(Estudiante estudiante);
    public Estudiante buscar(Integer id);
    public void actualizar(Estudiante estudiante);
    public void eliminar(Integer id);
    public List<Estudiante> consultarTodo();

}
