package com.example.exame2api.service;

import java.util.List;

import com.example.exame2api.service.TO.EstudianteTO;

public interface IEstudianteService {
    
    public void insertar(EstudianteTO estudiante);
    public EstudianteTO buscar(Integer id);
    public void actualizar(EstudianteTO estudiante);
    public void eliminar(Integer id);
    public List<EstudianteTO> consultarTodo();
}

