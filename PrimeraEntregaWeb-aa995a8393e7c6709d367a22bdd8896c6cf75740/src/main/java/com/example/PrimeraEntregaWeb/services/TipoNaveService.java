package com.example.PrimeraEntregaWeb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PrimeraEntregaWeb.repository.TipoNaveRepository;

import io.micrometer.common.lang.NonNull;

import com.example.PrimeraEntregaWeb.model.TipoNave;


@Service
public class TipoNaveService {
    @Autowired
    private TipoNaveRepository tipoNaveRepositorio;

    public List<TipoNave> listarTipoNaves() {
        return tipoNaveRepositorio.findAll();
    }

    @SuppressWarnings("null")
    public TipoNave buscar(@NonNull Long id) {
        return tipoNaveRepositorio.findById(id).orElseThrow();
    }

    public Optional<TipoNave> buscarTipoNaveOptional(Long id) {
        return tipoNaveRepositorio.findById(id);
    }

    public void guardarTipoNave(TipoNave tipoNavecita) {
        tipoNaveRepositorio.save(tipoNavecita);
    }

    public void eliminarTipoNave(Long id) {
        tipoNaveRepositorio.deleteById(id);
    }


}
