# Refused Bequest

## Description (What)

A class that implements an interface but doesn't use all of its methods, there is a discrepency between the interface and class. This can apply to any hierarchy.

## How to Locate It (Where)

Look at two or more sub-classes that implement the same interface but either override different sets of methods or implement different subsets of methods.

## How It Manifests (Why)

This can occur when the basis of the existence of the interface is logically flawed. Any class that implements an interface must be logically coherent to the interface and must strictly implement all its methods. 

It can also occur when a programmer tries to simply their codebase by merging two or more similar looking classes under a single interface.

## How to Fix It (Possible Treatments)

In Example 1, `Extract Superclass` is used. This treatment identifies the truly common methods implemented by the sub-classes and keeps only those in the superclass and simplifies the interface. This can have the effect of broadening the scope of the interface.

In Example 2, `Extract Superclass` is also used for the roughly same reasons.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/refused-bequest#:~:text=Treatment)

## Examples

### Example 1

#### Before:

Car and Bicycle both implement the interface Vehicle since they share common methods and logical attributes. However some of the methods of the interface only apply to the Car and not Bicycle and so the Bicycle is not able to fulfill some duties of Vehicle correctly since they do not apply to it.

```
Observed Code Smells:
- Refused Bequest (lines 93-101)
```

---

#### After:

Applied Extract Superclass and removed the non-common functions from the interface Vehicle. Created a new interface that extends the old interface.


```
Refactoring Applied:
- Refused Bequest
    - Extract Superclass(refuel() and replaceEngine() from Vehicle interface)
```

```
Observed Code Smells After Refactoring:
- None
```

### Example 2

#### Before:

The class `LegoPart` that related to toys,

intends to use the dimension getter method only from class `BuildingBrick` for construction,

Some more fields and methods in class `BuildingBrick` are not applicable to class `LegoPart`.

The inheritance relationship is embarrassing.

```
Observed Code Smells:
- Refused Bequest (line 44-108)
```

---

#### After:

Applied `Extract Superclass` method to consolidate the common dimension methods in to a

common superclass `Block` and then have class `BuildingBlock` and class `LegoPart` to extends it with

their own features.
```

Refactoring Applied:
- Refused Bequest:
    - Extract Superclass (line 12-45)
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

None

## More

[More about Refused Bequest](https://refactoring.guru/smells/refused-bequest)
