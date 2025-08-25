# Data Clumps
## Description (What)
An identical group of variables show up repeatedly.

These could be variables for connecting to a database, a client or repeatedly being passed across methods

## How to Locate It (Where)
Look for repeated sets of variables in method arguments

## How It Manifests (Why)
It's often easier to keep sending the list of variables to different functions.

It could also be due to 'copypasta programming'.

## How to Fix It (Possible Treatments)

In Example 1, `Extract Class` is used to create a new class for the data clumps and the new class is passed in method arguments instead of the variables separately.

We want to be able to reduce the amount of parameters we pass to methods and also by passing an object of a class created for parameters, we make it easier to change anything about the parameters themselves in a single place instead of in all methods that take those parameters.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/data-clumps#:~:text=Treatment)

## Examples

### Example 1

#### Before: DataClumpsSmelly.java
The connection variables for the MongoDB connection are an identical group of variables that need to be grouped together.

```
Observed Code Smells:
- Data Clumps (lines 2-5) 
```

---

#### After: DataClumpsRefactored.java

Applied `Extract Class` on the variables to create their own class to be passed to the actual connecting class

```
Refactoring Applied:
- Data Clumps
    - Extract Class (MongoDbConnectionVariables)
```

```
Observed Code Smells After Refactoring:
- None
```

---
---

### Example 2

#### Before: DataClumpsSmelly.java

---

#### After: DataClumpsRefactored.java

## When to Ignore
In cases where passing an object in method parameters might create an undesirable dependency between two classes

### More
[More about Data Clumps](https://refactoring.guru/smells/data-clumps)
