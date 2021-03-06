---------------------------------------------------------------------------------------------------------
<h1>Seção 01 - Consultando os dados</h1>

Banco de dados e SQL
Business Inteligence
Excel é uma ferramenta para guardar dados, entretanto ele tem um limite e dificulta

Banco de dados ajuda a você extrair informações de N formas diferentes.
SQL é uma linguagem para se comunicar com o banco.

SQL ---> Structured Query Language


Um problema muito comum que temos é controlar nossos gastos durante o ano, por exemplo, para compararmos o quanto a mais gastamos em janeiro e dezembro nas festas de final de ano do que com o quanto gastamos durante o período de férias de meio de ano com os gastos de supermercado.

Armazenar dados, para depois pesquisá-los e manipulá-los é uma necessidade comum no mercado de informática.

Uma possibilidade para realizar esse armazenamento de informações é através de planilhas eletrônicas, como o Excel, onde podemos ter algumas colunas tais como dia, valor e motivo da compra. No entanto, apesar de ser um ambiente interessante, o Excel se torna complexo quando precisamos extrair e manipular suas informações. Se necessitarmos utilizar esses dados através de alguma aplicação ou sistema, isso pode se tornar ainda mais complexo, pois será necessário fazer a leitura dos arquivos do Excel para conseguirmos consultar as informações.

Para simplificar esse trabalho, existem os softwares de bancos de dados, que nos permitem armazenar e manipular informações de uma maneira mais simples através de uma linguagem de manipulação de dados chamada SQL (Structured Query Language). Para conseguirmos utilizar essa linguagem, precisamos instalar um software (servidores de banco de dados) que nos permita armazenar essas informações, dos quais se destacam o MySQL, Oracle e SQLServer. Durante este curso utilizaremos o MySQL.

O MySQL é um software que pode ser instalado seguindo as maneiras tradicionais de cada sistema operacional. Por exemplo, no Windows, o processo é realizado através do download de um arquivo .msi, enquanto no Linux (versões baseadas em Debian), pode ser feito através de um simples comando apt-get, e no MacOS pode ser instalado com um pacote .dmg. Para realizar a instalação do servidor MySQL, basta baixar também o instalador. No exercício, você terá os links certos para isso.

Criando o nosso banco de dados
Durante todo o curso usaremos o terminal do MySQL. Apesar dele não ser a melhor interface gráfica, ele é igual para todas as plataformas, e não muda de versão para versão. Se você já conhecer alguma, não há problema de fazer uso dela.

Abra o terminal do seu sistema operacional. No Windows, digite cmd no Executar. No Mac e Linux, abra o terminal. Nele, vamos entrar no MySQL:

mysql -uroot -p
Esse é o comando para nos conectarmos no MySQL que está instalado na máquina. O -u indica o usuário root, e o -p é porque digitaremos a senha. Como não há senha, pressione enter.

Vamos agora criar um banco de dados; afinal o MySQL pode tomar conta de vários bancos diferentes. O create database cria, e o use diz ao MySQL que é o banco que queremos usar.

create database controle_compras;
use controle_compras;
Agora, vamos criar nossa primeira tabela. Tabela é como se fosse uma planilha do Excel, onde temos colunas, cada uma representando uma informação diferente, por exemplo, nome do produto, preço, etc, e linhas, onde cada linha é um dado em particular, por exemplo, geladeira, 50 reais.

A instrução para se criar uma tabela para nosso sistema de compras é:

create table COMPRAS (id int auto_increment primary key, valor double, data date, observacoes varchar(255), recebido boolean);
Repare que na instrução acima, passamos o nome da tabela (COMPRAS), e depois a lista de colunas (id, valor, data, descrição e recebido). Passamos também o tipo de cada uma, ou seja valor é double, data é date, e assim por diante.

O id é um campo em particular, estamos usando ele como um identificador único para aquela linha. Pense no mundo real, o seu RG é uma chave importante, pois ele consegue identificar um brasileiro dentre todos os outros, ele é um número e é sequencial, alguém tinha o RG 1 e agora alguém tem o RG 223334446, essa chave é tão importante que chamamos de chave primária. A chave primária não precisa necessariamente ser sequencial, mas deve ser única: não devem existir duas pessoas com o mesmo RG, assim como não devem existir dois cursos com o mesmo código, dois produtos diferentes com o mesmo código de barra, etc. Por isso que dizemos que o campo id é primary key, ou seja, chave primária. Além disso, dizemos que ele é sequencial, serial, cresce de um em um, isto é, um número inteiro que é incrementado automaticamente a cada novo produto: int auto_increment.

