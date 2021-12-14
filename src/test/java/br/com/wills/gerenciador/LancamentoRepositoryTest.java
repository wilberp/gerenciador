package br.com.wills.gerenciador;

import br.com.wills.gerenciador.model.Categoria;
import br.com.wills.gerenciador.model.EnumTipoLancamento;
import br.com.wills.gerenciador.model.Lancamento;
import br.com.wills.gerenciador.repository.LancamentoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LancamentoRepositoryTest {

    @Autowired
    private LancamentoRepository repo;


    @Test
    @Rollback(false)
    public void salvar() {
        LocalDate data = LocalDate.now();
        Categoria categoria = new Categoria();
        categoria.setId(2);
        Lancamento lancamentoResponse = repo.save(new Lancamento(EnumTipoLancamento.D, "gastos", new BigDecimal(20.5), data, categoria));

        assertThat(lancamentoResponse.getId()).isGreaterThan(0);
    }

    @Test
    public void testBuscaCategoriaPorID() {
        List<Lancamento> lancamentos = repo.buscaCategoriaId(3);
        assertThat(lancamentos.size() > 0);
    }
}
