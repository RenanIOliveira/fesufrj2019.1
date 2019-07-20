# fesufrj2019.1
Project for Fundaments of Software Engineering - 2019.1


## Configurando JAVAFX no Eclipse

1. Baixe a versão uma versão do eclipse (com exceção da 2019)<br>
  1.1 Para instalar uma versão antiga basta clicar em advanced mode no instalador<br>
  1.2 Selecione o Java 8 e versão mais antiga do eclipse (neon por exemplo)<br><br>
2. Inicie o eclipse.<br><br>
3. Va no menu ```Help >> Install new software...```<br>
  3.1 em "work with" selecione: "[sua versao do eclipse] - http://download.eclipse.org/releases/ [sua versao do eclipse]"<br>
  3.2 filtre o texto digitando e(fx)clipse e instale o e(fx)clipse IDE<br><br>
4. Se o projeto ainda não estiver identificando o javaFX, altere jre utilizada.


**Obs: Ao abrir o projeto no eclipse selecione abrir projeto e abra a pasta "VoceAluga" e não a fesufrj2019.1.**  

## Adicionando o driver de conexao com o mysql

1. Entre em https://dev.mysql.com/downloads/connector/j/<br>
  1.1 selecione "platform idependent" e baixe o arquivo.<br>
  1.2 Extraia a pasta no seu computador.<br><br>
2. Abra o projeto no eclipse e vá em ```window >> preferences >> java >> buildpath >> userlibraries ``` crie uma nova biblioteca, chame de SQLConnector e adicione o .jar presente na pasta que voce extraiu.<br><br>
3. Clique com o botão direito no projeto e em ```build path >> Add library``` e adicione a biblioteca SQLConnector que voce acabou de criar.

## Baixar a versão para Java 8 do Scene Builder

1. Entre em https://gluonhq.com/products/scene-builder/
  1.1 Baixe a versão do Scene Builder para Java 8
  1.2 Caso baixe o JAR executável, clique com o botão direito do mouse e clique em "Abrir com [sua versão de JDK]
  1.3 Caso baixe o arquivo Dev, basta apenas instalar