Consultando dados
Queremos inserir na tabela COMPRAS, então começamos a instrução:

 INSERT INTO COMPRAS
Em seguida, passamos os valores que queremos inserir. Por exemplo, suponha uma compra de 100,00 no dia 12/05/2007, que foi recebida, e que tem a observação 'COMPRAS DE MAIO':

 INSERT INTO COMPRAS VALUES (100.0, '2007-05-12', 'COMPRAS DE MAIO', 1)
Separamos com vírgula cada um dos diferentes valores. Repare o uso de aspas simples (') quando o valor passado é uma data ou string. Veja também o valor 1 no lugar do campo recebido para indicar verdadeiro (colocaríamos 0 se quiséssemos passar falso).

Mas o MySQL não sabe que queremos colocar o valor 100.0 no campo valor ou 'COMPRAS DE MAIO' no campo observacoes; precisamos passar essa informação pra ele, ou seja, passar o nome de cada coluna que tem um valor a ser inserido:

 INSERT INTO COMPRAS (VALOR, DATA, OBSERVACOES, RECEBIDO) VALUES (100.0, '2007-05-12', 'COMPRAS DE MAIO', 1)
Observe o comando inicial. INSERT INTO recebe o nome da tabela (no nosso caso COMPRAS). Em seguida, passamos a lista de colunas que vamos colocar valores:(VALOR, DATA, OBSERVACOES, RECEBIDO). Por fim, passamos os valores que gostaríamos de inserir, usando o comando VALUES: VALUES (100.0, '2007-05-12', 'COMPRAS DE MAIO', 1). Todas instruções de INSERT são similares a essa: passamos o nome da tabela, o nome das colunas, e os valores a serem inseridos, separados por vírgula.

Pronto. Com essa compra já inserida nesse banco de dados, vamos começar a consultá-las. Se quiséssemos, por exemplo, ver todas as compras já cadastradas na minha lista, faríamos:

 SELECT * FROM COMPRAS
Entendendo o comando que acabamos de digitar: SELECT quer dizer que queremos executar a operação de seleção; * indica que queremos selecionar todas as colunas daquela tabela; FROM COMPRAS quer dizer que a tabela que queremos executar esse comando é a tabela "compras".

Antes de começarmos a discutir sobre seleções usando SQL, vamos primeiro importar alguns dados para a tabela que criamos na seção anterior. Faça o download do arquivo .sql Aqui. Abra o arquivo, e veja que ele contém apenas um monte de INSERTs. Importe todos eles, saindo do MySQL, e digitando a instrução abaixo. Ela basicamente pegará todas as instruções que está no arquivo cap2.sql e mandará para o MySQL.

mysql -uroot -p controle_compras < cap2.sql
Volte para o MySQL. Se quiséssemos selecionar apenas valor e data dessa tabela, faríamos:

 SELECT VALOR, DATA FROM COMPRAS
Podemos inclusive criar novas colunas a partir das já existentes. Por exemplo, caso quiséssemos exibir, além de valor e data, o triplo de cada valor, poderíamos fazer:

 SELECT VALOR, VALOR * 3, DATA FROM COMPRAS
Podemos até dar um nome para essa nova coluna, por exemplo, "triplo", usando a instrução AS:

 SELECT VALOR, VALOR * 3 AS TRIPLO, DATA FROM COMPRAS
Mas recuperar todas as informações de uma tabela de uma só vez não é comum. Geralmente aplicamos filtros sobre esses resultados. Vamos supor que precisássemos saber todas as compras com valor superior a 1.000,00 reais. Poderíamos deixar esse filtro claro na instrução SQL:

 SELECT * FROM COMPRAS WHERE VALOR > 1000
Repare o comando WHERE: ele é quem informa a SQL que filtraremos os resultados. Já a condição VALOR > 1000 deixa bem claro qual o filtro que queremos fazer.

Podemos montar filtros ainda mais complexos. Por exemplo, caso precisássemos de todas as compras com valor superior a 1.000,00 reais e inferior a 3.000,00, poderíamos fazer:

 SELECT * FROM COMPRAS WHERE VALOR > 1000 AND VALOR < 3000
Ainda mais complexa: filtrar todas as compras cujo valor é superior a 1.000,00 reais e inferior a 3.000,00 ou a data seja inferior a 12/02/2010:

 SELECT * FROM COMPRAS WHERE (VALOR > 1000 AND VALOR < 3000) OR (DATA < '2010-02-12')
Repare no uso dos parênteses; eles indicam que a linha deve ser selecionado caso VALOR > 1000 AND VALOR < 3000 ou DATA < '2010-02-12'. Você pode montar a expressão que quiser, usando ANDs, ORs. Além de maior e menor, a SQL suporta outros tipos de consultas, como por exemplo:

Maior-ou-igual:

SELECT * FROM COMPRAS WHERE VALOR >= 1000
Menor-ou-igual:

SELECT * FROM COMPRAS WHERE VALOR <= 1000
Diferente:

SELECT * FROM COMPRAS WHERE VALOR <> 1000
Podemos filtrar também por textos. Se quiséssemos, por exemplo, buscar todas as compras cuja observação seja igual a "COMPRAS DE JANEIRO", podemos fazer:

 SELECT * FROM COMPRAS WHERE OBSERVACOES = 'COMPRAS DE JANEIRO'
Repare o uso das aspas simples ('). Ela indica que o conteúdo que será passado é do tipo texto. O "igual" irá buscar por linhas que possuem essa observação. Mas as vezes queremos buscar por apenas uma parte do texto. Por exemplo, caso quiséssemos todas as compras cuja observação começasse com o texto "COMPRAS", independentemente do que viesse a seguir, deveríamos fazer:

 SELECT * FROM COMPRAS WHERE OBSERVACOES LIKE 'COMPRAS%'
O caractere % funciona como um coringa, ou seja, não importa o que houver dali pra frente. Ele pode ser usado em qualquer lugar da consulta. Por exemplo, caso quiséssemos todas as compras cuja observação contenha a palavra "COMPRA" em qualquer lugar, faríamos:

 SELECT * FROM COMPRAS WHERE OBSERVACOES LIKE '%COMPRAS%'
A instrução SELECT é poderosíssima. Boa sorte nos exercícios!

<h2>Populando o banco</h2>

Aluno: Se tiver conectado com MySQL, digite exit para sair do prompt do MySQL e voltar para o prompt padrão.

Clique aqui e faça o download do arquivo .sql, e importe no MySQL:

mysql -u root -p controle_compras < cap2.sql
Refaça o login no MySQL:

mysql -u root -p
Em seguida, execute o select para garantir que todas as informaçoes foram adicionadas:

select * from COMPRAS;
DICA: Salve o arquivo cap2.sql em uma pasta que você possa acessar facilmente através linha de comando. O arquivo deve estar no mesmo lugar onde você executará o comando:

mysql -u root -p controle_compras < cap2.sql
Tudo deu certo?

----------------------------------------------------------------------------------------------------------
<h1>Seção 02 - Atualizando e excluindo dados</h1>

Continuaremos com a estrutura de dados utilizada no capítulo anterior. Se você deseja reconstruí-la, baixe o arquivo do projeto que contém alguns INSERTs para criar compras na tabela COMPRAS. Importe o arquivo igual na aula passada.

mysql -uroot -p < NOME-DO-ARQUIVO.sql
Agora que já existem várias compras cadastradas, desejamos selecionar apenas as que possuem valor entre R$200,00 e R$700,00. Para isso usaremos a cláusula WHERE:

     SELECT * FROM COMPRAS WHERE valor >= 200 AND valor <= 700
Mas será que não existe algum operador mais simples?

     SELECT * FROM COMPRAS WHERE valor BETWEEN 200 AND 700
O operador BETWEEN serve para filtrar registros de acordo com um intervalo de valores. É possível filtrar apenas as compras realizadas entre 05/01/2010 e 25/06/2010.

     SELECT * FROM COMPRAS WHERE DATA BETWEEN '2010-01-05' AND '2010-06-25'
Mas o que precisamos fazer é alterar os registros criados durante esse período. Para todos eles vamos colocar a observação "compra emergencial". O comando SQL para realizar alteração em uma tabela é o UPDATE. Devemos informar o nome da coluna que será alterada e o novo valor. Por exemplo, se desejamos atualizar todas as compras para emergenciais, gostaríamos de algo como:

"Atualize as compras, alterando a observação para 'compra emergencial'"

Podemos fazer um UPDATE como o a seguir:

     UPDATE COMPRAS SET OBSERVACOES = 'compra emergencial'
O comando UPDATE atualiza os registros já existentes. Ele é seguido pelo nome da tabela, no nosso caso, COMPRAS. Também é preciso indicar quais colunas terão seu valor alterado, usando o comando SET seguido pelo nome das colunas e pelo novo valor desejado. Portanto, SET OBSERVACOES = 'compra emergencial' indica que queremos colocar o valor 'compra emergencial' na coluna OBSERVACOES.

No comando acima, em nenhum momento foi dito que essa alteração só deveria ser aplicada para as compras realizadas em um determinado período. Como não havia nenhuma restrição no comando, todos os registros foram alterados e agora todas as compras têm a mesma observação. Todas as restrições que aprendemos até agora ao usar o comando WHERE se aplicam ao comando UPDATE. Com o uso do WHERE, teríamos evitado a alteração de todos os registros.

Para corrigir, vamos alterar a observação de todas as compras que não estão em um determinado intervalo de datas:

"Atualize as compras, alterando a observação para 'uma compra comum', somente se a data não estiver entre os dias 05-01 e 25-06."

Traduzindo para sql:

     UPDATE COMPRAS SET OBSERVACOES = 'uma compra comum' WHERE DATA NOT BETWEEN '2010-01-05' AND '2010-06-25'
No comando acima, o UPDATE foi utilizado com o WHERE para restringir as compras que serão afetadas pela alteração que desejamos. Nessa condição apareceu um novo operador, NOT, em WHERE DATA NOT. Queremos alterar todos as compras que foram feitas em qualquer data exceto as de um determinado período; ou seja, queremos fazer uma negação. O BETWEEN estabelece esse período e o NOT nega essa condição. Podemos ler o trecho DATA NOT BETWEEN '2010-01-05' AND '2010-06-25' como: a data não está no período entre 05/01/2010 e 25/06/2010.

Agora sabemos que é possível usar WHERE em um comando UPDATE para filtrar os dados da mesma forma que em um SELECT. Podemos alterar as compras feitas no dias 25/12/2010 ou no dia 12/10/2010 ou ainda no dia 12/06/2010 para conter a observação "datas festivas". Uma forma seria usar vários OR:

WHERE DATA = '2010/12/25' OR DATA = '2010-10-12' OR DATA = '2010-06-12'

O exemplo está correto, porém o código ficou feio, pois repete-se o nome da coluna DATA muitas vezes. Existe uma condição que pode ser usada justamente para melhorar isso:

     UPDATE COMPRAS SET OBSERVACOES = 'datas festivas' WHERE DATA IN('2010-12-25', '2010-10-12', '2010-06-12')
O operador IN foi usado para criar um conjunto de valores que usamos na comparação. O trecho WHERE DATA IN ('2010-12-25', '2010-10-12', '2010-06-12') diz que queremos todos os registros com qualquer uma das datas que esteja entre os parênteses, e apenas essas datas (nenhuma compra feita em outra data serve).

Na medida em que usamos o banco de dados, muita coisa que está sendo guardada se torna obsoleta. É possível excluir qualquer informação que não seja mais de nosso interesse. Vamos:

"Excluir todas as compras efetuadas antes de 2009":

     DELETE FROM COMPRAS WHERE DATA < '2009-01-01'
O comando DELETE exclui registros de uma tabela. E por isso sempre deve ser executado usando o WHERE. Se nenhuma condição for usada junto com o comando DELETE então todos os registros da tabela serão excluídos. Cuidado. Usamos a condição WHERE DATA < '2009-01-01' para excluir todas as compras feitas antes de 01/01/2009.

Os comandos UPDATE e DELETE são essenciais para a manutenção dos registros de nosso banco de dados. Como eles alteram definitivamente os valores dos registros, é importantíssimo sempre usarmos uma condição através do comando WHERE para limitar o efeito desses comandos a apenas alguns registros. Uma dica é sempre escrever primeiro o SELECT com o WHERE ao usar um UPDATE ou DELETE. Por exemplo, antes de excluir as compras anteriores a 2009, poderíamos ter executado o seguinte:

     SELECT * FROM COMPRAS WHERE data < '2009-01-01'
Assim poderíamos analisar o resultado e ter certeza que só compras anteriores a 2009 estavam na lista de resultados. Que tal fazer alguns exercícios para se acostumar com o uso do UPDATE e do DELETE?

<h2>Operador lógico NOT</h2>
O operador lógico NOT pode ser usado para alterar todas as compras que não foram realizadas em um período específico. Esse operador pode ser usado para negar qualquer condição. Por exemplo, para selecionar qualquer registro com data diferente de 03/11/2011, pode ser construído o seguinte WHERE:

WHERE NOT DATA = '2011-11-03'
Use o operador NOT e monte um SELECT que retorna todas as compras com valor diferente de R$ 108,00.

----------------------------------------------------------------------------------------------
<h1>Seção 03 - Alterando e restringindo o formato das nossas tabelas</h1>

E se não quiséssemos colocar uma observação? Em vez de deixarmos em branco, podemos usar NULL, um tipo especial suportado pelos bancos de dados pra representar vazio.

Veja o seguinte comando SQL:

     INSERT INTO COMPRAS (VALOR, DATA, OBSERVACOES, RECEBIDO) VALUES (100.0, '2010-10-10', NULL, 1);
Repare o NULL. Como dito anteriormente, isso indica que não queremos passar uma observação. É muito comum ver o uso de NULL nos mais diversos tipos de banco de dados.

Mas às vezes queremos evitar que isso aconteça, proibindo o usuário de inserir um valor nulo na tabela. Podemos pedir ao banco de dados que rejeite nulos nas colunas. Para isso, precisamos alterar a tabela e dizer que a coluna OBSERVACOES não aceitará mais valores nulos:

     ALTER TABLE COMPRAS MODIFY COLUMN OBSERVACOES TEXT NOT NULL
Basicamente, o comando acima altera a tabela COMPRAS através do ALTER TABLE COMPRAS, e modifica a coluna observações dizendo que ela não deve ser nula através do MODIFY COLUMN OBSERVACOES TEXT NOT NULL.

Se tentarmos agora executar o INSERT acima, ele falhará, pois o banco recusará valor nulo para a coluna OBSERVACOES.

Podemos ainda definir mais coisas para nossas tabelas. Vamos supor que a regra de negócio do nosso sistema diz que todas as compras nunca são entregues de primeira. Ou seja, todas compras nascem não entregues. Podemos falar ao banco que o valor padrão (default) da coluna RECEBIDO é falso (ou seja, 0).

Para isso, vamos alterar novamente a tabela:

     ALTER TABLE COMPRAS MODIFY COLUMN RECEBIDO TINYINT(1) DEFAULT '0';
Novamente, fizemos um MODIFY COLUMN, mas dessa vez na coluna RECEBIDO. Veja que setamos o valor padrão para 0 através do DEFAULT '0'. Poderíamos também setar valores padrão para qualquer uma das colunas.

Caso a coluna tenha um valor padrão, você não precisa passá-lo no comando INSERT. Por exemplo, agora que RECEBIDO possui um valor default, é possível pulá-lo na instrução:

     INSERT INTO COMPRAS (VALOR, DATA, OBSERVACOES) VALUES (189.76, '2009-02-09', 'UMA COMPRA QUALQUER');
Como não passamos o valor do RECEBIDO, o MySQL entende que deve ser inserido o valor padrão, ou seja, 0.

Vamos agora criar uma nova coluna para guardar a forma de pagamento de cada compra. Nosso sistema deve suportar compras por cartão de crédito, boleto bancário e dinheiro. Podemos criar uma coluna do tipo VARCHAR ou do tipo INT para guardar essa forma de pagamento.

O problema é que se declararmos essa coluna como varchar, ela vai aceitar qualquer valor, inclusive formas de pagamento inexistentes. Podemos avisar ao MySQL que essa coluna só deve aceitar valores como 'CARTAO', 'BOLETO' e 'DINHEIRO', por exemplo. O MySQL chama isso de enum. Vamos criar essa coluna:

     ALTER TABLE COMPRAS ADD COLUMN FORMA_PAGT ENUM('CARTAO', 'BOLETO', 'DINHEIRO');
Veja a sintaxe: declaramos um ENUM e passamos a lista de itens válidos para ele. Isso significa que se fizermos um INSERT com algum valor diferente do que estiver listado, o banco irá recusar. Veja a criação de uma compra feita por cartão de crédito:

     INSERT INTO COMPRAS (VALOR, DATA, OBSERVACOES, FORMA_PAGT) VALUES (189.76, '2010-02-09', 'UMA OUTRA COMPRA QUALQUER', 'CARTAO');
Mas repare que demos nome errado a coluna. Vamos renomeá-la usando o comando ALTER TABLE:

     ALTER TABLE COMPRAS CHANGE FORMA_PAGT FORMA_PAGT ENUM('CARTAO', 'BOLETO', 'DINHEIRO');
Veja o comando CHANGE: ele recebe a coluna que quer ser alterada, e a versão nova dessa coluna.

O comando ALTER TABLE nos permite alterar a tabela já existente. Ele é bem extenso e você deveria pesquisar mais a respeito!


---------------------------------------------------------------------------------------------------
<h1>Seção 04 - Agrupando dados e fazendo consultas mais inteligentes</h1>

Vamos supor que precisamos calcular a soma de todas as compras realizadas até hoje. Precisaríamos fazer algo parecido com um 'loop', pegando todos os valores retornados, e somando um-a-um. A SQL nos permite fazer consultas que agrupam valores da forma que queremos.

Nesse caso, queremos somar todos os valores da coluna VALOR. Usando a função SUM, temos o resultado esperado:

     SELECT SUM(VALOR) FROM COMPRAS
Por ser um comando SELECT, podemos colocar condições, por exemplo, calcular a soma de todas as compras feitas antes de 01/01/2010:

     SELECT SUM(VALOR) FROM COMPRAS WHERE DATA < '2010-01-01'
Podemos calcular informações ainda mais interessantes, tal como a média das compras feitas antes de 01/01/2010. Basta usarmos a função AVG:

     SELECT AVG(VALOR) FROM COMPRAS WHERE DATA < '2010-01-01'
Podemos inclusive juntar as duas, dando nomes mais bonitos para as colunas geradas, usando o comando AS, visto nas aulas anteriores:

     SELECT AVG(VALOR) AS MEDIA, SUM(VALOR) AS SOMA FROM COMPRAS WHERE DATA < '2010-01-01'
Podemos contar o número de compras da mesma forma, usando a função COUNT:

     SELECT COUNT(VALOR) FROM COMPRAS WHERE DATA < '2010-01-01'
Percebemos até então que o comando SELECT unido às funções de soma, média e contagem pode gerar dados muito úteis. Mas e se quisermos a soma de todas as compras recebidas e não recebidas? Podemos fazer duas consultas, por exemplo:

     SELECT SUM(VALOR) FROM COMPRAS WHERE RECEBIDO = 1;
    SELECT SUM(VALOR) FROM COMPRAS WHERE RECEBIDO = 0;
E se quisermos todo esse resultado em apenas uma única consulta? Afinal, executar uma-a-uma manualmente pode gerar muito trabalho - imagine se quiséssemos a soma das compras por forma de pagamento, seriam 3 consultas diferentes; uma para BOLETO, uma para CARTAO, uma para DINHEIRO.

Para isso, precisamos informar ao MySQL que queremos agrupar os dados. No nosso caso, queremos agrupar compras de acordo com o valor da coluna RECEBIDO. Usamos então o comando GROUP BY, junto ao resto da SQL. Veja o exemplo:

     SELECT SUM(VALOR) FROM COMPRAS GROUP BY RECEBIDO
Veja que agora ele agrupou e exibiu dois resultados, muito provavelmente a soma de todas as compras na qual recebido vale 1, e as compras na qual recebido vale 0. O problema é que não sabemos qual valor corresponde a cada uma das condições de RECEBIDO. Para isso, podemos exibir a coluna RECEBIDO na consulta também:

     SELECT RECEBIDO, SUM(VALOR) FROM COMPRAS GROUP BY RECEBIDO
Agora sabemos a soma para cada grupo! Podemos juntar também os valores desse agrupamento para exibir a soma e a quantidade de itens em cada grupo, por exemplo:

     SELECT RECEBIDO, SUM(VALOR) AS SOMA, COUNT(VALOR) AS TOTAL FROM COMPRAS GROUP BY RECEBIDO
Podemos refinar ainda mais esse resultado, ordenando, por exemplo, pelo grupo na qual a soma seja maior (ou seja, de maneira decrescente). Para isso, usamos o comando ORDER BY, informando o campo que queremos ordenar no final da nossa consulta SQL:

     SELECT RECEBIDO, SUM(VALOR) AS SOMA, COUNT(VALOR) AS TOTAL FROM COMPRAS GROUP BY RECEBIDO ORDER BY SOMA DESC
Repare a palavra DESC. Ela indica que queremos ordenar de maneira decrescente. Para ordenar de maneira crescente, fazemos uso da palavra ASC:

     SELECT RECEBIDO, SUM(VALOR) AS SOMA, COUNT(VALOR) AS TOTAL FROM COMPRAS GROUP BY RECEBIDO ORDER BY SOMA ASC
Consultas agrupadas são um recurso muito interessante e permite-nos extrair muitas informações dos nossos dados!


-------------------------------------------------------------------------------------------------------
<h1>Seção 05 - Juntando dados de várias tabelas</h1>
Vamos agora guardar, além dos dados da compra, os dados da pessoa que fez a compra. Podemos criar campos como NOME, ENDERECO, TELEFONE e etc, na tabela COMPRAS. Mas essa abordagem nos trará problemas: e se a pessoa muda de endereço? Precisaremos atualizar esses dados em todas as linhas da tabela daquela pessoa? Essa operação pode ser cara.

Uma outra abordagem seria criar então uma tabela para cada pessoa. Por exemplo, tabela COMPRAS_DO_MAURICIO, COMPRAS_DO_ADRIANO, COMPRAS_DO_RICARDO, e etc. Mas a solução também não é a mais elegante: a cada novo pagador, precisaríamos criar uma nova tabela.

Vamos primeiro criar uma tabela para representar todas as pessoas que já fizeram uma compra:

     CREATE TABLE COMPRADORES (
      ID INT NOT NULL AUTO_INCREMENT,
      NOME VARCHAR(100) NOT NULL,
      ENDERECO VARCHAR(100) NOT NULL,
      TELEFONE VARCHAR(20) NOT NULL,
      PRIMARY KEY(ID)
    )
Repare na coluna ID que não discutimos até então. Ela serve para que nós possamos identificar uma linha específica na tabela. Veja que até a marcamos como AUTO_INCREMENT, justamente para não termos que nos preocupar com esse valor único, e pedimos para o banco dar um número para cada linha da tabela.

Faça o teste. Insira 2 compradores na tabela:

INSERT INTO COMPRADORES (NOME, ENDERECO, TELEFONE) 
VALUES ('MAURICIO', 'RUA VERGUEIRO, 123', '(11) 1111-1111');
INSERT INTO COMPRADORES (NOME, ENDERECO, TELEFONE) 
VALUES ('ADRIANO', 'AV. PAULISTA, 456', '(11) 2222-2222');
Agora faça o SELECT na tabela. Veja que cada linha tem um número diferente. Se quisermos achar um elemento específico, podemos sempre buscar pelo ID dele. Essa coluna ID é chamada de primary key, chave primária, ou mesmo PK. É como se fosse o nosso RG: ele identifica um brasileiro específico em relação aos milhões de brasileiros existentes.

Veja que especificamos isso inclusive na hora da criação da tabela, através do PRIMARY KEY(ID). Isso diz ao banco que o ID é a coluna que identifica uma linha e que não deve haver dois elementos com o mesmo ID.

O problema agora é referenciar uma compra para um comprador. Sabemos que para identificar um comprador em específico podemos usar seu ID. Se uma compra pertence a um comprador, basta que a tabela COMPRA tenha uma coluna que guarda o ID do comprador. Vamos criar essa coluna:

ALTER TABLE COMPRAS ADD COLUMN COMPRADOR_ID INT NOT NULL
Veja que existe uma relação entre a tabela de compradores e a tabela de compras: cada compra tem um comprador, mas um comprador tem muitas compras. Chamamos essa relação de um-pra-muitos, ou one-to-many.

Apenas para testes, vamos colocar uma parte das compras para o comprador com id 1, e outra para o comprador de id 2:

UPDATE COMPRAS SET COMPRADOR_ID = 1 WHERE ID < 8;
UPDATE COMPRAS SET COMPRADOR_ID = 2 WHERE ID >= 8;
(Apenas por curiosidade, veja que todas as compras com ID menor que 8 agora apontam para o comprador com ID 1, e todas as compras com ID maior-ou-igual a 8 apontam para o comprador com ID 2).

Vamos agora selecionar todas as compras e seus compradores. Podemos dizer na instrução SQL que queremos exibir mais de uma tabela. Basta separar as tabelas com vírgula:

SELECT * FROM COMPRAS, COMPRADORES
Veja que ele nos trouxe um resultado gigante. Como são duas tabelas diferentes, e ele não sabe juntá-las, o MySQL nos devolveu todas as combinações possíveis entre os elementos das duas tabelas. Ou seja, se nossa tabela de COMPRAS tivesse apenas uma única compra de 100,00, e a tabela de COMPRADORES tivesse 2 pessoas, o Mauricio e o Adriano, então esse SELECT nos devolveria uma compra de 100,00 para o Mauricio e uma compra de 100,00 para o Adriano.

Precisamos dizer ao MySQL que queremos juntar esses dados, e que a coluna que faz essa junção é justamente a coluna COMPRADOR_ID da tabela COMPRAS com a coluna ID da tabela COMPRADORES. Basta dizermos que essa é a coluna que faz a junção ou, em inglês, o JOIN:

SELECT * FROM COMPRAS JOIN COMPRADORES ON COMPRAS.COMPRADOR_ID = COMPRADORES.ID
Agora sim, o MySQL tem informações suficientes para juntar os dados das duas tabelas! Ele sabe que o COMPRADOR_ID aponta para um ID de COMPRADOR. Portanto, ele nos trouxe todas as compras junto com os dados do comprador!

Veja que a coluna COMPRADOR_ID é como se fosse um link para um comprador. Colunas que tem justamente essa finalidade são chamadas de foreign key, chave estrangeira, ou simplesmente FK. Podemos inclusive deixar isso claro no MySQL, para que ele não permita que seja inserida uma FK inválida (ou seja, uma FK que não aponta para um comprador válido).

Vamos fazer essa alteração na tabela, e dizer que a coluna COMPRADOR_ID é uma FK:

       ALTER TABLE COMPRAS ADD FOREIGN KEY (COMPRADOR_ID) REFERENCES COMPRADORES(ID)
Veja que indicamos nessa instrução SQL o nome da coluna que é uma FK (no caso, COMPRADOR_ID) e dissemos que ela aponta para o campo ID da tabela COMPRADORES.

Os JOINs nos permitem juntar tabelas que se relacionam através das FKs. É um recurso poderoso que você deve praticar!

<h2>Bancos não relacionais</h2>

Para armazenar os dados dos compradores foi usada uma única tabela chamada COMPRADORES. Dessa forma foi simples listar todas as compras juntamente com as informações de seus compradores utilizando o JOIN. Foram usadas as colunas COMPRAS.COMPRADOR_ID e COMPRADORES.ID para relacionar as duas tabelas.

Uma outra sugestão seria criar uma tabela para cada comprador. Nessa situação, para ter o mesmo resultado atual, seria preciso consultar a tabela específica de cada comprador para cada uma das compras listadas. No nosso exemplo, essa não pareceu a melhor ideia.

Será que existe uma outra situação em que é mais indicado usar algo parecido com uma tabela por comprador? Pesquise sobre outras formas de armazenamento de dados e comente o que encontrou.

Não existe uma regra única. Bancos relacionais como o MySQL e os que suportam SQL apresentam determinadas características. Alguns projetos famosos, como o Twitter, não utilizam somente um banco relacional, mas também outros bancos com outras formas de armazenamento que permitem acelerar a atualização e busca dos dados.

<h2>Foreign Key</h2>

A tabela COMPRAS foi alterada para conter uma FOREIGN KEY referenciando a coluna ID da tabela COMPRADORES. O objetivo é deixar claro para o banco de dados que COMPRAS.COMPRADOR_ID está de alguma forma relacionado com a tabela COMPRADORES através da coluna COMPRADORES.ID. Mesmo sem criar a FOREIGN KEY é possível relacionar tabelas através do comando JOIN.

Qual a vantagem em utilizar a FOREIGN KEY?
A existência da FOREIGN KEY impede que seja criado um registro na tabela COMPRAS associado a um comprador inválido. Só é possível cadastrar uma compra se o valor associado à coluna COMPRADOR_ID for o mesmo que um ID cadastrado na tabela COMPRADORES.

