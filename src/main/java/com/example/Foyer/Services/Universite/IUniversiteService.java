package com.example.Foyer.Services.Universite;

import com.example.Foyer.DAO.Entities.Universite;

import java.util.List;

public interface IUniversiteService {
    Universite addOrUpdate(Universite u);
    List<Universite> findAll();
    Universite findById(Long id); // changed from long to Long
    void deleteById(Long id);     // changed from long to Long
    void delete(Universite u);
}
