# PetHealth - Sistema de Gerenciamento de Clínicas Veterinárias 🐾

O **PetHealth** é uma API desenvolvida para gerenciar donos de pets e seus respectivos bichinhos. Este projeto tem como objetivo servir como um sistema backend de suporte para clínicas veterinárias, organizando informações de forma eficiente e prática. Ideal para demonstração de habilidades em desenvolvimento backend com **Java** e **Spring Boot**.

---

## 🚀 Funcionalidades

### Donos de Pets (Owners):

- **Cadastrar** um novo dono.
- **Consultar** informações de um dono pelo ID.
- **Listar** todos os donos cadastrados.
- **Atualizar** os dados de um dono existente.
- **Excluir** um dono.

### Pets:

- **Cadastrar** um novo pet associado a um dono.
- **Consultar** informações de um pet pelo ID.
- **Listar** todos os pets cadastrados.
- **Atualizar** informações de um pet.
- **Excluir** um pet.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot** (3.x)
  - Spring Web
  - Spring Data JPA
- **H2 Database** (banco de dados em memória para testes)
- **JUnit 5** (testes automatizados)
- **Maven** (gerenciador de dependências)

---

## 📦 Estrutura do Projeto

```bash
src/
├── main/
│   ├── java/
│   │   └── com.pethealth/
│   │       ├── controller/   # Contém os controladores (endpoints da API)
│   │       ├── model/        # Contém as classes de modelo (entidades)
│   │       ├── repository/   # Contém as interfaces de acesso ao banco de dados
│   │       ├── service/      # Contém a lógica de negócios
│   ├── resources/
│       ├── application.properties  # Configurações do projeto
├── test/
    └── java/com.pethealth/   # Testes unitários e de integração
```

---

## ⚙️ Como Rodar o Projeto

### Pré-requisitos

- **JDK 17+**
- **Maven**

### Passos

1. Clone este repositório:

   ```bash
   git clone https://github.com/O-Farias/PetHealth.git
   cd PetHealth
   ```

2. Instale as dependências:

   ```bash
   mvn clean install
   ```

3. Rode o projeto:

   ```bash
   mvn spring-boot:run
   ```

4. Acesse a aplicação:

   - A API estará disponível em: `http://localhost:8080`

5. (Opcional) Acesse o console do banco H2:
   - URL: `http://localhost:8080/h2`
   - Username: `sa`
   - Password: (deixe em branco)

---

## 🔗 Endpoints Principais

### Donos de Pets (Owners):

- `GET /api/owners` - Retorna todos os donos.
- `GET /api/owners/{id}` - Retorna um dono pelo ID.
- `POST /api/owners` - Cadastra um novo dono.
- `PUT /api/owners/{id}` - Atualiza informações de um dono.
- `DELETE /api/owners/{id}` - Remove um dono.

### Pets:

- `GET /api/pets` - Retorna todos os pets.
- `GET /api/pets/{id}` - Retorna um pet pelo ID.
- `POST /api/pets` - Cadastra um novo pet.
- `PUT /api/pets/{id}` - Atualiza informações de um pet.
- `DELETE /api/pets/{id}` - Remove um pet.

---

## ✅ Testes Automatizados

Os testes estão implementados para garantir a qualidade do código e validar as funcionalidades principais.

### Rodar os testes:

```bash
mvn test
```

### Cobertura dos testes:

- Testes unitários para os serviços (`OwnerService` e `PetService`).
- Testes de integração para os controladores (`OwnerController` e `PetController`).

---

## 📄 Licença

Este projeto está sob a licença MIT. Sinta-se à vontade para utilizá-lo como referência ou base para outros projetos.
