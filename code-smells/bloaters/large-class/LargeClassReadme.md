# Large Class
## Description (What)
A class is violating the Single Responsibility principle and has grown to a large size. Ifa class contains too many lines of code, methods, fields and feel bloated, they can be considered a large class.

## How to Locate It (Where)

Look for classes which have methods and fields relating to two or more different responsibilities

## How It Manifests (Why)

If a programmer keeps adding functionality without making a new class because they feel that the new functionality doesn't warrant an entirely new class or if a programmer doesn't realize the scope of a class and adds too many functions and/or fields

## How to Fix It (Possible Treatments)

In Example 1, we identify the parts (fields and methods) of the class that we can move out to a new class and then apply `Extract Class` in which we create a new class that is better suited to hosting the extra functionality of our current class

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/large-class#:~:text=Treatment)

## Examples

### Example 1

#### Before: LargeClassSmelly.java

Calculator class has grown in size and is now handling too much functionality to be maintainable.

```
Observed Code Smells:
- Large Class (line 1-35)
```

---

#### After: LargeClassRefactored.java

Applied Extract Class to spin off some methods into their own class TrigonometricCalculator to reduce the size and responsibility of the Calculator class

```
Refactoring Applied:
- Large Class
    - Extract Class (Calculator)
```

```
Observed Code Smells After Refactoring:
- None
```

---
---

### Example 2

#### Before: LargeClassSmelly.java

---

#### After: LargeClassRefactored.java

## When to Ignore

No case

### More
[More about Large Class](https://refactoring.guru/smells/large-class)
