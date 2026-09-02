package br.edu.ifrn.portal.modelo;

/**
 * Entidade que representa um Jovem Candidato cadastrado no Portal.
 * Abrange os requisitos REQ.003 e REQ.004.
 */
public class Candidato {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private int idade;
    private String areaInteresse;
    private String nivelEscolaridade; // Fundamental, Medio, Superior
    private boolean buscandoEmprego;

    public Candidato() {
        this.buscandoEmprego = true;
    }

    public Candidato(String nome, String email, String telefone, int idade, String areaInteresse, String nivelEscolaridade) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.idade = idade;
        this.areaInteresse = areaInteresse;
        this.nivelEscolaridade = nivelEscolaridade;
        this.buscandoEmprego = true;
    }

    // Regra de negócio: jovens entre 14 e 29 anos podem usar o portal (REQ.003)
    public boolean isElegivelParaPortal() {
        return this.idade >= 14 && this.idade <= 29;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getAreaInteresse() { return areaInteresse; }
    public void setAreaInteresse(String areaInteresse) { this.areaInteresse = areaInteresse; }

    public String getNivelEscolaridade() { return nivelEscolaridade; }
    public void setNivelEscolaridade(String nivelEscolaridade) { this.nivelEscolaridade = nivelEscolaridade; }

    public boolean isBuscandoEmprego() { return buscandoEmprego; }
    public void setBuscandoEmprego(boolean buscandoEmprego) { this.buscandoEmprego = buscandoEmprego; }

    @Override
    public String toString() {
        return "Candidato{id=" + id + ", nome='" + nome + "', email='" + email +
               "', idade=" + idade + ", area='" + areaInteresse +
               "', escolaridade='" + nivelEscolaridade + "', buscandoEmprego=" + buscandoEmprego + "}";
    }
}
