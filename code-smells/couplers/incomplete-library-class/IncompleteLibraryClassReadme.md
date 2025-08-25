# Incomplete Library Class

## Description (What)

When we use an external library for a method (e.g. Math class), somtimes the functionality offered by the class is not enough for our use case. In such cases we need to in addition to calling the library perform some actions on the output of the library to fit our use case.

## How to Locate It (Where)

Look for lines of code where functions from external libraries are called and the output of those methods are modified in the same way similarly throughout the program or class.

## How It Manifests (Why)

Libraries can become outdated and stay read-only, in which cases, developers themselves must extend their functionality and since the extension might be small, developers might repeatedly use the same line of code all over their program.

## How to Fix It (Possible Treatments)

In Example 1, we use `Foreign Method`. This treatment introduces a dedicated method in which we call the library and perform the necessary additions and return the expected output for our use case. This way, we limit the scope of the external library to a single method in our program and use this method every we require the use of this library.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/incomplete-library-class#:~:text=Treatment)

## Examples

### Example 1

#### Problem ILCBE1.java
Using the Math class, there is no enum for the square root of twice Pi but there is an enum for Pi.

```
Observed Code Smells:
- Incomplete Library Class (lines 4, 10)
```

---

#### Solution ILCGE1.java
Applied `Foreign Method` to create a private method to calculate the square root of twice Pi which can be used instead of calculating it everytime.

```
Refactoring Applied:
- Incomplete Library Class
    - Foreign Method (sqrtTwoPi)
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

In cases where adding the required functionality by extending the library locally generates more additional work than the payoff.

## More

[More about Incomplete Library Class](https://refactoring.guru/smells/incomplete-library-class)
