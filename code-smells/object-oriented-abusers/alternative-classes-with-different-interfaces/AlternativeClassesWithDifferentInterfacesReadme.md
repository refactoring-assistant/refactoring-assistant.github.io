# Alternative Classes With Different Interfaces

## Description (What)

Two classes which perform identical functions but have different method names, field names and have different interfaces.

## How to Locate It (Where)

Look for hierarchies that look similar and perform similar functions. Look at the logical use of the class and make sure that there aren't other interfaces and classes that supposedly perform similar or exactly same functions.

## How It Manifests (Why)

This can happen in cases where two developers working on the same codebase eventually end up creating the same hierarchies and were unaware of a similar structure existing in the program already.

## How to Fix It (Possible Treatments)

In Example 1, we simply removed on of the duplicate hierarchies. A more formal treatment is possible by slowly renaming methods, moving methods and eventually simplifying the two classes such that they appear identical and then removing one of them.

In Example 2, a combination of `Extract superclass` and `Rename Method` is used. For the common methods, better coding practice will be extract a super class to push all the 

common methods into that class; accordingly, some methods have to be renamed because the change of placements.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/alternative-classes-with-different-interfaces#:~:text=Treatment)

## Examples

### Example 1

#### Before:

There are two different interfaces each of which have their respective implementations all of which are identical but only in method names and field names.

```
Observed Code Smells:
- Alternative Classes With Different Interfaces (lines 12-20 and 65-107)
```

#### After:

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

---

### Example 2

#### Before:

In the bad example, the developer put too much emphasis on the subject that makes the action,

for example, a bishop can `getBishopRow()`, `getBishopCol()`, `getBishopColor()`, `bishopCanMove()`, `bishopCanKil()`.

In the scenario of the international chess, all the chess pieces have these functionalities.

It doesn't matter who makes the action, just be careful with the rule for each of them respectively.

Therefore, have these chess pieces classes written separately is not ideal, even the names of the methods are different

(which means the subject who makes that action differs), the functionality ends up being the same.

Each of the chess piece can get its locations and color, can kill another chess piece and can move within the bounds.

There are two different interfaces each of which have their respective implementations all of which are identical but only in method names and field names.

```
Observed Code Smells:
- Alternative Classes With Different Interfaces (lines 11-29, lines 41-69,
lines 78-93, lines 107-153, lines 164-179, lines 192-238, lines 253-273, lines 317-405,
lines 422-437, lines 449-495, lines 503-518, lines 530-576)
```

#### After:

Applied `Extract superclass` to summarize the getter methods and constructors.

Applied `Rename Method` to get rid of the subject, e.g., `bishopCanKill()` -> `canKill()`.

```
Refactoring Applied:
- Alternative Classes With Different Interfaces
    - Extract superclass (lines 18-55, lines 58-64, lines 88-94, lines 124-130, lines 163-174, lines 235-241, 
     lines 265-271).
    - Rename Method (lines 36-54)
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

In cases where these two classes might be part of different programs, libraries or frameworks

## More

[More about Alternative Classes With Different Interfaces](https://refactoring.guru/smells/alternative-classes-with-different-interfaces)
