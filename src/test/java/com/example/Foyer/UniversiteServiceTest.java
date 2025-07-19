package com.example.Foyer;

import com.example.Foyer.DAO.Entities.Universite;
import com.example.Foyer.DAO.Repositories.UniversiteRepository;
import com.example.Foyer.Services.Universite.UniversiteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UniversiteServiceTest {

    @Mock
    private UniversiteRepository repo;

    @InjectMocks
    private UniversiteService universiteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOrUpdate() {
        Universite u = new Universite();
        when(repo.save(u)).thenReturn(u);

        Universite result = universiteService.addOrUpdate(u);

        assertNotNull(result);
        assertEquals(u, result);
        verify(repo).save(u);
    }

    @Test
    void testFindAll() {
        List<Universite> mockList = Arrays.asList(new Universite(), new Universite());
        when(repo.findAll()).thenReturn(mockList);

        List<Universite> result = universiteService.findAll();

        assertEquals(2, result.size());
        verify(repo).findAll();
    }

    @Test
    void testFindById_WhenFound() {
        Universite u = new Universite();
        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.of(u));

        Universite result = universiteService.findById(Long.valueOf(1L));

        assertNotNull(result);
        assertEquals(u, result);
        verify(repo).findById(Long.valueOf(1L));
    }

    @Test
    void testFindById_WhenNotFound() {
        when(repo.findById(Long.valueOf(2L))).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            universiteService.findById(Long.valueOf(2L));
        });

        assertEquals("universite id not found", exception.getMessage());
        verify(repo).findById(Long.valueOf(2L));
    }

    @Test
    void testDeleteById() {
        Long id = (Long) 1L;

        universiteService.deleteById(id);

        verify(repo).deleteById(id);
    }

    @Test
    void testDelete() {
        Universite u = new Universite();

        universiteService.delete(u);

        verify(repo).delete(u);
    }
}
