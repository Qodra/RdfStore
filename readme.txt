
Atualização (30/01/17) - versão acoplada (busca e armazenamento) não necessita ActiveMQ

Requisitos
- Alegro instalado
- importar a base de teste no allegro

Adequações no código
- na classe Allegro.java altere usuario senha conforme sua instalação
- na classe Main.java altere caminho para o arquivo resultado.csv
- escolha o alfa e beta com que desejar rodar o experimento
- para rodar com ou sem poda basta alterar o valor a variavel de mesmo nome (true ou false) 

- Antes de iniciar cada experimento execute no allegrograph manual :( sorry
     delete where {?s <dcterms:category> ?o}
     delete where {?s <dcterms:time> ?o}


     

----------------------------------------------------------------------
/////////////////////
antigo readme
Módulo RDF Store
Autor: jayme
Data: 10/03/16

-Módulo criado para receber uma mensagem via Apache ActiveMQ e realizar as operações de persistência no banco de dados.
-Consome mensagens das seguintes filas do apache activeMQ:
     qodra.crawler.rdfstore
     qodra.ASR.rdfStore
     qodra.AnnotaionToll.rdfstore
     qodra.busca.rdfstore


-Configurado para persistir informações no AllegroGraph.

-Requer servidor Apache ActiveMQ instalado e em execução:

Inicialização:

Pre-requisitos:
-Servidor Apache ActiveMQ em funcionamento

executar com o comando:
 # java -jar rdfStore.jar
