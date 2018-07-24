# Atividade sobre performance da cadeira de Sistemas Distribuídos

#### Questão 1
Construa duas aplicações, uma cliente, que sabe enviar dois números, e outra servidora, que sabe somar e diminuir. O cliente envia dois números inicialmente aleatórios entre 0 e 9 e uma operação (9 bytes). Ao receber uma requisição, o servidor processa e devolve o resultado. De posse desse resultado o cliente envia novamente uma requisição usando o resultado e outro número aleatório entre 0 e 9 e continua fazendo isto continuamente. (use aleatóriedade para obter o tipo de operação também)

#### Questão 2
Reimplemente o cliente para que este envie cerca de 10 req/s usando apenas uma única thread.

#### Questão 3
Para o mesmo cenário da questão 01, utilizando um repositório com 10 threads do lado do cliente e de 5 threads do lado do servidor (limitado a uma única conexão por vez), descreva o comportamento do sistema e proponha uma solução de melhoria de performance.
