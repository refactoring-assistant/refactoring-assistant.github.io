# Shotgun Surgery

## Description (What)

Shotgun Surgery is when making a small change to one class leads you to make many small changes to other classes.

## How to Locate It (Where)

Look for an extreme split up of responsibility: One responsibility that has been split up among many files.

## How It Manifests(Why)

This code smell can occur because of overuse of treating `Divergent Change` code smell

In an attempt to follow Single Responsibility principle, you might be tempted to create a class for every small responsibility. 

In reality this may seem normal, but you have to add a logical minimum limit for defining a single responsibility. 

Creating many small classes that might together define a single responsibility can lead to having to make changes to many classes when trying to make a change to one class (one part of the larger responsibility).

## How to Fix It (Possible Treatments)

In Example 1, `Move Method` is used to move methods back to a larger responsibility class. You should move methods that belong together in the larger logical grouping to one class.

In Example 2, a combination of `Move Method` and `Move Field` is used since the fields used in the methods need to go with the methods.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/shotgun-surgery#:~:text=Treatment)

## Examples

### Example 1

#### Before:

Both `CheckProcessor` class and `DirectDepositProcessor` classes print information about the `BankAccount` class. If there is any change in the way print is to be handled or another field is added to `BankAccount` class, change will need to be made to the other two classes as well.

```
Observed Code Smells:
- Shotgun Surgery (lines 59, 94-95)
- Feature Envy (lines 59, 94-95)
```

#### After:

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

---

### Example 2

#### Before:

Imagine this system consist of many types of sensors (now 3), and each of the sensors has a battery inside of it.

But essentially, batteries and sensors are separate entities. So it makes no sense, when we change something about the  

battery, we should go into every sensor classes to have that done. It leads to Shotgun issue.
```

Observed Code Smells:
- Shotgun Surgery (line 26, lines 28-29, line 38, line 42, )
```

#### After:

Applied `Move Method` and `Move Field` to the SensorData class to have the constants and the check logic managed

in a centralized data system.
```

Refactoring Applied:
- Shotgun Surgery 
  -  Move Field (lines 129-131)
  -  Move Method (lines 146-160)
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

None

### More

[More about Shotgun Surgery](https://refactoring.guru/smells/shotgun-surgery)
