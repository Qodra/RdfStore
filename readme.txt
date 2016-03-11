
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
-Servidor Apche ActiveMQ em funcionamento

executar com o comando:
 # java -jar rdfStore.jar



