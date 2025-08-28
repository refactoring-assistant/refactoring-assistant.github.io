# Temporary Field

## Description (What)

A field in a class that is given its value and then subsequently used by particular methods and are undefined in the rest of the cases.

## How to Locate It (Where)

Look for fields that aren't explicit assigned a value in the constructor or are assigned default or garbage values in the constructor and fields that are used only in one method in the class and are assigned their proper values in that method.

Also look for fields that get their value based on type code/if-else/switch statements where they get their value based on satisfying a condition but remain unassigned in other conditions.

## How it Manifests (Why)

Temporary fields might arise from the need of variables for complex algorithms or functions in a class that need them and are required to be defined in the fields of the class.

## How to Fix It (Possible Treatments)

In Example 1, we use `Extract Class`. This treatment moves the fields and the methods that require them into their own class such that in the new class, the field is always assigned a proper value. This way the field is always populated with a correct value on object instantation.

In Example 2, `Introduce Null Object` is used. This treatment introduces a Null Object to prevent Null-Pointer error.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/temporary-field#:~:text=Treatment)

## Examples

### Example 1

#### Before:

Variables a and b are always used but c is used only when Quadratic/Cubic equations need to be solved and d only when Cubic equations need to be solved.

```
Observed Code Smells:
- Temporary Field (lines 4-5)
```

#### After:

Applied `Extract Class` to extract all the methods into their own Classes 'LinearSolver', 'QuadraticSolver' and 'CubicSolver' and deleted the original 'PolynomialSolver' class

```
Refactoring Applied:
- Temporary Field
    - Extract Class (LinearSolver, QuadraticSolver, CubicSolver)
```

```
Observed Code Smells After Refactoring:
- None
```

---

### Example 2

#### Before:

The line 50 is checking if a customer is null, considering this case of nullable customer is reasonable

in such a scenario, some object should be created to capture this situation and prevents program crashes

from NullPointer issues.

```
Observed Code Smells:
-  Temporary field (line 40, 47, 54)
```

#### After:

Apply `Introduce Null Object` by creating a new subclass of `Customer` called `NullCustomer`,

mostly just an informative temp variable for checking for null.

```
Refactoring Applied:
- Temporary Field:
    - Introduce Null Object (line 22, line 40-48, line 56, line 65, line 74)
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

None

## More

[More about Temporary Field](https://refactoring.guru/smells/temporary-field)
