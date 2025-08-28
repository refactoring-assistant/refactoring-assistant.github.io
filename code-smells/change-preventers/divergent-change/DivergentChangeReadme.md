# Divergent Change
## Description (What)

Divergent Change is when you want to make changes to a field or a class as whole and end up having to also make changes to methods unrelated to that change in the class.

## How to Locate It (Where)

Look for strong coupling between fields and methods. In general all fields that are being used in methods must be specific to that method.

## How It Manifests (Why)

Divergent Change can occur due to a poor program structure. Especially when a class is not modular enough or contains strong coupling with fields.

## How to Fix It (Possible Treatments)

In Example 1, `Extract Class` is used. `Extract Class` in this case is used because of strong coupling in the expectation of the structure of a field (validation), 

which causes changes when adding a new type of protocol in methods unrelated to that change.

In Example 2, `Extract Class` is also used. We say that the class contains multiple purposes, but ideally, each class should have 

its own purpose and functionality. Therefore, we extract each functionality as a class, preventing the divergent change of one huge clas

when some change has to be made. 

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/divergent-change#:~:text=Treatment)

## Examples

### Example 1

#### Before:
If we were to add a new type of connection, we would have to make changes to both the unrelated methods getDomain() and checkValidUrl()

```
Observed Code Smells:
- Divergent Change (lines 9-18, 20-27)
```

#### After:
Applied `Extract Class` to split the behavior into two classes. Both classes have the same behavior so, created interface Connector for inheritance

```
Refactoring Applied:
- Divergent Change
    - Extract Class (HTTPConnector, FTPConnector)
```

```
Observed Code Smells After Refactoring:
- None
```

---

### Example 2

#### Before:

The services of Draw, Move and Resize are all inside one class which is LucyChartService.

If we add one more shape to the ShapeType Enum, the structure requires to change many spots altogether within

the LucyChart class, which constitutes a Divergent change.
```

Observed Code Smells:
-  Divergent Change (lines 75-158)
-  Switch Statements (lines 78-98, lines 103-122, lines 128-157)
```

#### After:
The services of Draw, Move and Resize should have their own classes and single responsibility.
```

Refactoring Applied:
- Divergent Change:
    - Extract class (lines 124-153, lines 155-184, lines 186-215)
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

None

### More

[More about Divergent Change](https://refactoring.guru/smells/divergent-change)
