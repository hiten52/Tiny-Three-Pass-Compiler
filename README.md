# Tiny Three-Pass Compiler

The Tiny Three-Pass Compiler is a simple, educational compiler for a small programming language, targeting a small assembly language. It demonstrates a three-pass compilation process including lexical analysis, syntax analysis, and code generation.

### Example
A function which computes a^2 + b^2 might look like:
```javascript
    [ a b ] a*a + b*b
```

A function which computes the average of two numbers might look like:
```javascript
    [ first second ] (first + second) / 2
```

Variables are strings of alphabetic characters. Numbers are strings of decimal digits representing integers.

## Getting Started

### Prerequisites
Ensure you have the following software installed before proceeding:

**Java JDK 8 or higher**: Necessary to run Java applications.\
**Gradle**: Used for building and running the compiler.

### Installation

**1. Clone the Repository**:
Clone the project to your local machine using the following command:
```bash
git clone https://github.com/hiten52/Tiny-Three-Pass-Compiler.git
```

**2. Navigate to the Project Directory**:
Change into the project directory:
```bash
cd tiny-three-phase-compiler
```

**3. Build the Project**:
Compile the project using Gradle:
```bash
gradle build
```

### Usage
Run the compiler through your command line interface:
```bash
java -cp app/build/classes/java/main App
```

## Programming Language Syntax

The compiler supports a simple language with the following syntax:

```text
function   ::= '[' arg-list ']' expression
arg-list   ::= /* nothing */
             | variable arg-list
expression ::= term
             | expression '+' term
             | expression '-' term
term       ::= factor
             | term '*' factor
             | term '/' factor
factor     ::= number
             | variable
             | '(' expression ')'
```

## Compiler Phases

**Pass 1: Generating the Abstract Syntax Tree (AST)** \
Converts the source code into an AST.

**Pass 2: Constant Expression Reduction**\
Optimizes the AST by folding constant expressions wherever possible.

**Pass 3: Code Generation**\
Converts the optimized AST into an array of assembly instructions compatible with a simple simulated processor.

## Simulation
The compiler includes a simple simulator for the target machine with two registers (R0 and R1), a stack, and an array of input arguments which interprets the assembly instructions. The simulator supports basic operations such as load, store, arithmetic operations, and stack management.

Supported Instructions
```javascript
IM n - Load the constant value n into R0
AR n - Load the nth input argument into R0
SW - Swap R0 and R1
PU - Push R0 onto the stack
PO - Pop the top value off of the stack into R0
AD - Add R1 to R0
SU - Subtract R1 from R0
MU - Multiply R0 by R1
DI - Divide R0 by R1
```

![mytutorial-ezgif com-speed](https://github.com/hiten52/Tiny-Three-Pass-Compiler/assets/128628308/539bcd6c-95a0-4dec-8c27-240bc73832b6)
