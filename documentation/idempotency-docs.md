# Idempotência em APIs RESTful com Domain-Driven Design

## Livros e Recursos Essenciais

1. "REST in Practice" por Jim Webber, Savas Parastatidis e Ian Robinson
    - Capítulo 5: "Web Service Implementation Styles"
    - Seção sobre idempotência e segurança em operações REST

2. "Building Microservices" por Sam Newman
    - Capítulo 4: "Integration"
    - Discute padrões de integração, incluindo idempotência em APIs

3. "Designing Data-Intensive Applications" por Martin Kleppmann
    - Capítulo 9: "Consistency and Consensus"
    - Aborda aspectos de consistência e idempotência em sistemas distribuídos

## Artigos Online

1. [Idempotency Patterns](https://blog.jonathanoliver.com/idempotency-patterns/) por Jonathan Oliver
    - Discute diferentes padrões para implementar idempotência

2. [Implementing Idempotency in Stripe's APIs](https://stripe.com/blog/idempotency)
    - Um case study real de implementação de idempotência

## Conceitos Fundamentais

1. **O que é Idempotência?**
    - Uma operação é idempotente se o resultado de executá-la múltiplas vezes é o mesmo que executá-la uma única vez
    - Crucial para garantir consistência em caso de falhas de rede ou retentativas do cliente

2. **Por que é Importante?**
    - Previne duplicação de operações
    - Permite retentativas seguras
    - Melhora a confiabilidade do sistema

3. **Tipos de Idempotência**
    - Idempotência natural (GET, PUT, DELETE)
    - Idempotência implementada (POST com chave de idempotência)

## Considerações de Design

1. **Escolha do Armazenamento**
    - Redis: Rápido, suporta TTL, bom para dados temporários
    - Banco relacional: Útil se precisar de consistência ACID
    - Considere:
        - Tempo de retenção das chaves
        - Volume de requisições
        - Requisitos de consistência

2. **Formato da Chave de Idempotência**
    - UUID v4 é comum e recomendado
    - Deve ser único por operação
    - Exemplo: `idempotency-key: 123e4567-e89b-12d3-a456-426614174000`

3. **TTL (Time-To-Live)**
    - Defina um período apropriado
    - Considere o ciclo de vida da operação
    - Exemplo: 24 horas para pagamentos

## Citações Relevantes

> "In a distributed system, retrying an operation is a common way to handle transient failures. Making operations idempotent simplifies the client's logic, since it can simply retry without worrying about duplicate processing." - Martin Kleppmann

## 