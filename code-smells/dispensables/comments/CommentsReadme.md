# Comments

## Description (What)

Any comment that explains a line(s) of code inside a method or in the class that is not part of a comment syntax such as JavaDoc

## How to Locate It (Where)

Look for single lines comments that can be removed as the code they are explainig should be self-explanatory

## How It Manifests (Why)

The need to explain a line of code usually means that line of code is not self-explanatory and needs to be ammended such that it is. Any time you feel that a comment is absolutely necessary it is possible that the piece of code being explained can be spinned of into a new method. 

## How to Fix It (Possible Treatments)

Remove the comment and use `Extract Method` to convert the explained code into its own method for more understandable and maintainable code.


Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/comments#:~:text=Treatment)

## Examples

### Example 1


#### Problem CBE1.java
A lot of comments at the end of lines that bloat the code and are dispensable as the code is self-explanatory. Some comments are detailed and required which means methods can be extracted

```
Observed Code Smells:
- Comments (lines 117, 135, 138, 141, 144, 149, 154, 157)
```

#### Solution CGE1.java
Applied `Extract Method` to create a new method called `applyBrightnessToPixel` that better illustrates what that piece of code is to do.
```
Refactoring Applied:
- Comments
    - Extract Method (line 147-158)
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

In places where the comment is used to explain Why a specific implementation is being used
or for explaining complex algorithms after trying to simplify the code.

## More
[More about Comments](https://refactoring.guru/smells/comments)
