<h1 align="center"> 💻 Sobre o desafio </h1> 



<div align="center" > 
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>
    <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
    <img src="https://img.shields.io/badge/Insomnia-5849be?style=for-the-badge&logo=Insomnia&logoColor=white"/>
    <img src="https://img.shields.io/badge/Heroku-430098?style=for-the-badge&logo=heroku&logoColor=white"/>
    <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white"/>
</div>

<br> 



``` 
 Olá! Este é um desafio SpringBoot + JPA + postgresSQL para gerenciar seus pertences emprestados!

 O objetivo é construir uma API para você não esquecer para quem emprestou cada coisa.

 O usuário se cadastra e seus dados ficam salvos

 A aplicação deve receber o item emprestado, a data de devolução prevista, e contato do amigo

 Cada empréstimo tem 20 dias de prazo, ao cadastrar um item emprestado o sistema deve salvar a data atual

 Ao devolver um Item o sistema deve salvar a data da devolução do Item 

 O sistema deve permitir avaliar um amigo após a devolução do empréstimo

 Um Item só pode ser emprestado novamente caso o Amigo tenha recebido alguma avaliação

 Um Amigo deve ter um Dono associado

 Um Item emprestado precisa ter um Dono e um Amigo emprestimo associado

 Um Item deve ter um status atual definido como EMPRESTADO ou DEVOLVIDO
 ```
<br> 

 ### Status do Desafio: **`Concluido`** ✔

 <h2 align="center">🏁 A API deve disponibilizar: </h2>
  
 ### 🚀 Funcionalidades 
 
 [✔] Cadastro de Dono
 <br> 
 [✔] Listagem de Donos
 <br> 
 [✔] Listagem de Donos por id
 <br> 
 [✔] Listagem de Donos por nome
 <br> 
 [✔] Alteração de Dono por id
 <br> 
 [✔] Remoção de Dono por id

 [✔] Cadastro de Amigo
 <br> 
 [✔] Listagem de Amigos
 <br> 
 [✔] Listagem de Amigos piores avaliados
 <br> 
 [✔] Listagem de Amigos melhores avaliados
 <br> 
 [✔] Listagem de Amigos que são conhecidos por um Dono x
 <br> 
 [✔] Listagem de Amigos por id
 <br> 
 [✔] Remoção de Amigo por id
 
 [✔] Cadastro de Item
 <br> 
 [✔] Listagem de Itens 
 <br> 
 [✔] Listagem de Itens por id
 <br> 
 [✔] Listagem de Itens por nome
 <br> 
 [✔] Listagem de Itens EMPRESTADOS
 <br> 
 [✔] Listagem de Itens DEVOLVIDOS
 <br> 
 [✔] Listagem de Itens pertencentes a um Dono x
 <br> 
 [✔] Emprestar um Item para um Amigo
 <br> 
 [✔] Emprestar um Item NOVAMENTE para um Amigo x
 <br> 
 [✔] Receber um Item de volta e avaliar o Amigo
 <br> 
 [✔] Remoção de um Item

## 🥇 Aplicação disponivel no Heroku:

https://givemeback-api.herokuapp.com

## Serviços disponíveis 

### 👨‍💻 Dono:

**[GET]** https://givemeback-api.herokuapp.com/donos/buscar_por_nome?nomeDono=Samuel 
<br>
**[GET]** https://givemeback-api.herokuapp.com/donos/buscar_por_id/4
<br>
**[GET]** https://givemeback-api.herokuapp.com/donos/listar_todos

```json
   "id": 1,
   "nome": "Samuel",
   "whatsapp": "555-777-522"
``` 

### 🙅‍♂️ Amigo:

**[GET]** https://givemeback-api.herokuapp.com/amigos/buscar_por_nome?amigoEmprestimo=José
<br>
**[GET]** https://givemeback-api.herokuapp.com/amigos/buscar_por_id/8
<br>
**[GET]** https://givemeback-api.herokuapp.com/amigos/buscar_por_dono_item?idAmigo=2&idDono=2
<br>
**[GET]** https://givemeback-api.herokuapp.com/amigos/listar_todos
<br>
**[GET]** https://givemeback-api.herokuapp.com/amigos/buscar_melhores_avaliados
<br>
**[GET]** https://givemeback-api.herokuapp.com/amigos/buscar_piores_avaliados
<br>
**[POST]** https://givemeback-api.herokuapp.com/amigos/amigos/adicionar?nomeDono=Alladin
<br>
**[PUT]** https://givemeback-api.herokuapp.com/amigos/amigos/alterar/1
<br>
**[DELETE]** https://givemeback-api.herokuapp.com/amigos/deletar/1

```json

   "id": 2,
   "nome": "Pedro",
   "whatsApp": "123",
   "endereco": "rua do Pedro",
   "amigoDono": "Steve Jobs",
   "avaliacao": "OTIMA"
``` 

### 🎁 Item:

**[GET]** https://givemeback-api.herokuapp.com/itens/buscar_por_id/1
<br>
**[GET]** https://givemeback-api.herokuapp.com/itens/buscar_por_nome?nomeItem=bone
<br>
**[GET]** https://givemeback-api.herokuapp.com/itens/listar_itens
<br>
**[GET]** https://givemeback-api.herokuapp.com/itens/status_itens?status=EMPRESTADO
<br>
**[GET]** https://givemeback-api.herokuapp.com/itens/status_itens?status=DEVOLVIDO
<br>
**[GET]** https://givemeback-api.herokuapp.com/itens/emprestados_para?idAmigo=3
<br>
**[GET]** https://givemeback-api.herokuapp.com/itens/meus_itens?idDono=2
<br>
**[GET]** https://givemeback-api.herokuapp.com/itens/meus_itens?idDono=2
<br>
**[POST]** https://givemeback-api.herokuapp.com/itens/emprestar_item?dono=1&idAmigoEmprestimo=1
<br>
**[PUT]** https://givemeback-api.herokuapp.com/itens/emprestar_novamente?idItem=7&idAmigoEmprestimo=1
<br>
**[PUT]** https://givemeback-api.herokuapp.com/itens/devolver/7?nomeAmigo=José&avaliacao=OTIMA
<br>
**[DELETE]** https://givemeback-api.herokuapp.com/itens/itens/deletar/7


### Item quando Emprestado:

```json
   "id": 7,
   "nome": "Sapato",
   "desc": "um sapato Nike",
   "nomeDono": "Samuel Almeida",
   "status": "EMPRESTADO",
   "emprestado_para": "Silvestre Stallone Cobra 22",
   "prazoPadrao": "Cada emprestimo tem 20 dias de prazo.",
   "dataEmprestimo": "2021-06-01",
   "dataDevolucao": "2021-06-21"
```

### Item quando devolvido:

```json
      "id": 1,
      "nome": "video game",
      "desc": "um video game",
      "nomeDono": "Samuel Almeida",
      "status": "DEVOLVIDO",
      "emprestado_para": "ninguém",
      "prazoPadrao": "Cada emprestimo tem 20 dias de prazo.",
      "dataEmprestimo": "2021-06-01",
      "dataDevolucao": "2021-06-01"
```
<br>

### 🎯 Diagrama UML
<img align="center" src="https://github.com/samuelalmeida95/giveMeBack-api/blob/main/diagramaUML.png"></img>
## 📝 Licença


Esse projeto está sob a licença MIT. Veja o arquivo <a href="https://github.com/samuelalmeida95/giveMeBack-api/blob/main/LICENSE">LICENSE</a> para mais detalhes.


<hr>
<p align="center">Feito com 💚 by Samuel Almeida</p>
