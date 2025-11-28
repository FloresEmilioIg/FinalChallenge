# Project: Banking Application Input Validation Simulation

This script simulates the input validation process for a banking application's operation request form.  
It validates five key input fields based on the **Equivalence Class Partitioning** test design.

## Prerequisites

1. **Java Development Kit (JDK)**  
   You must have a Java Runtime Environment (JRE) or JDK installed (**version 8 or newer** is recommended).

## How to Run the Script

The simulation can be run via the command line (Terminal or Command Prompt) in three simple steps:

---

### 1. **Save the file**
Ensure the `BankingAppSimulation.java` file is saved in your desired 
directory.

---

### 2. **Compile the Java file**
Navigate to the directory where you saved the file and compile it using:

```bash
javac BankingAPPTesting.java
```

3. **Execute the compiled class**:
Run the compiled class file (note: do not include the .class extension):

```bash
java BankingAppSimulation
```

### Output

The script will automatically execute three representative test cases:

**Test Case 1 (Valid)**: A successful request demonstration.

**Test Case 2 (Boundary)**: A successful request using the maximum **Order 
Value (10)**.

**Test Case 3 (Invalid)**: A failed request due to an invalid (too short)
**Branch Code**.

Each test case execution will clearly print the input data, 
the expected output, and the actual validation result 
(**SUCCESS** or **FAILURE**) with corresponding error messages.