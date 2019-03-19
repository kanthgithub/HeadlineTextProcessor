# HeadlineTextProcessor

## Problem-Statement:

 Given the file headlines.zip which contains a CSV file of the headlines and the date it appeared for:

### Sample Input:

These are valid examples:

```
taiwan hit hard by sars outbreak
united states to play davis cup in bratislava
russia ponders space tourism deal
zabaleta advances in austria
```

### Task:

- Find headlines with mentions of countries and cities.

- These should be capitalised

    1. First word of the headline
    2. Countries
    3. Cities

### Expected Output:

Examples of capitalised headlines

```
Taiwan hit hard by sars outbreak.
United States to play davis cup in Bratislava.
Russia ponders space tourism deal.
Zabaleta advances in Austria
```

- Return a file of the results in CSV format, and the program used for generating it.

### Approach:

- Generate a dictionary of Countries and Cities from comprehensive
  list of Countries and Cities data is downloaded from web as json

- Implementation of text transformation uses Chain of Responsibility pattern

- Stages:

  - Capitalize first word
  - Search for mentions of Countries and capitalize the mentions
  - Search for mentions of Cities and capitalize the mentions

### Maintainability:

- New stages can be added to chain or existing nodes can be reordered

### Open Close Principle:

- Algorithm to search for mentions and transformation can be enhanced in sub-classes

### Algorithm:

 - String-Search Algorithm

    - QGramShiftOr -- A fast algorithm for multi-pattern searching

### References:

#### High-performance search Library:
- https://johannburkard.de/software/stringsearch/

#### StringSearchAlgorithm
- http://stringsearchalgorithms.amygdalum.net/

#### A Java library for byte pattern matching and searching
- https://github.com/nishihatapalmer/byteseek