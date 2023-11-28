package com.example.streaminggp;

public class Filme {
    private String titulo;
    private String descricao;
    private String caminhoDoPoster; // Alteração para String

    public Filme(String titulo, String descricao, String caminhoDoPoster) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.caminhoDoPoster = caminhoDoPoster;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCaminhoDoPoster() {
        return caminhoDoPoster;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCaminhoDoPoster(String caminhoDoPoster) {
        this.caminhoDoPoster = caminhoDoPoster;
    }
}
