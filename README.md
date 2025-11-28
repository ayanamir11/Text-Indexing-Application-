# Text-Indexing-Application-
Java application that reads text, cleans tokens, and generates an alphabetized word index using BST-backed data structures.



# Features

* Custom BST implementation (insert, search, traversal)
* Token cleaning & normalization (punctuation removal, lowercase)
* File reading and processing using Java I/O
* Word–page index built with `IndexAdd` and `Pagelist` classes
* Modular OOP architecture with recursion
* JUnit tests for correctness

---

# Project Structure

```
BinarySearchTree.java
BSTNode.java
IndexAdd.java
Pagelist.java
FileReadingDemo.java
Client.java
AyanAmirBSTTest.java
input.txt / uscons.txt
```

---

# How It Works

1. Read tokens from a text file
2. Clean and normalize each token
3. Insert into a BST-backed dictionary
4. Track page numbers for each word
5. Print an alphabetized index via in-order traversal

---

# Run the Program

```bash
javac *.java
java Client
```

---

# Skills Demonstrated

* Data Structures (BSTs)
* Java OOP & modular design
* Recursion & algorithm design
* File parsing & text processing
* Software testing with JUnit
