package com.botecotech;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BarApp {
    private static List<Bebida> bebidas = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarMock();
        int opcao = 0;

        do {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        cadastrarBebida();
                        break;
                    case 2:
                        listarBebidas();
                        break;
                    case 3:
                        criarPedido();
                        break;
                    case 4:
                        listarPedidos();
                        break;
                    case 5:
                        System.out.println("Encerrando o sistema do Bar... Até logo!");
                        break;
                    default:
                        System.out.println("Opção inválida! Escolha entre 1 e 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido!");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
            System.out.println();
        } while (opcao != 5);
    }

    private static void inicializarMock() {
        bebidas.add(new Bebida(1, "Chopp Pilsen", 12.00, 4.5, 300));
        bebidas.add(new Bebida(2, "Caipirinha de Limão", 22.50, 13.0, 350));
        bebidas.add(new Bebida(3, "Refrigerante Lata", 7.00, 0.0, 350));
    }

    private static void exibirMenu() {
        System.out.println("=== SISTEMA BOTECO TECH ===");
        System.out.println("1. Cadastrar nova bebida");
        System.out.println("2. Listar bebidas cadastradas");
        System.out.println("3. Criar novo pedido (Mesa)");
        System.out.println("4. Listar pedidos");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarBebida() {
        System.out.println("\n--- Cadastro de Bebida ---");
        System.out.print("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Preço (R$): ");
        double preco = Double.parseDouble(scanner.nextLine());
        System.out.print("Teor Alcoólico (%): ");
        double teor = Double.parseDouble(scanner.nextLine());
        System.out.print("Volume (ml): ");
        int volume = Integer.parseInt(scanner.nextLine());

        Bebida novaBebida = new Bebida(codigo, nome, preco, teor, volume);
        bebidas.add(novaBebida);
        System.out.println("Bebida cadastrada com sucesso!");
    }

    private static void listarBebidas() {
        System.out.println("\n--- Cardápio / Bebidas Disponíveis ---");
        if (bebidas.isEmpty()) {
            System.out.println("Nenhuma bebida cadastrada.");
            return;
        }
        for (Bebida b : bebidas) {
            System.out.println(b.exibirDetalhes());
        }
    }

    private static void criarPedido() {
        System.out.println("\n--- Novo Pedido ---");
        System.out.print("Número da Mesa: ");
        int mesa = Integer.parseInt(scanner.nextLine());
        Pedido pedido = new Pedido(mesa);

        String continuar;
        do {
            listarBebidas();
            System.out.print("Digite o código da bebida para adicionar ao pedido: ");
            int codBebida = Integer.parseInt(scanner.nextLine());
            
            Bebida bebidaEncontrada = null;
            for (Bebida b : bebidas) {
                if (b.getCodigo() == codBebida) {
                    bebidaEncontrada = b;
                    break;
                }
            }

            if (bebidaEncontrada != null) {
                System.out.print("Quantidade: ");
                int qtd = Integer.parseInt(scanner.nextLine());
                pedido.adicionarItem(bebidaEncontrada, qtd);
                System.out.println("Item adicionado ao pedido!");
            } else {
                System.out.println("Bebida não encontrada.");
            }

            System.out.print("Deseja adicionar mais itens? (s/n): ");
            continuar = scanner.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        pedidos.add(pedido);
        System.out.printf("Pedido da Mesa %d gerado com sucesso! Total: R$ %.2f\n", mesa, pedido.calcularTotal());
    }

    private static void listarPedidos() {
        System.out.println("\n--- Pedidos Realizados ---");
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
            return;
        }
        for (Pedido p : pedidos) {
            System.out.printf("Mesa: %d | Status: %s | Total: R$ %.2f\n", 
                    p.getNumeroMesa(), p.getStatus(), p.calcularTotal());
            for (ItemPedido item : p.getItens()) {
                System.out.printf("   - %dx %s (R$ %.2f cada)\n", 
                        item.getQuantidade(), item.getBebida().getNome(), item.getBebida().getPreco());
            }
        }
    }
}
