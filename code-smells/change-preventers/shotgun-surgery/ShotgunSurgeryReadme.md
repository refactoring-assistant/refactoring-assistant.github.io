# Shotgun Surgery

## Description (What)

Shotgun Surgery is when making a small change to one class leads you to make many small changes to other classes.

## How to Locate It (Where)

Look for an extreme split up of responsibility: One responsbility that has been split up among many files.

## How It Manifests(Why)

This code smell can occur because of overuse of treating `Divergent Change` code smell

In an attempt to follow Single Responsbility principle, you might be tempted to create a class for every small responsbility. In reality this may seem normal, but you have to add a logical minimum limit for defining a single responsibility. Creating many small classes that might together define a single responsbility can lead to having to make changes to many classes when trying to make a change to one class (one part of the larger responsbility).

## How to Fix It (Possible Treatments)

In Example 1, `Move Method` is used to move methods back to a larger responsbility class. You should move methods that belong together in the larger logical grouping to one class.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/shotgun-surgery#:~:text=Treatment)

## Examples

### Example 1


#### Problem SSBE1.java
Both `CheckProcessor` class and `DirectDepositProcessor` classes print information about the `BankAccount` class. If there is any change in the way print is to be handled or another field is added to `BankAccount` class, change will need to be made to the other two classes as well.

```
Observed Code Smells:
- Shotgun Surgery (lines 59,94-95)
- Feature Envy (lines 59, 94-95)
```

#### Solution SSGE1.java
Applied `Move Method` to move the printing logic to `BankAccount` class itself which also removes `Feature Envy` and now any change made to printing logic or any new fields that need to be printed (ex: balance) can be made in one class.
```
Refactoring Applied:
- Shotgun Surgery
    - Move Method (printAccountDetails)
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

None

### More

[More about Shotgun Surgery](https://refactoring.guru/smells/shotgun-surgery)
