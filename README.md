# 📚 LiterAlura
![Java](https://img.shields.io/badge/Java-17+-red)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![API](https://img.shields.io/badge/API-Gutendex-orange)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)

## 📖 Sobre o Projeto
**LiterAlura** é uma aplicação Java desenvolvida com **Spring Boot** que funciona como um **catálogo de livros via console**.  
O sistema consome dados da **API Gutendex** (Projeto Gutenberg) para buscar informações sobre livros e armazená-las em um **banco de dados PostgreSQL**.

O objetivo do projeto é praticar conceitos como:
- Consumo de APIs REST
- Persistência de dados com **Spring Data JPA**
- Integração com **PostgreSQL**
- Estruturação de aplicações **Spring Boot**
- Manipulação de dados e consultas no banco

---

## ⚙️ Tecnologias Utilizadas
- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **API Gutendex**
- **Jackson (JSON parsing)**

---

## 🌐 API Utilizada
A aplicação utiliza a API pública do **Projeto Gutenberg**.  
Documentação: https://gutendex.com/

Exemplo de requisição:
```
https://gutendex.com/books/?search=dom%20casmurro
```

---

## 🚀 Funcionalidades

### 🔎 Buscar livro por título
- Consulta a API Gutendex
- Recupera informações do livro
- Armazena no banco de dados caso ainda não esteja cadastrado

### 📚 Listar livros registrados
Exibe todos os livros armazenados no banco de dados.

### 👨‍🏫 Listar autores registrados
Mostra todos os autores presentes no banco de dados com nome, ano de nascimento e falecimento.

### 🕰 Listar autores vivos em determinado ano
Informa um ano específico e retorna todos os autores que estavam vivos naquele período.

### 🌎 Listar livros por idioma
Filtra livros registrados por idioma:

| Código | Idioma |
|--------|--------|
| pt | Português |
| en | Inglês |
| es | Espanhol |
| fr | Francês |

---

## 🛠️ Como Configurar o Projeto

### Pré-requisitos
- Java 17 ou superior
- Maven
- PostgreSQL
- Git

### 1. Clonar o Repositório
```bash
git clone https://github.com/Hitwhatt/literalura.git
cd literalura
```

### 2. Criar o Banco de Dados
No PostgreSQL execute:
```sql
CREATE DATABASE literalura;
```

### 3. Configurar o `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Executar o Projeto
```bash
./mvnw spring-boot:run
```

---

## 🖥️ Menu da Aplicação
```
========== LITERALURA ==========
1 - Buscar livro por título
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em determinado ano
5 - Listar livros em determinado idioma
0 - Sair
=================================
```

---

## 🎯 Objetivo Educacional
Projeto desenvolvido como parte dos estudos em Java, Spring Boot e consumo de APIs, dentro do programa ONE (Oracle Next Education) em parceria com a Alura.

---

## 📜 Licença
Projeto destinado a fins educacionais. Os dados são fornecidos pela API Gutendex, baseada no acervo do Projeto Gutenberg.
