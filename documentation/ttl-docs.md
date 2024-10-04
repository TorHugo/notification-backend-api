## Time To Live (TTL)

## O que é TTL?
TTL é um valor que especifica quanto tempo um dado deve permanecer armazenado antes de ser automaticamente removido ou invalidado. No contexto de idempotência, o TTL determina por quanto tempo uma chave de idempotência e seu resultado associado devem ser mantidos.

## Por que usar TTL?
1. **Gestão de Recursos**
    - Evita acúmulo infinito de dados
    - Libera espaço de armazenamento automaticamente
    - Reduz a necessidade de limpeza manual

2. **Segurança**
    - Limita a janela de tempo para reutilização de chaves
    - Reduz riscos de replay attacks

3. **Consistência**
    - Define um período claro para validade da operação
    - Alinha com expectativas de negócio sobre retry

## Considerações ao Definir TTL
1. **Tempo de Processamento**
    - O TTL deve ser maior que o tempo máximo esperado para processamento
    - Exemplo: Se uma operação leva até 5 minutos, um TTL de 15-30 minutos pode ser apropriado

2. **Padrões de Retry do Cliente**
    - Considere com que frequência e por quanto tempo os clientes podem tentar novamente
    - Exemplo: Se clientes retry por até 24 horas, o TTL deve ser de pelo menos 24 horas

3. **Requisitos de Negócio**
    - Algumas operações podem ter validade natural
    - Exemplo: Um pagamento pode ter 24 horas para ser concluído

4. **Capacidade de Armazenamento**
    - Balance o TTL com a capacidade de armazenamento disponível
    - Monitore o uso de armazenamento

## Exemplos de TTL por Tipo de Operação

| Operação          | TTL Sugerido  | Justificativa                                      |
|-------------------|---------------|----------------------------------------------------|
| Pagamento         | 24-48 horas   | Tempo para resolução de problemas de processamento |
| Upload de Arquivo | 1-2 horas     | Tempo para retry em caso de falha de rede          |
| Criação de Pedido | 12 horas      | Alinhado com políticas de cancelamento             |
