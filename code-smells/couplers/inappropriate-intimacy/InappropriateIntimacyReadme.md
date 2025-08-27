# Inappropriate Intimacy

## Description (What)
A class uses the internal fields and methods of an object directly

## How to Locate It (Where)

Look for public fields being access by other classes. Classes should know as little about each other as possible.

## How It Manifests (Why)

Can occur due to fields being public and being accessed by other classes because it is easier to do instead of creating a getter/setter method.

## How to Fix It (Possible Treatments)

In Example 1, `Hide Delegate` is used. This treatment requires you to create getter and setter methods for the field that needs to be accessed to allow for an "official" relationship as compared to dirt mutating. If a public method is also being accessed along with the fields it is better to first `Extract Class` and create a new class and then move the fields and methods to that new class and use it from there.

In Example 2, `Replace Delegate with Inheritance` is used. Because the associations between the classes need to be corrected. 

A `Delegation` is inappropriate in the particular case, instead, an `inheritance` should be adopted.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/inappropriate-intimacy#:~:text=Treatment)

## Examples

### Example 1

#### Before:

`Library` class uses the internal fields of `Book` class as they are marked as public.
```
Observed Code Smells:
- Inappropriate Intimacy (lines 38, 43, 44)
```

---

#### After:

Applied `Hide Delegate` to make field private and create a getter for the field instead to make the relation "official" instead of directly mutating the field. This is step 2 of the Extract Class and Hide Delegate treatment but since only a field is being accessed, the `Extract Class` part seems unecessary and making the field private from public seems to be enough.

```
Refactoring Applied:
- Inappropriate Intimacy
    - Hide Delegate
```

```
Observed Code Smells After Refactoring:
- None
```

### Example 2

#### Before:

Imagine this coffee machine is making 4 types of coffee:

Espresso (hot only),
Americano(iced and hot),
Latte(iced and hot),
CafeMocha(iced and hot).

The interface is Coffee, which is firstly implemented by an AbstractCoffee class and then these 4 types of coffees.

The problem lies on when the machine starts to make a CafeMocha, it grabs a Latte first and continue working on

top of that Latte; Even though the final ingredients are correct, private fields and methods from class `Latte` are

constantly used in class `CafeMocha`. Same wrong pops out for Espresso and Americano as well.

```
Observed Code Smells:
- Inappropriate Intimacy (line 78, line 82, line 91, line 131, line 135, line 145)
                         
```

---

#### After:

Applied `Replace Delegate with Inheritance` to get the thing done easier and get rid of the `Inappropriate Intimacy`.
```

Refactoring Applied:
- Inappropriate Intimacy
  - Replace Delegate with Inheritance (lines 78, line 88, line 100, line 109, lines 138, line 141, line 149, line 154, 
                                       line 159) 
```

```
Observed Code Smells After Refactoring:
- None.
```

## When to Ignore

None

## More
[More about Inappropriate Intimacy](https://refactoring.guru/smells/inappropriate-intimacy)
