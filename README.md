# refactoring-assistant.github.io

## Repository of code smells

### How to navigate

In the `code-smells` folder you will find the following example file structure:

```
code-smells/
└── bloaters/
    └── data-clumps/
        ├── code-smell-files/
        │   ├── example1/
        │   │   ├── DCGE1.java
        │   │   └── DCBE1.java
        │   └── example2/
        │       ├── DCBE2.java
        │       └── DCGE2.java
        └── DataClumpsReadme.md
```

Each `example` folder has two files in it, a 'good' example and a 'bad' example.
A 'bad' example is a code file that contains the code smell mentioned by its parent folder and a 'good' example is a code file that does NOT contain THAT code smell in it.

A 'good' example code file ends with a `GE` at the end of the file name and a 'bad' example code file ends with a `BE` at the end of the file name.

In each `code smell` folder, there is a markdown file named `CodeSmellNameReadme.md` which contains information about the code smell such as:

 - Description (What is it)
 - How to Locate It (Where you might find it)
 - How It Manifests (Why it may have been introduced)
 - How to Fix It (Possible Treatments)

> **Note:** In the `How to Fix It` section, you will find that there are only 1 or 2 treatments listed. These are the treatments that have been used to fix the 'bad' example code files. There may be more treatments listed. You can find these other treatments at the link at the end of the section.

### Using the website

A website is also available to more easily browse this repository [here](https://refactoring-assistant.github.io)

At the top of the website is the navbar where code smells have been separated by their types.

Clicking on a code smell will bring you to a two pane view. On the left side is the HTML equivalent of the markdown file associated with the code smell and on the right is a tabbed code viewer.

In the tabbed code viewer, the top two tabs are the two different examples and each tab then contains two tabs to show the state of the code before (with code smell) and after refactoring (without code smell).