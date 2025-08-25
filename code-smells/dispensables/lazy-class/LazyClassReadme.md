# Lazy Class

## Description (What)

A lazy class is a class that doesn't do much or doesn't contribute enough to warrant your attention.

## How to Locate It (Where)

Find classes that appear small, have limited interaction with the rest of the program and don't contribute to the overall program.

## How It Manifests (Why)

Classes that were originally designed to support some future work or have reduced in size by a lot after refactoring.

## How to Fix It (Possible Treatments)

In Example 1, `Collapse Hierarchy` is used to remove the Lazy Class. `Collapse Hierarchy` works by removing a superclass that is not doing much more than simply than existing for the sake of hierarchy in the case of Lazy Class.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/lazy-class#:~:text=Treatment)

## Examples

### Example 1

#### Problem LZCBE1.java
Cheetah was added considering that it might have unique functionality in the future.

```
Observed Code Smells:
- Lazy Class (lines 29-33)
```

---

#### Solution LZCGE1.java
Applied `Collapse Hierarchy` to remove the class given that it is the same as the super class `Cat`

```
Refactoring Applied:
- Lazy Class
    - Collapse Hierarchy (Cheetah)
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

In cases where classes are created in specific cases to project future work or extensions

## More

[More about Lazy Class](https://refactoring.guru/smells/lazy-class)
