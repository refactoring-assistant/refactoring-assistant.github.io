# Feature Envy
## Description (What)

When one method accesses the fields or methods of another object more than it does itself

## How to Locate It (Where)

Look for methods where an object's fields or methods (objects either from the class' fields or passed through parameters) are used more than it's own classes fields or methods.

## How It Manifests (Why)

It might be easier to use another object's fields or methods directly in the method of an object.

## How to Fix It (Possible Treatments)

In Example 1, `Move Method` treatment is used. This treatment is applied when a method that is being called a lot by another class' method is actually better suited to that class itself. Doing this reduces the number of calls to another object and keeps changes easier and restricted to a single class.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/feature-envy#:~:text=Treatment)

## Examples

### Example 1


#### Problem FEBE1.java
The data of `Record` class, specifically genre, name and duration is accessed far more by the class `RecordPlayer` than `RecordPlayer` is accessing its own data

```
Observed Code Smells:
- Feature Envy (lines 57-61, 63-66)
```

---

#### Solution FEGE1.java
Used `Move Method` to move getFormattedDuration() and getCurrentRecordDetails() to the `Record` class as it is better suited.

```
Refactoring Applied:
- Feature Envy
    - Move Method (getFormattedDuration, getCurrentRecordDetails)
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

In cases where there is use of the Strategy Pattern or Visitor Pattern. Also in cases of Data Access Objects

## More

[More about Feature Envy](https://refactoring.guru/smells/feature-envy)

