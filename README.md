# Construct Alphabetic Order
The class ConstructAlphabeticOrder takes in a filename from stdin, 
read in the input file containing lines of words in a sorted alphabetical order,
compute the alphabetic order and print it to stdout, with each character separated by ' '

Alternatively, the function *ConstructAlphabeticOrder.constructAlphabet* can be called directly, 
with one given parameter *String[]: words*, holding the sorted words in the to-be-computed alphabetical order,
and output the correct order in a character list

## ConstructAlphabeticOrder.constructAlphabet
**input**
An array of words, pre-sorted in the to-be-computed alphabetical order

**output**
List of lowercase Characters representing the alphabetical order

**special cases**
 - If input is an empty array or array of empty strings, return an empty List
 - If input has multiple valid outputs, return one of the valid ordering

## how to compile
Run following commands in terminal:
 - to compile ConstructAlphabeticOrder.java: '*javac ConstructAlphabeticOrder.java*'
 - to compile Test class: '*javac TestConstructAlphabet.java*'

## how to run
 - run construct alphabet method:
   - run by giving input file: '*java ConstructAlphabeticOrder [inputfile name]*'
   - run by calling method directly, use the following code: (refer more details in TestConstructAlphabet.java)
        *ConstructAlphabeticOrder.constructAlphabet([$input String array])*
 - run Test class: '*java TestConstructAlphabet*' (more test details can be found in the java file, and please feel free to add more test cases if you'd like)

## algorithm explanation
The algorithm construct a "graph" based on given input. We have a hashMap so that each character *c* points to a set of characters *k* greater than *c*, and 
an int array to keep number of characters smaller than each character *c*.
**Note: all characters *c* are pointed by a smaller character *k*, and we keep record of the number of such *k*'s**

The algorithm then start removing the character *c* with no smaller characters before it, and all the out going pointers *p* from *c*. After removing *c*, we
find the newest smallest character/s *c'*, and repeat this procedure of removing and finding new smallest.

## complexity analysis
Denote *n* as the number of distinct characters, and *m* as the number of pointers in total from all characters *c* to characters greater than *c*
 - time: *O(n + m)*, since we need to go over all characters and all pointers from each character.
 - space: *O(n + m)*, since we need to store all characters and all pointers


   

