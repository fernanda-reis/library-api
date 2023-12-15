# library-api
Projeto de API de uma biblioteca virtual para o módulo 3 do curso #btgfaztech da ADA.

Especificações: 
- Java 17
- Banco de dados H2
- Spring Security 3.1.4 

Como utilizar:
- baixe o arquivo AdaLibrary_Collection.json e importe no programa Postman para testar os endpoints;
- execute a classe LibraryApplication;
- execute o endpoint POST /user para criação de novo usuário;
- execute o endpoint POST /login para recuperar o token de acesso de usuário cadastrado;
- insira o token no formato "Bearer " + {token}" como valor da chave Authentication na aba Headers para ter acesso a todos os endpoints.

📌 [Testes End-to-End](https://github.com/fernanda-reis/library-api-test)
