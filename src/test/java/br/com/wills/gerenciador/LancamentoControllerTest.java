package br.com.wills.gerenciador;

import br.com.wills.gerenciador.controller.LancamentosController;
import br.com.wills.gerenciador.model.Categoria;
import br.com.wills.gerenciador.model.EnumTipoLancamento;
import br.com.wills.gerenciador.model.Lancamento;
import br.com.wills.gerenciador.service.LancamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(LancamentosController.class)
public class LancamentoControllerTest {


    private MockMvc mockMvc;

    @MockBean
    private LancamentoService lancamentoService;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new LancamentosController()).build();
    }

    @Test
    public void salvarSucesso() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setId(2);
        Lancamento lancamento = new Lancamento(EnumTipoLancamento.D, "gastos", new BigDecimal(20.5), null, categoria);

        when(lancamentoService.salvaLancamento(any())).thenReturn(lancamento);
        this.mockMvc.perform(post("/lancamentos", lancamento))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("gastos")))
        ;
    }
}
