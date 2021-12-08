package br.com.wills.gerenciador.repository;

import br.com.wills.gerenciador.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento,  Integer> {
}
