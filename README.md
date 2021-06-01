<h1 align="center"> 💻 Sobre o desafio </h1> 



<div align="center" > 
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>
    <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
    <img src="https://img.shields.io/badge/Insomnia-5849be?style=for-the-badge&logo=Insomnia&logoColor=white"/>
    <img src="https://img.shields.io/badge/Heroku-430098?style=for-the-badge&logo=heroku&logoColor=white"/>
    <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white"/>
</div>

<br> 

```php

Olá! Este é um desafio SpringBoot + JPA + postgresSQL para gerenciar seus pertences emprestados!

O objetivo é construir uma API para você não esquecer para quem emprestou cada coisa.

O usuário se cadastra e seus dados ficam salvos

A aplicação deve receber o item emprestado, a data de devolução prevista, e contato do amigo

Por padrão cada empréstimo de item tem 15 dias de prazo, ao cadastrar um item emprestado o sistema deve salvar a data atual

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

<br> 

## 🥇 Aplicação disponivel em:

https://givemeback-api.herokuapp.com

<br>

### Serviços disponíveis 

### DONO:

[GET] https://givemeback-api.herokuapp.com/donos/buscar_por_nome?nomeDono=Samuel Almeida
<br>
[GET] https://givemeback-api.herokuapp.com/donos/buscar_por_id/4
<br>
[GET] https://givemeback-api.herokuapp.com/donos/listar_todos

```json

   "id": 1,
   "nome": "Samuel Almeida",
   "whatsapp": "555-777-522"
```   



### 🎯 Diagrama UML
<img align="center" src="https://github.com/samuelalmeida95/giveMeBack-api/blob/main/diagramaUML.png"></img>
## 📝 Licença


Esse projeto está sob a licença MIT. Veja o arquivo <a href="https://github.com/samuelalmeida95/giveMeBack-api/blob/main/LICENSE">LICENSE</a> para mais detalhes.


<hr>
<p align="center">Feito com 💚 by Samuel Almeida</p>
