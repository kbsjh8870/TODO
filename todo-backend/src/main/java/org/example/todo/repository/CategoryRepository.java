package org.example.todo.repository;

import org.example.todo.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {
    Optional<Category> findByCategory(String category);

    void deleteByCategory(String category);
}
