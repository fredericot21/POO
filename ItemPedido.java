package com.botecotech;

public class ItemPedido {
    private Bebida bebida;
    private int quantidade;

    public ItemPedido(Bebida bebida, int quantidade) {
        this.bebida = bebida;
        this.quantidade = quantidade;
    }

    public double calcularSubtotal() {
        return bebida.getPreco() * quantidade;
    }

    public Bebida getBebida() { return bebida; }
    public int getQuantidade() { return quantidade; }
}
