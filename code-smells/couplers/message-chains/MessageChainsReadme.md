# Message Chains

## Description (What)

Message chains are simply multiple chained calls to functions to retrieve data. Message chains create a chain of dependency throughout the program which can be harder to keep track of as the chain increases and any changes made at any part of the chain might require refactoring in other parts of the chain

## How to Locate It (Where)

Look for calls resembling `a().b().c().d()` where data is being retrieved through a chain of function calls where each function in the call calls another function and retrieves data from it.

## How It Manifests (Why)

While writing code in silos, it comes naturally that when you need to interact between Classes or Methods, it will require using chains to bring information to the client surface.

## How to Fix It (Possible Treatments)

In Example 1, `Hide Delegate` is used to push the chain to an inner class which leads the message chain being hidden behind a single public method which itself contains a chain of private or internal methods. `Hide Delegate` simplifies the end call for the client.

Other treatments are also possible based on the specific scenario, they can be found [here](https://refactoring.guru/smells/message-chains#:~:text=Treatment)

## Examples

### Example 1

#### Problem MCBE1.java
For the vehicle to get the address for the latest order to be delivered, it needs to go through a message chain of going to the `Distributor` object which returns a `Warehouse` object which returns an `Order` object from which the address can be retrieved.

```
Observed Code Smells:
- Message Chains (line 117)
```

---

#### Solution MCGE1.java
Applied `Hide Delegate` by adding a new public method `getLatestOrderDestination` to `Distributor` class to make the calls internal
Removed `getWarehouse()` method from `Distributor` so it remains private and reduces its public exposure.

```
Refactoring Applied:
- Message Chains
    - Hide Delegate (getWarehouse())
```

```
Observed Code Smells After Refactoring:
- None
```

## When to Ignore

In cases where solving this code smell might inadvertently cause the `Middle Man` code smell.

## More

[More about Message Chains](https://refactoring.guru/smells/message-chains)
