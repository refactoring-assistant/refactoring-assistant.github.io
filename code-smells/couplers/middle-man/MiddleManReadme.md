# Middle Man

## Description (What)

Middle Man is a class whose sole purpose in existence is to act as an intermediary between two other classes.

## How to Locate It (Where)

Look at classes that simply act as a gateway for data to passed between two other classes. Classes that do only delegation work of moving data are generally considered as `Middle Man`s. Remember that a `Middle Man` doesn't do anything more than this. If a class looks like a `Middle Man` but also performs actual work on the data itself like processing, cleaning, converting, it is not a `Middle Man`.

## How It Manifests (Why)

Trying to minimize the `Message Chains` code smell might end up bringing about this code smell.

It can also occur due to the slow removal of functions of a class to the point where it only delegates work at one stage.

## How to Fix It (Possible Treatments)

In Example 1, `Remove Middle Man` treatment is used which simply means deleting the class that acts as a middle man and rerouting method calls directly to the main class which the middle man was acting as a facade for.

Example 2 is an ignoring case, please see `When to Igonore` on the page below. 

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/middle-man#:~:text=Treatment)

## Examples

### Example 1

#### Before:

`ProductHandler` acts as a middleman to `ProductManager` by giving it access to only what it needs.

```
Observed Code Smells:
- Middle Man (lines 55-66)
- Data Class (line 55-66)
```

---

#### After:

Used `Remove Middle Man` by removing `ProductHandler` and letting `ProductManager` directly use `Product` methods

```
Refactoring Applied:
- Middle Man
    - Remove Middle Man (removed ProductHandler)
```

```
Observed Code Smells After Refactoring:
- None
```

### Example 2

#### Before:

The class LucyChart is a proxy to gate keeping all the original servicing objects: Draw, Move and Resize.

The class LucyChart is doing little other than delegating all the works to other classes.

The class LucyChart is a 'middle man' in this case.

```
Observed Code Smells:
-  Middle Man (line 60-91)
  - In this case, the coding follows the proxy pattern, where the 'middle man' code smell can be ignored.
```

## When to Ignore

In cases where a design pattern like the Proxy or Decorator Design Patterns are used

In cases where adding a middle man removes unnecessary interdependence between two classes

## More

[More about Middle Man](https://refactoring.guru/smells/middle-man)
