# Simple Character Translator in Java

> This program translates one character to another character (or string of characters) using .lang files

This was created entirely for fun and you're free to make edits / forks of this repo, however I would appreciate the author credit left within the code.

# How to create .lang files, and how they work.

To create new langfiles check the existing examples in the /lang/ directory within this repo.
The general system is

```
char1/char2/char3/etc {
what to make it
example here
test 3
}
char4 {
example 5
}
```

> Use "/" between characters in the lang file to use that output with multiple inputs (eg: "a/A {" == for both "a" and "A" use the code within the {})

> It is important to keep the "{" on the same line as the input characters and "}" on a line by itself after the output string.

> You do not need to have all of the outputs lines be the same length, the code will make all characters the same length 
(Eg: Spaces = * in this example)

```
a {
abc****
123
}
```
would output to 
```
abc****
123****
```
in the final output as to avoid spacing issues

similarly 
```
a {
1
1
1
}
b {
2
2
}
_ {
 
}
```
with the translation input of "a b a"
would become
```
1   1
1 2 1
1 2 1
```

keeping spacing consistent.

Spaces are automatically converted to "_" characters when translated, to include a space translation add: 
```
_ {
Space Translation Here
}
```

Any characters which are not defined in the lang file will be skipped in the final output.