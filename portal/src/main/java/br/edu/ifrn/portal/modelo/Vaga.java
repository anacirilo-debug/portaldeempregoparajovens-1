package br.edu.ifrn.portal.modelo;

/**
 * Entidade que representa uma Vaga de Emprego no Portal.
 * Abrange os requisitos REQ.001 e REQ.002.
 */
public class Vaga {

    private Long id;
    private String titulo;
    private String empresa;
    private String descricao;
    private String area;
    private String tipoContrato; // CLT, Estagio, Freelance, Aprendiz
    private String localizacao;
    private boolean ativa;

    public Vaga() {
        this.ativa = true;
    }

    public Vaga(String titulo, String empresa, String descricao, String area, String tipoContrato, String localizacao) {
        this.titulo = titulo;
        this.empresa = empresa;
        this.descricao = descricao;
        this.area = area;
        this.tipoContrato = tipoContrato;
        this.localizacao = localizacao;
        this.ativa = true;
    }

    // Regra de negócio: verifica se a vaga está disponível (REQ.002)
    public boolean isDisponivel() {
        return this.ativa;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getTipoContrato() { return tipoContrato; }
    public void setTipoContrato(String tipoContrato) { this.tipoContrato = tipoContrato; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }

    @Override
    public String toString() {
        return "Vaga{id=" + id + ", titulo='" + titulo + "', empresa='" + empresa +
               "', area='" + area + "', tipo='" + tipoContrato +
               "', local='" + localizacao + "', ativa=" + ativa + "}";
    }
}
