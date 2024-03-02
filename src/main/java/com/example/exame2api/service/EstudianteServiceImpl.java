package com.example.exame2api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exame2api.repository.IEstudianteRepo;
import com.example.exame2api.repository.model.Estudiante;
import com.example.exame2api.service.TO.EstudianteTO;

@Service
public class EstudianteServiceImpl implements IEstudianteService{

    @Autowired
    private IEstudianteRepo estudianteRepo;

    @Override
    public void insertar(EstudianteTO estudiante) {
        // TODO Auto-generated method stub
        this.estudianteRepo.insertar(this.TOaE(estudiante));
    }

    @Override
    public EstudianteTO buscar(Integer id) {
        // TODO Auto-generated method stub
        return this.EaTO(this.estudianteRepo.buscar(id));
    }

    @Override
    public void actualizar(EstudianteTO estudiante) {
        // TODO Auto-generated method stub
        this.estudianteRepo.actualizar(this.TOaE(estudiante));
    }

    @Override
    public void eliminar(Integer id) {
        // TODO Auto-generated method stub
        this.estudianteRepo.eliminar(id);
    }

    @Override
    public List<EstudianteTO> consultarTodo() {
        // TODO Auto-generated method stub
        var le = this.estudianteRepo.consultarTodo();
        List<EstudianteTO> leTO = new ArrayList<>();
        for (Estudiante estudiante : le) {
            leTO.add(this.EaTO(estudiante));
        }
        return leTO;
    }

    //Convertidores

    private Estudiante TOaE (EstudianteTO to){
        Estudiante est = new Estudiante(to.getId(),to.getNombre(),to.getApellido(),to.getCedula());
        return est;
    }
    
    private EstudianteTO  EaTO (Estudiante est){
        EstudianteTO to = new EstudianteTO(est.getId(),est.getNombre(),est.getApellido(),est.getCedula());
        return to;
    }

}

