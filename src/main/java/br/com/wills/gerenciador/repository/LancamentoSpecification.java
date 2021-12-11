package br.com.wills.gerenciador.repository;

import br.com.wills.gerenciador.model.Lancamento;
import br.com.wills.gerenciador.model.Lancamento_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class LancamentoSpecification {
    public static Specification<Lancamento> categoriaLazer() {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Lancamento_.CATEGORIA), 3);
    }

    public static Specification<Lancamento> dataMes(LocalDate data) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Lancamento_.DATA_CRIACAO), data);
    }
}
