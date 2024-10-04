# Services no Domain-Driven Design.

## Livros Essenciais

1. "Domain-Driven Design: Tackling Complexity in the Heart of Software" por Eric Evans
    - Capítulo 7: "Using the Language: An Extended Example"
    - Seção sobre Services, páginas 104-107
    - Este é o livro original que introduziu DDD e discute os diferentes tipos de services: Domain Services, Application Services e Infrastructure Services

2. "Implementing Domain-Driven Design" por Vaughn Vernon
    - Capítulo 7: "Services"
    - Fornece exemplos práticos e aprofundados sobre quando e como usar services em DDD

3. "Domain-Driven Design Distilled" por Vaughn Vernon
    - Um livro mais conciso que oferece uma visão geral prática de DDD, incluindo o uso de services

## Artigos Online

1. Martin Fowler's Blog: ["AnemicDomainModel"](https://martinfowler.com/bliki/AnemicDomainModel.html)
    - Discute o anti-padrão do modelo anêmico e como services podem ser usados adequadamente

2. MSDN: ["Domain Services in Domain-Driven Design"](https://learn.microsoft.com/en-us/archive/msdn-magazine/2009/february/best-practice-an-introduction-to-domain-driven-design)
    - Um artigo detalhado da Microsoft sobre o uso de services em DDD

## Recursos Práticos

1. GitHub: ["DDD Sample Application"](https://github.com/citerus/dddsample-core)
    - Uma aplicação de exemplo que demonstra vários conceitos de DDD, incluindo o uso apropriado de services

2. Série de artigos do Vladimir Khorikov: ["Services in Domain-Driven Design"](https://enterprisecraftsmanship.com/posts/services-in-ddd/)
    - Uma série excelente que aborda em detalhes os diferentes tipos de services em DDD

## Principais Conceitos a Serem Estudados

1. **Tipos de Services**
    - Domain Services: Encapsulam lógica de negócios que não pertence naturalmente a entidades ou value objects
    - Application Services: Orquestram a execução de casos de uso
    - Infrastructure Services: Fornecem capacidades técnicas

2. **Quando Usar Services**
    - Quando a operação é um conceito importante do domínio
    - Quando a operação envolve múltiplos objetos de domínio
    - Quando a lógica não se encaixa naturalmente em entidades ou value objects

3. **Anti-Padrões a Evitar**
    - Services anêmicos que apenas delegam para repositórios
    - Services que contêm estado
    - Services que violam os limites do contexto limitado (bounded context)

## Citações Relevantes

> "When a significant process or transformation in the domain is not a natural responsibility of an ENTITY or VALUE OBJECT, add an operation to the model as a standalone interface declared as a SERVICE." - Eric Evans

> "A good Service has three characteristics:
> 1. The operation relates to a domain concept that is not a natural part of an Entity or Value Object
> 2. The interface is defined in terms of other elements of the domain model
> 3. The operation is stateless"
> - Eric Evans

## Exemplos de Code Smells que Indicam Necessidade de Refatoração

1. Services muito grandes com muitas responsabilidades
2. Services que contêm estado
3. Services que violam o princípio de responsabilidade única
4. Services que duplicam lógica presente em entidades

## Boas Práticas

1. Mantenha services focados e coesos
2. Use nomes que reflitam conceitos do domínio
3. Evite services genéricos como "UtilityService"
4. Prefira métodos que retornam algo em vez de void (quando faz sentido)