# Inappropriate Intimacy

## Description (What)
A class uses the interal fields and methods of an object directly

## How to Locate It (Where)

Look for public fields being access by other classes. Classes should know as little about each other as possible.

## How It Manifests (Why)

Can occur due to fields being public and being accessed by other classes because it is easier to do instead of creating a getter/setter method.

## How to Fix It (Possible Treatments)

In Example 1, `Hide Delegate` is used. This treatment requires you to create getter and setter methods for the field that needs to be accessed to allow for an "official" relationship as compared to dirt mutating. If a public method is also being accessed along with the fields it is better to first `Extract Class` and create a new class and then move the fields and methods to that new class and use it from there.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/inappropriate-intimacy#:~:text=Treatment)

## Examples

### Example 1

#### Problem IIBE1.java
`Library` class uses the internal fields of `Book` class as they are marked as public.
```
Observed Code Smells:
- Inappropriate Intimacy (lines 38, 43, 44)
```

---

#### Solution IIGE1.java
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

## When to Ignore

None

## More
[More about Inappropriate Intimacy](https://refactoring.guru/smells/inappropriate-intimacy)
