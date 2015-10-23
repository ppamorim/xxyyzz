![Logo 1][1]

[![Build Status](https://api.travis-ci.org/ppamorim/xxyyzz.svg?branch=master)](https://travis-ci.org/ppamorim/xxyyzz)

# Configurações

  É necessário instalar as ultimas versões das SDKs do Android (23.0.1) e o app suporta no mínimo a versão 10 da SDK do Android. É recomendável abrir o projeto no Android Studio de ultima versão.
  Não se esqueça de ir em Build Variants e configurar o Test Artifact para Unit Tests

# Estrutura

Neste projeto foi utilizado o padrão MVP (Model-View-Presenter) e injeção de dependências utilizando o pré processador Dagger 2.
Mantive a mínima quantidade possível de bibliotecas no projeto, no entanto, utilizei as melhores e mais atualizadas.

# Bibliotecas

 - Appcompat: Provém de bibliotecas padrões de suporte(Toolbar, ViewCompat...)
 - suport.Design: Fornece views Material Design 
 - Butterknife: Inject de view menos verbosa
 - Square OkHttp: Requests Http
 - SmartTabLayout: Fornece uma barra superior e facilita a criação de ViewPager
 - Discrete-SeekBar: Barra de avaliação Material Design
 - Leakcanary: Analise de leak de memória
 - jUnit: Testes unitários
 - Mockito: Gerador de mock

# Misc

O projeto utiliza o estilo de código da Square, sendo 2 espaços para as indentações.

No momento é o mais recomendado para Android pois facilita a leitura e organização do código (mais compacto).

Proguard está ativado para ofuscar e reduzir a quantidade de métodos com o objetivo de evitar o estouro de DEX.

Travis CI é utilizado para analisar o código e verificar se há alguma divergência do estilo de código.

[1]: ./art/logo.png
[0]: travis_invocation
