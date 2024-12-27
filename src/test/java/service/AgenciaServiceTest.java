package service;

import com.sun.source.tree.AssertTree;
import domain.Agencia;
import domain.Endereco;
import domain.http.AgenciaHttp;
import domain.http.SituacaoCadastral;
import exceptions.AgenciaNaoAtivaOuNaoEncontradaException;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.AgenciaRepository;
import service.http.SituacaoCadastralHttpService;
import service.utils.AgenciaFixture;

@QuarkusTest
public class AgenciaServiceTest {

    @InjectMock
    private AgenciaRepository agenciaRepository;


    @InjectMock
    @RestClient
    private SituacaoCadastralHttpService situacaoCadastralHttpService;

    @Inject
    private AgenciaService agenciaService;

    @Test
    public void deveCadastarQuandoClienteRetornarSituacaoCadastralAtiva(){
        Agencia agencia = AgenciaFixture.criarAgencia();

        Mockito.when(situacaoCadastralHttpService.buscarPorCnpj("cnpj123")).thenReturn(AgenciaFixture.criarAgenciaHttp(SituacaoCadastral.ATIVO));

        agenciaService.cadastrar(agencia);

        Mockito.verify(agenciaRepository).persist(agencia);
    }

    @Test
    public void deveNaoCadastrarQuandoClienteRetornaNull(){
        Mockito.when(situacaoCadastralHttpService.buscarPorCnpj("cnpj123")).thenReturn(null);

        Agencia agencia = AgenciaFixture.criarAgencia();
        Assertions.assertThrows(AgenciaNaoAtivaOuNaoEncontradaException.class, () -> agenciaService.cadastrar(agencia));

        Mockito.verify(agenciaRepository, Mockito.never()).persist(agencia);
    }

    @Test
    public void deveNaoCadastrarQuandoClientRetornarAgenciaInativa(){
        AgenciaHttp agenciaHttpInativa = AgenciaFixture.criarAgenciaHttp(SituacaoCadastral.INATIVO);
        Agencia agencia = AgenciaFixture.criarAgencia();
        Mockito.when(situacaoCadastralHttpService.buscarPorCnpj("123")).thenReturn(agenciaHttpInativa);

        Assertions.assertThrows(AgenciaNaoAtivaOuNaoEncontradaException.class, () -> agenciaService.cadastrar(agencia));

        Mockito.verify(agenciaRepository, Mockito.never()).persist(agencia);
    }




}
