package domain.http;

import domain.Endereco;

public class AgenciaHttp {

    public AgenciaHttp(){

    }

    public AgenciaHttp(String nome, String razaoSocial, String cnpj, SituacaoCadastral situacaoCadastral){
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.situacaoCadastral = situacaoCadastral;
    }

    private String nome;
    private String razaoSocial;
    private String cnpj;
    private SituacaoCadastral situacaoCadastral;

    private Endereco endereco;

    public String getNome() {
        return nome;
    }


    public String getCnpj() {
        return cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public SituacaoCadastral getSituacaoCadastral() {
        return situacaoCadastral;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}


