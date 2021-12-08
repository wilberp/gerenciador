package br.com.wills.gerenciador.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "lancamento")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "tipo_lancamento", nullable = false)
    private String tipoLancamento;
    private String descricao;
    private BigDecimal valor = BigDecimal.ZERO;
    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "categoria_fk")
    private Categoria categoria;

    public Lancamento(String tipoLancamento, String descricao, BigDecimal valor, LocalDate dataCriacao, Categoria categoria) {
        super();
        this.tipoLancamento = tipoLancamento;
        this.descricao = descricao;
        this.valor = valor;
        this.dataCriacao = dataCriacao;
        this.categoria = categoria;
    }

    public Lancamento() {
    }

    public Integer getId() {return id;}

    public String getTipoLancamento() {
        return tipoLancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
