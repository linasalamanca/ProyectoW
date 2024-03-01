package com.example.PrimeraEntregaWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.PrimeraEntregaWeb.model.Nave;

@Repository
public interface NaveRepository extends JpaRepository<Nave, String> {

}