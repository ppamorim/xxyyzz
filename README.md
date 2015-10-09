# xxyyzz

# Configurações

  É necessário instalar as ultimas versões das SDKs do Android (23.0.1) e abrir o projeto no Android Studio de ultima versão

# Estrutura

Neste projeto foi utilizado o padrão MVP (Model-View-Presenter) e injeção de dependências utilizando o pre processador Dagger 2.
Mantive a minima quantidade possível de bibliotecas no projeto, no entanto, utilizei as melhores e mais atualizadas.

# Bibliotecas

 - Appcompat: Provem de biblotecas padrões de suporte(Toolbar, ViewCompat...)
 - Butterknife: Inject de view menos verbosa
 - Square OkHttp: Requests Http
 - Fresco: ImageLoader, provido pelo Facebook
 - ppamorim/Recyclerrenderers: Pequeno wrapper para a classe RecyclerView, usado em lista
 - Parceler: Parceladores  de dados, necessário para transferir dados entre atividades
 - Leakcanary: Analise de leak de memória
 - Mockito: Testes em TDD

# Misc

O projeto utiliza o estilo de código da Square, sendo 2 espaços para as indentações.
É o mais recomendado para Android pois facilita a leitura e organização do código (mais compacto)
Proguard está ativado para obfuscar e reduzir o tamanho de métodos com o objetivo de evitar o estouro de DEX.
