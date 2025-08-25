# Duplicate Code

## Description (What)

Code that is duplicated in a class. Duplicated code may be harder to find if for example two methods do the exact same thing but have different names.

## How to Locate It (Where)

Modern IDEs usually do a good job of finding duplicate code if configured correctly.
Otherwise simply look for code snippets in your class that look eerily similar

## How it Manifests (Why)

Two different developers might make two different functions that perform the same task unknowingly. This can also be caused by copy-pasting code snippets.

## How to Fix It (Possible Treatments)

In Example 1, two methods perform identical functions but have different names in which case `Extract Method` is used to combine both the functions into a single function.

There are mutliple possible situations in which duplicate code might arise, like when two subclasses have duplicate methods or if duplicate code is found in two different classes.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/duplicate-code#:~:text=Treatment)

## Examples

### Example 1


#### Problem DUPCBE1.java
Functions Add() and Sum() are functionally identical.

```
Observed Code Smells:
- Duplicate Code (lines 10-12, 26-29 )
```

#### Solution DUPCGE1.java
Applied `Extract Method` as it’s a duplication in the same class.

```
Refactoring Applied:
- Duplicate Code
    - Extract Method (Sum)
```

```
Observed Code Smells After Refactoring:
- None
```

## More

[More about Duplicate Code](https://refactoring.guru/smells/duplicate-code)
