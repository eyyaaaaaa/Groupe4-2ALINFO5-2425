package com.example.Foyer.Services.Universite;

import com.example.Foyer.DAO.Entities.Universite;
import com.example.Foyer.DAO.Repositories.UniversiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversiteService implements IUniversiteService {
    private final UniversiteRepository repo;

    public UniversiteService(UniversiteRepository repo) {
        this.repo = repo;
    }

    @Override
    public Universite addOrUpdate(Universite u) {
        return repo.save(u);
    }

    @Override
    public List<Universite> findAll() {
        return repo.findAll();
    }

    @Override
    public Universite findById(Long id) { // changed from long to Long
        return repo.findById(id).orElseThrow(() -> new RuntimeException("universite id not found"));
    }

    @Override
    public void deleteById(Long id) { // changed from long to Long
        repo.deleteById(id);
    }

    @Override
    public void delete(Universite u) {
        repo.delete(u);
    }
}
