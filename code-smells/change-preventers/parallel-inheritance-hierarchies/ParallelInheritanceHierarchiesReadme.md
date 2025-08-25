# Parallel Inheritance Hierarchies

## Description (What)

The structure of your program is such that creating a sub class for a class leads you to having to create a sub class for another class.

## How to Locate It (Where)

Look for inheritance heirarchies that are paralleled throughout your program. Commonly occurs when you have a two or more different interfaces and their respective classes which appear logically similar but are structured in such a way that making changes to one makes you think about making changes to the other.

## How It Manifests (Why)

Often when creating a new inheritance hierarchy there is a subconscious need to create inheritance hierarchies of objects that are used in the new inheritance hierarchy or for the sake of creating interfaces for future extendability.

This can be manageable when having small hierarchies but when classes and interfaces increase, it can become hard to manage.

## How to Fix It (Possible Treatments)

In Example 1, a combination of two treatments, `Move Field` and `Move Method` are used to create a new simpler interface that combines two previous separated interfaces. This way, new classes created to inherit the interface no longer need to be made twice for both previous interfaces.

### Example 1


#### Problem PIHBE1.java
There is a parallel heirarchy between the Weapon interface and Enemy interface where, each time a new enemy is made (for eg. Werewolf), a new weapon needs to be made to complement it (WerewolfWeapon).
```
Observed Code Smells:
- Parallel Inheritance Hierarchies (lines 66-118)
```

---

#### Solution PIHGE1.java
Removed hierarchy in `VampireWeapon` and `GhoulWeapon` by replacing them with a `BasicWeapon` and moved their fields and methods to the new `BasicWeapon` class using `Move Method` and `Move Field`.

```
Refactoring Applied:
- Parallel Inheritance Hierarchies
    - Move Method and Move Field (BasicWeapon)
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

Sometimes having parallel inheritance hierarchies can make sense, where applying the treatments can lead to messier code.

### More
[More about Parallel Inheritance Hierarchies](https://refactoring.guru/smells/parallel-inheritance-hierarchies)
