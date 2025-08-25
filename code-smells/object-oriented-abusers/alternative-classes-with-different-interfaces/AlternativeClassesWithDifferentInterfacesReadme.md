# Alternative Classes With Different Interfaces

## Description (What)

Two classes which perform identical functions but have different method names, field names and have different interfaces.

## How to Locate It (Where)

Look for hierarchies that look similar and perform similar functions. Look at the logical use of the class and make sure that there aren't other interfaces and classes that supposedly perform similar or exactly same functions.

## How It Manifests (Why)

This can happen in cases where two developers working on the same codebase eventually end up creating the same hierarchies and were unaware of a similar structure existing in the program already.

## How to Fix It (Possible Treatments)

In Example 1, we simply removed on of the duplicate hierarchies. A more formal treatment is possible by slowly renaming methods, moving methods and eventually simplifying the two classes such that they appear identical and then removing one of them.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/alternative-classes-with-different-interfaces#:~:text=Treatment)

## Examples

### Example 1

#### Problem ACDIBE1.java
There are two different interfaces each of which have their respective implementations all of which are identical but only in method names and field names.
```
Observed Code Smells:
- Alternative Classes With Different Interfaces (lines 12-20 and 65-107)
```

#### Solution ACDIGE1.java
Removed one of the similar interface and its class.
```
Refactoring Applied:
- Alternative Classes With Different Interfaces
    - Delete alternate interface and its implementation
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

In cases where these two classes might be part of different programs, libraries or frameworks

## More

[More about Alternative Classes With Different Interfaces](https://refactoring.guru/smells/alternative-classes-with-different-interfaces)
