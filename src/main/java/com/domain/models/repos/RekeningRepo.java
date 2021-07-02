package com.domain.models.repos;

import com.domain.models.entities.Rekening;

import org.springframework.data.repository.CrudRepository;

public interface RekeningRepo extends CrudRepository<Rekening, Long> {

    public Rekening findByNorek(String norek);

}
