# HeadlineTestProcessor

## Problem-Statement:

Given the file headlines.zip which contains a CSV file of the headlines and the date it appeared,

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

Return a file of the results in CSV format, and the program used for generating it. You may use any programming language(except Brainfuck).


### Approach:

- Generate a dictionary of Countries and Cities
