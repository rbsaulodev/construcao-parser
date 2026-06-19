# Construção Parser 

## Sobre o Desafio

A professora Senhora Lasanha quer que o Sandubinha extraia informações gramaticais da letra da música [**Construção** de Chico Buarque](https://www.letras.mus.br/chico-buarque/45124/) usando **Regex**, com boas práticas, **TDD** e **sem frameworks de produção**.

---

## Funcionalidades Implementadas

### Requisitos Obrigatórios 
| # | Requisito | Implementação |
|---|-----------|---------------|
| 1 | Palavras com `ç`, `ã` ou `õ` | `TextoParserService#getPalavrasComCedilhaOuTilde` |
| 2 | Frases repetidas + contagem | `TextoParserService#getFrasesRepetidas` |
| 3a | Palavras com Ditongo | `TextoParserService#getPalavrasComDitongo` |
| 3b | Palavras com Tritongo | `TextoParserService#getPalavrasComTritongo` |
| 3c | Palavras com Hiato | `TextoParserService#getPalavrasComHiato` |
| 4 | Frases de exatamente 4 palavras | `TextoParserService#getFrasesComQuatroPalavras` |

### Extras (+200 pts)

| # | Requisito | Implementação |
|---|-----------|---------------|
| E1 | Proparoxítonas (+100) | `TextoParserService#getPalavrasProparoxitonas` |
| E2 | Texto sem plural em s/es (+100) | `TextoParserService#getTextoSemPlural` |

---

## Arquitetura e Padrões de Projeto

```
src/main/java/br/com/aczg/construcao/
├── Main.java                          ← Ponto de entrada
├── domain/
│   ├── TextoMusical.java              ← Value Object (imutável)
│   └── ResultadoAnalise.java          ← Value Object + Builder Pattern
├── repository/
│   ├── LetraRepository.java           ← Interface (Strategy Pattern)
│   └── ConstrucaoLetraRepository.java ← Implementação concreta
├── service/
│   ├── TextoParserService.java        ← Interface (ISP/SOLID)
│   ├── TextoParserServiceImpl.java    ← Implementação com Template Method
│   └── AnalisadorMusicalService.java  ← Facade Pattern (orquestra tudo)
└── util/
    ├── RegexPatterns.java             ← Constantes (DRY, pré-compiladas)
    └── TextoNormalizer.java           ← SRP: normalização de texto
```

### Design Patterns utilizados

- **Strategy** — `LetraRepository` permite trocar a fonte do texto sem alterar nada
- **Builder** — `ResultadoAnalise.Builder` para construção fluente e imutável
- **Facade** — `AnalisadorMusicalService` orquestra repositório + parser em um único ponto
- **Template Method** — `extrairPalavrasUnicasOrdenadas` usado internamente pelos extratores

### Princípios SOLID aplicados

- **S** — Cada classe tem uma única responsabilidade (`TextoNormalizer` só normaliza, `RegexPatterns` só armazena padrões, etc.)
- **O** — `TextoParserService` é aberta para extensão sem modificação
- **L** — Implementações substituem as interfaces sem quebrar comportamento
- **I** — `TextoParserService` com métodos segregados por responsabilidade
- **D** — `AnalisadorMusicalService` depende das interfaces, não das implementações concretas

### Clean Code

- Nomes expressivos e sem abreviações (`extrairPalavrasUnicasOrdenadas` vs `extPal`)
- Métodos pequenos e focados (nenhum ultrapassa 20 linhas de lógica)
- Objetos de valor imutáveis (`TextoMusical`, `ResultadoAnalise`)
- Constantes de Regex pré-compiladas (performance e legibilidade)
- Zero frameworks de produção (Java puro)

---

## Regex explicados

### Palavras com ç, ã ou õ
```
\b\w*[çÇãÃõÕ]\w*\b
```
Captura qualquer palavra que contenha ao menos um desses caracteres.

### Ditongo decrescente (vogal + semivogal)
```
[aeiouáéíóú...][iu](?![aeiou...])
```
Vogal tônica seguida de `i` ou `u` que não é seguida de outra vogal (senão seria tritongo).
Exemplos: `feijão` (ei), `gargalhou` (ou), `caiu` (ai), `trouxe` (ou)

### Ditongo crescente (semivogal + vogal)
```
[iu][aeiouáéíóú...]
```
Semivogal `i` ou `u` seguida de vogal tônica. Exemplos: `quando` (ua), `quieto` (ie)

### Tritongo
```
[iu][aeiouáéíóú...][iu]
```
Semivogal + vogal tônica + semivogal. Exemplos: `Uruguai` (uai), `iguais` (uai)

### Hiato
```
[aeoáéóâêô][aeiouáéíóú...]  |  [aeiou...][áéíóúâêîôû...]
```
Duas vogais em sílabas diferentes. A segunda é vogal acentuada (sempre tônica, portanto nova sílaba) ou é vogal oral após `a/e/o`. Exemplos: `saúde` (a+ú), `poesia` (o+e)

### Proparoxítona
```
[a-z...]*[áéíóúâêîôû][a-z...]{2,}[a-z]\b
```
Heurística: vogal acentuada (tônica gráfica) seguida de pelo menos 2 sílabas.
Funciona para proparoxítonas porque em português elas **sempre** têm acento gráfico.
Exemplos: `lógico`, `pêndulo`, `máquina`, `último`, `trágico`, `sólido`

### Plural em es / s
```
\b(\w+)es\b          → remove "es" final
\b(\w+[vogal])s\b    → remove "s" final após vogal
```

---



## Sobre a reutilização

O sistema foi projetado para funcionar com **qualquer texto**, não apenas a música "Construção". Para usar com outro texto:

```java
// Implemente sua própria fonte
LetraRepository minhaFonte = () -> new TextoMusical("Título", "Autor", "Seu texto aqui...");

// Reutilize o parser sem alteração
TextoParserService parser = new TextoParserServiceImpl();
AnalisadorMusicalService analisador = new AnalisadorMusicalService(minhaFonte, parser);

ResultadoAnalise resultado = analisador.analisar();
```

---

