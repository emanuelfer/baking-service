package service.utils;

import domain.Agencia;
import domain.Endereco;
import domain.http.AgenciaHttp;
import domain.http.SituacaoCadastral;

public class AgenciaFixture {

    public static AgenciaHttp criarAgenciaHttp(SituacaoCadastral situacaoCadastral){
        return new AgenciaHttp("Agencia teste", "Razao agencia teste", "cnpj123", situacaoCadastral);
    }

    public static Agencia criarAgencia(){
        Endereco endereco = new Endereco(1, "Rua 1" , "Unidade 203", "Cidade Operaria", 44);
        Agencia agencia = new Agencia(1, "Agencia CO", "Agencia CO BB", "cnpj123", endereco);
        return agencia;
    }
}
