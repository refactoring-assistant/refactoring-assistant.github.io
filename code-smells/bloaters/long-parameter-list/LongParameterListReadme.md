# Long Parameter List

## Description (What)

A method which has greater than 3 or 4 parameters in its parameter list

## How to Locate it (Where)

Look for constructors, methods that take in more than 3 or 4 parameters, 

especially parameters that are logically related and can be made part of the same object.

## How It Manifests (Why)

Sometimes long parameter lists can manifest from refactoring and moving parameters, fields and variables around in a way that now methods that relied on fields now need to be given the same variable through its parameters.

Long parameter lists can also manifest because a method does more than required and can be further broken down into two or more methods to maintain more modular and maintainable methods.

## How to Fix It (Possible Treatments)

By using the `Introduce Parameter Object` treatment, we created an object out of the logically similar parameters. This treatment can lead to the creation of `Lazy Class` or `Data Class` code smells so it is important to remember the consequences of introducing objects and classes.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/long-parameter-list#:~:text=Treatment)

## Examples

### Example 1

#### Before: 

The method calculateDistance has 4 parameters (2 of which are logically similar)

```
Observed Code Smells:
- Long Parameter List (line 4)
```

#### After:

Applied 'Introduce Parameter Object' and added a LocationCoordinates class to hold the values with getters.

```
Refactoring Applied:
- Long Parameter List
    - Introduce Parameter Object (LocationCoordinates)
```

```
Observed Code Smells After Refactoring:
- None
```

---

### Example 2

#### Before:

The logic of activatePromotion is there but instead of calling the relevant method to catch the logic,

it passes new unnecessary parameters.

#### After:

Applied `Replace Parameter with Method Call` to get rid of the long parameter list but preserve the logics.

```
Refactoring Applied:
- Long Parameter List
    - Replace Parameter with Method Call (line 45-66)
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

Don't shorten parameter lists if doing so causes unwanted dependencies between classes

## More
[More about Long Parameter Lists](https://refactoring.guru/smells/long-parameter-list)
