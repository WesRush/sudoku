# Sudoku em Java - Desafio de Projeto DIO



## 📖 Sobre o Projeto

Este projeto foi desenvolvido como conclusão do desafio de código **"Entendendo o Desafio"**, proposto pela [Digital Innovation One (DIO)](https://dio.me/).

> O desafio consistia em explorar os conceitos de Programação Orientada a Objetos com Java para replicar (ou melhorar) um projeto prático, criando um repositório próprio para compor um portfólio de destaque no GitHub.

A aplicação consiste em um **jogo de Sudoku totalmente funcional que roda no console**. O objetivo foi criar uma estrutura de código limpa, coesa e que aplicasse os princípios da orientação a objetos, como encapsulamento, abstração e polimorfismo, de forma prática.

## ✨ Funcionalidades

O jogo oferece uma interface de menu interativa no console, com as seguintes funcionalidades:

* **Iniciar um novo jogo**: Inicia um novo jogo com um tabuleiro aleatório, selecionado de uma biblioteca interna, garantindo uma experiência diferente a cada partida.
* **Inserir e Remover Números**: Permite que o jogador insira números nas casas vazias do tabuleiro e remova-os, caso queira corrigir uma jogada.
* **Validação de Posições**: O sistema impede que o jogador altere os números que fazem parte do desafio original (números fixos).
* **Visualização do Tabuleiro**: A qualquer momento, o jogador pode visualizar o estado atual do seu jogo.
* **Status do Jogo**: O jogador pode verificar se o tabuleiro atual contém erros (números repetidos em linhas, colunas ou blocos 3x3).
* **Limpar o Progresso**: Opção para reiniciar o tabuleiro atual, mantendo os números fixos originais.
* **Finalizar o Jogo**: Verifica se o tabuleiro está completo e sem erros, declarando a vitória ou informando o que falta para concluir.

## 🛠️ Tecnologias Utilizadas

* **Java 17+**: Versão do Java utilizada para desenvolver toda a lógica do jogo.
* **Programação Orientada a Objetos (POO)**: Conceito central para a modelagem das classes `Board`, `Space`, etc.


