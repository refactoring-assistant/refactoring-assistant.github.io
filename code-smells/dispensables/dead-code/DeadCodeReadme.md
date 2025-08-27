# Dead Code

## Description (What)

Dead code is any piece of code (method, line, field) that is unused or unreachable.

## How to Locate It (Where)

Most modern IDEs and Linters have inbuilt dead code detection for public methods and fields and gray out or highlight dead code.

In cases where a field or method is private, your IDE might not gray about dead code, in which case it is imperative to go through your class to make sure any private method or field is not going unused.

## How It Manifests (Why)

Sometimes better, newer or simply different methods are created to superseed older methods. In these cases, the older method become dead code since they are not used anymore. Sometimes methods and fields or variables are created in anticipation of being used in the future but simply remain unused.

## How to Fix It (Possible Treatments)

Simply delete the unused (dead) methods or fields or variables.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/dead-code#:~:text=Treatment)

## Examples

### Example 1

#### Before:

Shipment class has dead method `calculateDistance`  which has been 

superseeded by method `calculateDistanceFast` and thus also making

method `degToRad` dead.

```
Observed Code Smells:
- Dead Code (lines 9-14, 27-29)
```

---

#### After:

`Deleted` unused dead methods `calculateDistance` and `degToRad`.

```
Refactoring Applied:
- Dead Code
    - Deleted unused methods (calculateDistance and degToRad)
```

```
Observed Code Smells After Refactoring:
- None
```

### Example 2

#### Before:

The method `calculate2018BlackFridayPrice` is no longer applicable.

Need to be refactored or deleted.

```
Observed Code Smells:
-  Dead Code (line 34-50)
```

---

#### After:

Simple delete the code.

```
Refactoring Applied:
- Dead Code:
    - Remove Method (No longer exist).
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

None

## More

[More about Dead Code](https://refactoring.guru/smells/dead-code)
