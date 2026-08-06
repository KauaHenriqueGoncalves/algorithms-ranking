# Sistema de Ranking de Jogadores

API REST desenvolvida como projeto para a disciplina, com o objetivo de aplicar estruturas de dados e algoritmos de busca e ordenação. O sistema permite cadastrar jogadores, gerar registros aleatórios, buscar por nome e ordenar o ranking por pontuação.

## Tecnologias

- Java
- Spring Boot
- Spring Data JPA
- H2 Database (banco em memória)

## Funcionalidades

O sistema permite cadastrar jogadores manualmente ou gerar registros aleatórios automaticamente, com nome, categoria e pontuação. Também é possível listar todos os jogadores cadastrados e buscar um jogador específico pelo nome, utilizando busca linear.

## Ordenação

O ranking pode ser ordenado por pontuação utilizando três algoritmos diferentes, escolhidos dinamicamente através de um parâmetro na requisição, graças ao padrão de projeto Strategy:

- Bubble Sort
- Selection Sort
- Insertion Sort

Cada algoritmo está isolado em sua própria implementação, o que facilita a manutenção e permite adicionar novos métodos de ordenação futuramente sem alterar a lógica da camada de serviço.