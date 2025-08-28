# Large Class
## Description (What)
A class is violating the Single Responsibility principle and has grown to a large size. Ifa class contains too many lines of code, methods, fields and feel bloated, they can be considered a large class.

## How to Locate It (Where)

Look for classes which have methods and fields relating to two or more different responsibilities

## How It Manifests (Why)

If a programmer keeps adding functionality without making a new class because they feel that the new functionality doesn't warrant an entirely new class or if a programmer does not realize the scope of a class and adds too many functions and/or fields

## How to Fix It (Possible Treatments)

In Example 1, we identify the parts (fields and methods) of the class that we can move out to a new class and then apply `Extract Class` in which we create a new class that is better suited to hosting the extra functionality of our current class

In Example 2, there are essentially different types of Employees that are included in 1 class, need to apply `Extract Class` to

categorize different types of employees into different classes.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/large-class#:~:text=Treatment)

## Examples

### Example 1

#### Before: 

Calculator class has grown in size and is now handling too much functionality to be maintainable.

```
Observed Code Smells:
- Large Class (line 1-35)
```

#### After: 

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

### Example 2

#### Before:

The class `EmployeeVariation` contains constructors and features for 3 types of employees:

Ordinary, Security and Manager.

The constructors are overloaded to instantiate these 3 types of employees.

But as the number of features increases, the class expands to create a code smell.

#### After: 

Applied `Extract Class` to categorize the constructors and features respectively into their own classes.

```
Refactoring Applied:
- Large Class:
    - Extract Class (line 51-138)
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

No case.

### More
[More about Large Class](https://refactoring.guru/smells/large-class)
