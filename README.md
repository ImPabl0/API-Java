# API Java
Uma API feita em java para registro de clientes no banco de dados MySQL

## Setup
Você precisará do JDK e Maven devidamente instalados e uma DB MySQL hospedada(Eu hospedei na minha própria máquina utilizando o xampp).
Para criar a DB você precisará executar a seguinte query: 

~~~~mysql
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` text NOT NULL,
  `sobrenome` text NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `email` text NOT NULL,
  `senha` varchar(50) NOT NULL,
  `salt` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
~~~~


Após isso extraia o projeto, abra a pasta onde você extraiu através do CMD e digite ``mvn spring-boot:run``
## Referências
- [Como criar uma REST API com Spring Boot (tutorial passo a passo)](https://www.youtube.com/watch?v=9GWK9A79tEc)
- [Java: variáveis e constantes](https://www.devmedia.com.br/java-variaveis-e-constantes/38311)
- [How to Build a Spring Boot REST API with Java?](https://hevodata.com/learn/spring-boot-rest-api/)
- [Using Spring @ResponseStatus to Set HTTP Status Code](https://www.baeldung.com/spring-response-status)
- [Hashing passwords in Spring applications](https://nullbeans.com/hashing-passwords-in-spring-applications/)
## Documentação da API

#### Retorna nulo com o código 200 em caso de sucesso

```http
  POST /register
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `nome` | `string` | **Obrigatório**. O nome do cliente |
| `sobrenome`|`string`|**Obrigatório** O sobrenome do cliente|
| `cpf`|`string`|**Obrigatório** O cpf do cliente|
| `email`|`string`|**Obrigatório** O e-mail do cliente|
| `senha`|`string`|**Obrigatório** A senha do cliente|


