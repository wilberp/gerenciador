package br.com.wills.gerenciador.repository;

import br.com.wills.gerenciador.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,  Integer> {
}
