# Primitive Obsession
## Description (What)

The use of primitive types (like string, int, etc.) in place of appropriate non-primitive types for variables that have special requirements (Dates, currencies, etc.)

The use of string arrays where each index is a different field name also constitutes Primitive Obsession

Any variable that might require special constraints that need to be validated or have methods specifically associated with the validation, creation and updation of the value should have specific non-primitive types (new class or used from a pre-existing library)

## How to Locate it (Where)

Look at the fields of the class to determine if some of the variables could benefit from using their own special data-types. A lot of primitive variables in the fields of a class usually point to the presence of Primitive Obsession

## How It Manifests (Why)

Developers often make fields of a class early on without knowing the full extent of the variable's scope and capabilities, which leads to sticking with primitive fields for the time being but are then never updated to a specific data type.

## How to Fix It (Possible Treatments)

Example 1 below uses the treatment `Replace Data Value With Object` which is used when you have a set of fields that can be logically grouped together into an Object. 

This is useful for example when you have a set of fields that might require validation together and the values depend on each other in some way.

Example 2 below also uses the treatment `Replace Data Value With Object`, as explained above.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/primitive-obsession#:~:text=Treatment)

## Examples

### Example 1

#### Before:

```
Observed Code Smells:
- Primitive Obsession (lines 2-5)
    - Too many primitive variables and a method that can be put into a separate class to represent itself.
```

---

#### After:
Applied `Replace Data Value with Object` treatment

```
Refactoring Applied:
- Primitive Obsession
    - Added `CardboardBox` class to move primitives into their own class
```

```
Observed Code Smells After Refactoring:
- None
```

### Example 2

#### Before:

For the array of TutorialModals, it poses the issues of Primitive Obsession rather than using an Object

to encapsulate the information related to TutorialModals.

```
Observed Code Smells:
- Primitive Obsession (line 9, lines 15-19).
```

---

#### After:

Applied `Replace Array with Object` to get rid of the array of type String.

A new class `TutorialModalManager` is created to deal with assign tutorials.

```
Refactoring Applied:
- Primitive Obsession
    - Replace Array with Object (line 6-45, line 50, lines 61-63).
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

None

### More

[More about Primitive Obsession](https://refactoring.guru/smells/primitive-obsession)
