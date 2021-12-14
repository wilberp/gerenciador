package br.com.wills.gerenciador.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @Column(name = "limite_mensal", nullable = true)
    private BigDecimal limiteMensal = BigDecimal.ZERO;


   public Categoria(String nome, BigDecimal limiteMensal) {
        this.nome = nome;
        this.limiteMensal = limiteMensal;
    }

    public Categoria() {
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getLimiteMensal() { return limiteMensal; }

    public void setId(Integer id) {
        this.id = id;
    }
}