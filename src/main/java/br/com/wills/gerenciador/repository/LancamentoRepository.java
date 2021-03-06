package br.com.wills.gerenciador.repository;

import br.com.wills.gerenciador.dto.BalancoFinalDTO;
import br.com.wills.gerenciador.model.Categoria;
import br.com.wills.gerenciador.model.Lancamento;
import br.com.wills.gerenciador.model.RelatorioBalanco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento,  Integer> {

    List<Lancamento> findBydataCriacaoBetweenAndCategoria(LocalDate dataAnterior, LocalDate dataAtual, Categoria categoria);

    @Query("SELECT l FROM Lancamento l WHERE tipo_lancamento = 'D' and MONTH(data_criacao)=:mes")
    List<Lancamento> buscaMes(@Param("mes") Integer mes);


    @Query("SELECT l FROM Lancamento l WHERE tipo_lancamento = 'D' and (data_criacao)>=:dataAnterior and (data_criacao)<=:dataAtual")
    List<Lancamento> buscaSemana(@Param("dataAnterior") LocalDate dataAnterior, @Param("dataAtual") LocalDate dataAtual);


    @Query("SELECT l FROM Lancamento l WHERE tipo_lancamento = 'D' and data_criacao=:data")
    List<Lancamento> buscaDia(@Param("data") LocalDate data);

    @Query(value ="SELECT * FROM lancamento l WHERE tipo_lancamento = 'D' and categoria_fk=:categoriaId", nativeQuery = true)
    List<Lancamento> buscaCategoriaId(@Param("categoriaId") Integer categoriaId);

//    @Query(value ="SELECT COUNT(*) AS quantidadeSaida,(sum(valor)) AS somaSaidas FROM lancamento  WHERE tipo_lancamento='D' and MONTH (data_criacao)=:mes", nativeQuery = true)
//    Object buscaBalancoSaida(@Param("mes") Integer mes);


    @Query(value ="SELECT * FROM lancamento  WHERE tipo_lancamento=:tipo and MONTH (data_criacao)=:mes", nativeQuery = true)
    List<Lancamento> buscaBalanco(@Param("tipo") String tipo, @Param("mes") Integer mes);

}
