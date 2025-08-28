# Data Class

## Description (What)

A data class is a class whose sole purpose is to simply hold some data and have a getter and setter for that data and nothing more.

## How to Locate It (Where)

Look for classes who have fields and only getters and setters as methods for the entire class.

## How It Manifests(Why)

Data classes can be a side effect of treating many code smells (namely Primitive Obsession) where `Extract Class` is used to create a new class but all the class holds is getters and setters for it's fields.

Data classes can also manifest from the requirement of the program structure such that a class is made to simply hold some data that is required by other "Manager" classes.

## How to Fix It (Possible Treatments)

In Example 1, `Encapsulate Field` is used to make fields private and create getters and setters for them. Then `Extract Method` is used to move methods from the original class that now fit better in the Data Class instead. The main motive to fixing `Data Class` should be to move as many methods that specifically only interact with those fields to the data class itself and add functionality beyond simply getting and setting to the data class in question.

In Example 2, `Move Method` is used when we identify some methods actually should belong to the Data class, hence we simply

move them back to the Data Class to get rid of the code smell.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/data-class#:~:text=Treatment)

## Examples

### Example 1

#### Before:

`Task` has only public fields and no getters or setters (encapsulation).

```
Observed Code Smells:
- Data Class (lines 4-8)
```

#### After:

Applied `Encapsulate Field` for name, description and completed fields. Add applied `Extract method` to create the `markAsCompleted` method in the Task class.

```
Refactoring Applied:
- Data Class
    - Encapsulate Field (name, description, completed)
    - Extract Method(markAsCompleted)
```

```
Observed Code Smells After Refactoring:
- None
```

---

### Example 2

#### Before:

There are 2 classes: Plane and PartStudio.

The misplacement of the method `calculateVolume` in PartStudio makes Plane a Data Class.

The method `calculateVolume` should be in class Plane.

```
Observed Code Smells:
-  Data Class (line 38-51).
```

#### After:

Apply `Move Method`, moving the method back to class Plane would help to get rid of the code smell.

```
Refactoring Applied:
- Data Class:
    - Move Method (line 20-29).
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

None

## More

[More about Data Class](https://refactoring.guru/smells/data-class)
