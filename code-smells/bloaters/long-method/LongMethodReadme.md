# Long Method
## Description (What)
A method/function that has too many lines of code. 
A general rule of thumb is to check if any method longer than 10-20 lines is a long method.

A long method is also described as method that primarily violates the Single Responsibility principle.

## How to Locate It (Where)
An easy way to find Long Methods is to look for any comments in the method. 
If there are comments, it usually means that the code explained by explicit comments can be made into its own method.

## How It Manifests (Why)
Programmers keep adding onto the method instead of creating a new method because its easier.

## How to Fix It (Possible Treatments)
Usually, conditional operators, loops and switch statements are a good indicators that code
can be moved to a separate method.

- For conditionals, use `Decompose Conditional`.
- If loops are in the way, try `Extract Method`.
- If long Switch statement, try `Extract Interface` plus `HashMap Technique`, to preserve the automaticity.

In both examples below, the treatment used is called `Extract Method`.
In this treatment, we identify a part(s) of the method which can qualify as its own method under the single responsibility rule and remove it from the original method and make it its own separate method.

Other treatments are also possible based on the specific scenario if `Extract Method` cannot be applied, they can be found [here](https://refactoring.guru/smells/long-method#:~:text=of%20spaghetti%20code.-,Treatment,-As%20a%20rule)

## Examples

### Example 1

#### Before: LongMethodSmelly.java
The method `boardFlight()` does a lot of work that can make it confusing to understand and should be broken into smaller methods.

```
Observed Code Smells:
- Long Method (line 62-92)
```

---

#### After: LongMethodRefactored.java

Applied `Extract Method`s to the method and created three new methods `securityCheck`, `noFlyListCheck`, `passengerCanBoard`.

```
Refactoring Applied:
- Long Method
    - Extract Method (securityCheck, noFlyListCheck, passengerCanBoard)
```

```
Observed Code Smells After Refactoring:
- None
```

>Note: The if-else-if statements in boardAirplane() method are not considered a code smell because of the ignore case in Switch Statements code smell `When a switch operator performs simple actions, there’s no reason to make code changes`

---
---

### Example 2

#### Before: LongMethodSmelly.java

The class TextAnalyzer is a utility class that contains a useful method to calculate information about a .txt file.
The 'analyze' method is now doing everything:
1. Calculate the word count,
2. Calculate the sentence count,
3. Calculate the average word length,
4. Identify the most common word.
which, makes the method extremely long.

```
Observed Code Smells:
- Long Method (lines 17-53, roughly)
```

---

#### After: LongMethodRefactored.java
Applied `Extract Method` to take each calculation out as a separate method.

```
Refactoring Applied:
- Long Method
    - Extract Method (line 17-70, roughly)
```

>Note: The line numbers might have a little discrepancies.
```
Observed Code Smells After Refactoring:
- None
``` 

## When to Ignore
Not applicable in the given examples.

### More
[More about Long Method](https://refactoring.guru/smells/long-method)
