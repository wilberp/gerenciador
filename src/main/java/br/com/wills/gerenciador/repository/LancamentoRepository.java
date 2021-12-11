package br.com.wills.gerenciador.repository;

import br.com.wills.gerenciador.dto.BalancoDTO;
import br.com.wills.gerenciador.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento,  Integer>, JpaSpecificationExecutor<Lancamento> {

    @Query("SELECT l FROM Lancamento l WHERE tipo_lancamento = 'D' and MONTH(data_criacao)=:mes")
    List<Lancamento> buscaMes(@Param("mes") Integer mes);


    @Query("SELECT l FROM Lancamento l WHERE tipo_lancamento = 'D' and (data_criacao)>=:dataAnterior and (data_criacao)<=:dataAtual")
    List<Lancamento> buscaSemana(@Param("dataAnterior") LocalDate dataAnterior, @Param("dataAtual") LocalDate dataAtual);


    @Query("SELECT l FROM Lancamento l WHERE tipo_lancamento = 'D' and data_criacao=:data")
    List<Lancamento> buscaDia(@Param("data") LocalDate data);

    @Query(value ="SELECT * FROM Lancamento l WHERE tipo_lancamento = 'D' and categoria_fk=:categoriaId", nativeQuery = true)
    List<Lancamento> buscaCategoriaId(@Param("categoriaId") Integer categoriaId);

    @Query(value ="SELECT COUNT(*) AS quantidadeSaida,(sum(valor)) AS somaSaidas FROM Lancamento  WHERE tipo_lancamento='D' and MONTH (data_criacao)=:mes", nativeQuery = true)
    Object buscaBalancoSaida(@Param("mes") Integer mes);

}
