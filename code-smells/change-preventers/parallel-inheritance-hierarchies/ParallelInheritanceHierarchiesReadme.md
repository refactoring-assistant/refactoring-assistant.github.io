# Parallel Inheritance Hierarchies

## Description (What)

The structure of your program is such that creating a sub-class for a class leads you to having to create a sub class for another class.

## How to Locate It (Where)

Look for inheritance hierarchies that are paralleled throughout your program. Commonly occurs when you have a two or more different interfaces and their respective classes which appear logically similar but are structured in such a way that making changes to one makes you think about making changes to the other.

## How It Manifests (Why)

Often when creating a new inheritance hierarchy there is a subconscious need to create inheritance hierarchies of objects that are used in the new inheritance hierarchy or for the sake of creating interfaces for future extendability.

This can be manageable when having small hierarchies but when classes and interfaces increase, it can become hard to manage.

## How to Fix It (Possible Treatments)

In Example 1, a combination of two treatments, `Move Field` and `Move Method` are used to create a new simpler interface that combines two previous separated interfaces. 

This way, new classes created to inherit the interface no longer need to be made twice for both previous interfaces.

In Example 2, also apply a combination of `Move Field` and `Move Method`, since the fields used in some methods need to move with those methods,

and two parallel class structure could be managed through one central platform, to which the methods and fields move to.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/parallel-inheritance-hierarchies#:~:text=Treatment)

### Example 1

#### Before:

There is a parallel hierarchy between the Weapon interface and Enemy interface where, each time a new enemy is made (for eg. Werewolf), a new weapon needs to be made to complement it (WerewolfWeapon).
```
Observed Code Smells:
- Parallel Inheritance Hierarchies (lines 66-118)
```

---

#### After:
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

### Example 2

#### Before:

Suppose we have 2 operating systems in the Platform Enum, which are iOS and Android.

They both extends the same set of abstract classes(super classes), but now for the same super classes,

a newly-added child class for iOS must lead to a newly-added child class for Android.

This constitutes a Parallel Inheritance Hierarchies.

```
Observed Code Smells:
- Parallel Inheritance Hierarchies (lines 28-40, lines 46-58, lines 65-87)
```

---

#### After:

Instead of having separate classes for different operating systems to implement the same contract,

have a operatingSystem to extend the super class which defines the functions and use EnumMap inside that

operatingSystem class to switch among different operating systems.
```

Refactoring Applied:
- Parallel Inheritance Hierarchies:
   - Move Method and Move Field (lines 32-57, lines 63-88, lines 95-115)
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

Sometimes having parallel inheritance hierarchies can make sense, where applying the treatments can lead to messier code.

### More
[More about Parallel Inheritance Hierarchies](https://refactoring.guru/smells/parallel-inheritance-hierarchies)
