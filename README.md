# PestDetect

Repositório pro projeto de Digital Business Enablement

---

## Endpoints

- Usuário
  - [Cadastrar](#cadastrar-usuário)
  - [Editar](#editar-usuário)
  - [Deletar](#deletar-usuário)

- Peste
  - [Lista](#lista-de-pestes)
  - [Adicionar](#adicionar-pestes)
  - [Editar](#editar-pestes)
  - [Excluir](#excluir-peste)

---
### Cadastrar Conta
`POST` /api/contas

**Campos da requisição**

| Campo | Tipo | Obrigatório | Descrição | 
|-------|------|-------------|-----------|
| nome |String|  Sim | Aqui o usuário deverá preencher o campo com o seu nome ou apelido. | 
| email |String|  Sim | Aqui o usuário deverá preencher o campo com o seu e-mail. | 
| senha |String|  Sim | Aqui o usuário deverá preencher o campo com uma senha. | 

 Corpo de requisição 

```js
{
    login: 'mateus@email.com',
    senha: '205478'
}
```

 Códigos de resposta 
| Código | Descrição | 
|--------|------|
|200|dados retornados com sucesso|
|400|campos não preenchidos|

---

### Editar Conta
`PUT` /api/contas/{id}

 **Corpo de requisição**

```js
{
    login: 'joao@email.com',
    senha: '215579'
}
```

 Códigos de resposta 
| Código | Descrição | 
|--------|------|
|200|dados alterados com sucesso|
|400|campos não preenchidos|
|404|conta não encontrada|

---

### Deletar Usuário
`DELETE` /api/contas/{id}

Códigos de resposta 
| Código | Descrição | 
|--------|------|
|200|conta excluída com sucesso|
|404|conta não encontrada|

---

### Adicionar Peste

`POST` /api/peste

**Campos da requisição**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
|nome popular | string | sim | o nome popular da peste
|nome científico | string | sim | o nome científico da peste
|tipo| string | sim | o tipo da peste
|método de controle| string | não | métodos de controle usados p/ eliminar a pesta

**Exemplo de corpo de requisição**

```js
{
    nomepopular: 'Antracnose',
    nomecientifico: 'Colletotrichum truncatum',
    tipo: 'Fungo',
    metododecontrole: 'Fungicidas'
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 201 | peste cadastrada com sucesso
| 400 | campos não preenchidos
---

### Editar Peste

`PUT` api/peste/{id}

**Exemplo de corpo de requisição**

```js
{
    nomepopular: 'Lagarta-do-cartucho',
    nomecientifico: 'Spodoptera frugiperda',
    tipo: 'Lagarta',
    metododecontrole: 'Inseticidas'
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 202 | peste alterada com sucesso
| 400 | campos não preenchidos ou faltando
| 404 | nenhum peste com o id informado
---

### Excluir Peste

`DELETE` api/peste/{id}

**Códigos de Resposta**

| código | descrição
|-|-
| 202 | peste excluída com sucesso
| 404 | nenhuma peste com o id informado
---

### Lista de Pestes

**Campos da requisição**

`GET` /api/peste

**Exemplo de corpo de requisição**
```js
{
    id: 1,
    nomepopular: 'Lagarta-do-cartucho',
    nomecientifico: 'Spodoptera frugiperda',
    tipo: 'Lagarta',
    metododecontrole: 'Inseticidas'
}
```
```js
{
    id: 2,
    nomepopular: 'Pulgão',
    nomecientifico: 'Aphidoidea',
    tipo: 'Inseto',
    metododecontrole: 'Inseticidas, predadores naturais.'
}
```
**Códigos de Resposta**

| código | descrição
|-|-
| 200 | dados retornados com sucesso
| 404 | nenhum peste encontrado
