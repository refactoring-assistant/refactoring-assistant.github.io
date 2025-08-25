# Speculative Generality

## Description (What)

The existence of a class, field or method that was intended for future use that is currently unused contributes to Speculative Generality

## How to Locate It (Where)

Look for code that is currently not used (like dead code) or has a very bare implementation created specifically to support a future work that is anticipated

## How It Manifests (Why)

Speculative Generality appears when a lot of code is written that is intended to support future work such that it starts hampering current development and becomes harder to maintain. In general coding, it is better to have extendability in your program through the use of interfaces, but when classes, fields or methods are created as placeholders, it can make code unreadable.

## How to Fix It (Possible Treatments)

In Example 1, `Collapse Hierarchy` is used to remove an unused abstract class. `Collapse Hierarchy` can be used to simplify hierarchies that were made for far flung future work but work towards increasing technical debt more than the extendability of the program.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/speculative-generality#:~:text=Treatment)

## Examples

### Example 1


#### Problem SGBE1.java
An abstract class `Shape` was created with the hope of future use, but it was never used anywhere.

```
Observed Code Smells:
- Speculative Generality (lines 1-5)
```

---

#### Solution SGGE1.java
Deleted the unused abstract class by `Collapse Hierarchy`.

```
Refactoring Applied:
- Speculative Generality
    - Collapse Hierarchy (Deleted the unused abstract class Shape)
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

Writing code that is designed to behave like a framework, as long as the code is directly used by the framework's users

Code that is written specially for testing purposes

## More

[More about Speculative Generality](https://refactoring.guru/smells/speculative-generality)
