# Speculative Generality

## Description (What)

The existence of a class, field or method that was intended for future use that is currently unused contributes to Speculative Generality

## How to Locate It (Where)

Look for code that is currently not used (like dead code) or has a very bare implementation created specifically to support a future work that is anticipated

## How It Manifests (Why)

Speculative Generality appears when a lot of code is written that is intended to support future work such that it starts hampering current development and becomes harder to maintain. In general coding, it is better to have extendability in your program through the use of interfaces, but when classes, fields or methods are created as placeholders, it can make code unreadable.

## How to Fix It (Possible Treatments)

In Example 1, `Collapse Hierarchy` is used to remove an unused abstract class. `Collapse Hierarchy` can be used to simplify hierarchies that were made for far flung future work but work towards increasing technical debt more than the extendability of the program.

In Example 2, `Remove Method` is used to remove the strategic planning code. Developers could be considerate in a good way when they design the architecture,

but when it comes to the usability of the code, the strategic part does not function well. 

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/speculative-generality#:~:text=Treatment)

## Examples

### Example 1

#### Before:

An abstract class `Shape` was created with the hope of future use, but it was never used anywhere.

```
Observed Code Smells:
- Speculative Generality (lines 1-5)
```

---

#### After:

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

### Example 2

#### Before:

The structure of different memberships are there for strategic design, but the memberships are not

currently put into use.

```
Observed Code Smells:
- Speculative Generality (line 10-59)
```

---

#### After:

Simply delete the code to get the codebase organized.

```
Refactoring Applied:
- Remove Method:
    -  (The classes and methods no longer exist.)
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
