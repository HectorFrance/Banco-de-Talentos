# Banco de Talentos - Plataforma para Tech Recruiters

Este projeto é uma aplicação fullstack desenvolvida como estudo, com o objetivo de criar uma **plataforma para recrutadores técnicos** gerenciarem seus próprios bancos de talentos. Através dela, é possível cadastrar candidatos, organizar suas informações, habilidades técnicas e registrar observações pessoais.

---

## Tecnologias Utilizadas

### Backend
- **Java 24**
- **Spring Boot**
  - Spring Data JPA
  - Spring Security (JWT)
  - Spring Web
- **MySQL**
- **Swagger** (Documentação da API)
- **Docker** (para conteinerização)

### Frontend
- **Angular 17**
- **TypeScript**
- **Bootstrap**

---

## Funcionalidades

### Para Recrutadores:
- Cadastro e login seguro com **JWT (access & refresh token)**
- Cadastro de candidatos com:
  - Nome completo
  - Contato
  - Stack principal
  - Lista de **skills** com nível (ex: Junior, Pleno, Sênior)
  - Observações personalizadas
- Edição e remoção de candidatos
- Filtro e pesquisa por nome, stack ou skill
- Dashboard com visão geral dos candidatos cadastrados

---
