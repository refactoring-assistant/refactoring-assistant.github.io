# Switch Statements

## Description (What)

A complex switch statement of a long sequence of if-else-if statements. Having these causes code to become difficult to maintain in the long run as new conditions are added or new requirements are added, existing code is required to be changed.

## How to Locate It (Where)

Look for long switch statements and long sequences of if-else-if statements based on type code.

## How It Manifests (Why)

When a program starts out small, switch statements and if-else-if statements are easier to write based on type code but as the code base increases it becomes difficult to maintain these control flows as they may spread throughout the program and require changes in each place with the introduction of more types or conditions.

## How to Fix It (Possible Treatments)

In Example 1, we use the `Replace Type Code With Strategy` treatment. Strategy pattern allows for better extendability through the use of maps of keys and values where the correct method is called based on the type keys.

In Example 2, a combination of `Extract Method` and `Move Method` is used. When developers want to preserve the convenience of a switch statement, 

but not including it as a code smell, a better practice would be leverage the data structure of `HashMap` and some re-organization of code.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/switch-statements#:~:text=Treatment)

## Examples

### Example 1

#### Before:

In CommandParser there is a big switch case that calls methods based on the command given to it.
```
Observed Code Smells:
- Switch Statements (lines 33-53)
```

---

#### After:

Applied `Replace Type Code With Strategy` to create a new class that encapsulates each possible command which can provide the command name and execute the action of the command.

```
Refactoring Applied:
- Switch Statements
    - Replace Type Code With Strategy (Action, AttackAction, ...)
```

```
Observed Code Smells After Refactoring:
- None
```

### Example 2

#### Before:

The method `performTest` currently contains a long switch statements based on the sample's test type.

The switch statement carries the responsibility of both deciding which test to go for and the concrete

implementations of that particular test. Therefore, single responsibility is not demonstrated here.

And also, the switch statement is too long.

```
Observed Code Smells:
-  Switch Statements (line 39-100)
```
---

#### After:

Applied `Extract Method` and `Move Method` to get rid of the long switch statements.

Set up an interface called `TestExecutor` and have different tests as different classes to implement this interface.

```
Refactoring Applied:
- Switch Statements:
    - Extract Method and Move Method (line 68-149)
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

In cases where the switch statement of if-else-if statement is used for simple actions

In cases where the switch statement is required by the Factory Design Pattern

## More

[More about Switch Statements](https://refactoring.guru/smells/switch-statements)
