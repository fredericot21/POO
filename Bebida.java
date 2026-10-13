package com.botecotech;

public class Bebida {
    private int codigo;
    private String nome;
    private double preco;
    private double teorAlcoolico;
    private int volumeMl;

    public Bebida(int codigo, String nome, double preco, double teorAlcoolico, int volumeMl) {
        this.codigo = codigo;
        this.nome = nome;
        setPreco(preco);
        this.teorAlcoolico = teorAlcoolico;
        this.volumeMl = volumeMl;
    }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) {
        if (preco >= 0) {
            this.preco = preco;
        } else {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
    }

    public double getTeorAlcoolico() { return teorAlcoolico; }
    public void setTeorAlcoolico(double teorAlcoolico) { this.teorAlcoolico = teorAlcoolico; }

    public int getVolumeMl() { return volumeMl; }
    public void setVolumeMl(int volumeMl) { this.volumeMl = volumeMl; }

    public String exibirDetalhes() {
        return String.format("Cód: %d | %s - R$ %.2f (Teor: %.1f%%, %dml)", 
                codigo, nome, preco, teorAlcoolico, volumeMl);
    }
}
