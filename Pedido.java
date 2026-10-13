package com.botecotech;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int numeroMesa;
    private List<ItemPedido> itens;
    private String status;

    public Pedido(int numeroMesa) {
        this.numeroMesa = numeroMesa;
        this.itens = new ArrayList<>();
        this.status = "Aberto";
    }

    public void adicionarItem(Bebida bebida, int quantidade) {
        this.itens.add(new ItemPedido(bebida, quantidade));
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public int getNumeroMesa() { return numeroMesa; }
    public List<ItemPedido> getItens() { return itens; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
