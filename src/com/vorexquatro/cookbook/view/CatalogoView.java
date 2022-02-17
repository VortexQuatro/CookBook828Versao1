package com.vorexquatro.cookbook.view;

import com.vorexquatro.cookbook.controller.Catalogo;
import com.vorexquatro.cookbook.domain.Receita;

import java.util.Locale;
import java.util.Scanner;

public class CatalogoView {
    private Catalogo controller;
    private Receita ative;
    private int currentIndex;

    public CatalogoView(Catalogo controller) {
        this.controller = controller;
        if (controller.getTotal() > 0) {
            currentIndex = 1;
            ative = controller.getReceita(currentIndex);
        } else {
            currentIndex = -1;
            ative = null;
        }
    }

    public void add() {
        //Capturar o nome da receita.
        //Procura no Catalogo a receita com o mesmo nome.
        //Se encontrar, mostra mensagem.
        //Se NÃO encontrar continua.
        //Capturar dados da nova receita.
        //Cria uma nova receita
        //Passa a receita para o Catalogo adicionar.
        //Torna a nova receita a ativa.
//        view();
    }

    public void find() {
        Scanner scanner = new Scanner(System.in);                                       // Rubens
        //Capturar o nome da receita.
        String stringAux;                                                               // Rubens
        System.out.println("Digiye o nome da receita a ser localizada: ");              // Rubens
        stringAux = scanner.nextLine().trim().toUpperCase(Locale.getDefault());         // Rubens
        Receita receita = new Receita();                                                // Rubens
        receita = controller.getReceita(stringAux);                                     // Rubens
        if (receita != null){                                                           // Rubens
            ative = receita;                                                            // Rubens
        }                                                                               // Rubens
        else{                                                                           // Rubens
            System.out.println("Receita não cadastrada!");                              // Rubens
        }                                                                               // Rubens
        //Procura no Catalogo a receita com o mesmo nome.
//        view();
    }

    public void view() {
        String tela = "";
        do {
            if (ative == null) {
                //Se NÃO estiver com uma receita ativa, mostra mensagem.
                tela = "Não existe receita cadastrada!";
            } else {
                //Se estiver com uma receita ativa, continua.
                //Monta o layout da tela com os dados da receita.
                tela = ative.toString();
            }
            //Exibe o layout montado.
            System.out.println(tela);
            //Exibe o menu de opções.
        } while (showMenu());
    }

    private boolean showMenu() {
        System.out.println("#".repeat(100));
        System.out.println("  + : Adicionar  ");
        if (ative != null) {
            System.out.println("  - : Remover  ");
        }
        if (controller.getTotal() > 0) {
            System.out.println("  P : Próxima  ");
            System.out.println("  A : Anterior  ");
            System.out.println("  L : Localizar  ");
        }
        System.out.println("# # # # # # # # # # # ".concat("#".repeat(78)));
        System.out.println("  X : Sair  ");
        System.out.println("#".repeat(100));

        Scanner scanner = new Scanner(System.in);
        String opcao = scanner.nextLine().trim().toUpperCase(Locale.getDefault());
        switch (opcao) {
            case "+":
                add();
                break;
            case "-":
                if (ative != null) del();
                break;
            case "P":
                if (ative != null) next();
                break;
            case "A":
                if (ative != null) previous();
                break;
            case "L":
                if (controller.getTotal() > 0) {
                    find();
                }
                break;
            case "X":
                System.out.println("Obrigado!!");
//                System.exit(0);
                return false;
            default:
                System.out.println("Opção inválida!!!");
//                view();
        }
        return true;
    }

    public void next() {
        //Se estiver com uma receita ativa, ativa a próxima receita.
        //Se NÃO estiver com uma receita ativa, ativa a primeira receita.
        if (ative != null) currentIndex++;
        try {
            ative = controller.getReceita(currentIndex);
        } catch (IllegalArgumentException e) {
            ative = null;
        }
        if (ative == null) {
            currentIndex = 1;
            ative = controller.getReceita(currentIndex);
        }
//        view();
    }

    public void previous() {
        //Se estiver com uma receita ativa, ativa a anterior receita.
        //Se NÃO estiver com uma receita ativa, ativa a última receita.
        if (currentIndex == 1) { // Rubens
            currentIndex = controller.getTotal() + 1;
        }
        if (ative != null) currentIndex--;
//        try {
//            ative = controller.getReceita(currentIndex);
//        } catch (IllegalArgumentException e) {
//            ative = null;
//        }
//        if (ative == null) {
//            currentIndex = controller.getTotal();
//            currentIndex = currentIndex <= 0 ? 1 : controller.getTotal();
              currentIndex = currentIndex <= 0 ? 1 : currentIndex; // Rubens
            ative = controller.getReceita(currentIndex);
//        }
//        view();
    }

    public void del() {
        //Se NÃO estiver com uma receita ativa, mostra mensagem.
        //Se estiver com uma receita ativa, confirma a operação.
        //Se confirmar, solicita ao Catalogo apagar a receita.
        System.out.println("Você deseja realmente APAGAR a receita " + ative.getNome() + "?\nS - Sim   N - Não");
        Scanner scanner = new Scanner(System.in);
        String opcao;
        do {
            opcao = scanner.nextLine().trim().toUpperCase(Locale.getDefault());
            if (opcao.equals("S")) {
                controller.del(ative.getNome());
                ative = null;
//                currentIndex = 1;
//                next();
                currentIndex = 1;                                   // Rubens
                if (controller.getTotal() >= 1) {                   // Rubens
                     ative = controller.getReceita(currentIndex);   // Rubens
                }                                                   // Rubens
                else{                                               // Rubens
                    ative = null;                                   // Rubens
                }                                                   // Rubens
                break;
            } else if (opcao.equals("N")){
//                view();
                break;
            } else {
                System.out.println("Opção inválida!!!");
            }
        } while (true);
    }

    public void edit() {
        //Se NÃO estiver com uma receita ativa, mostra mensagem.
        //Se estiver com uma receita ativa, abra a tela de edição.
//        view();
    }
}
