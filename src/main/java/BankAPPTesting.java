import java.util.Scanner;

/**
 * BankAPPTesting.java
 * ----------------------------------------------------------------------
 * Description:
 * This Java script simulates the input validation process for a banking
 * application's electronic operation request. It implements the black-box
 * equivalence class logic derived in Sprint 1 and 2 to validate user
 * input for the Bank Code, Branch Code, Account Number, Personal Key,
 * and Order Value.
 *
 * It includes an automated test runner that executes three representative
 * test cases (Valid, Boundary, Invalid) to demonstrate the validation logic.
 *
 */

public class BankAPPTesting {
    // =========================================================================
    // 1. INPUT VALIDATION METHODS (Based on Equivalence Classes)
    // =========================================================================

    /**
     * Validates if a string input is a numeric value of a specific length.
     * @param input The string to validate.
     * @param requiredLength The expected length.
     * @return true if valid, false otherwise.
     */
    private static boolean validateFixedLengthNumeric(String input, int requiredLength) {
        // E.C. (I-XX-03): Checks for non-numeric characters
        if (input == null || !input.matches("\\d+")) {
            return false;
        }
        // E.C. (I-XX-01, I-XX-02): Checks for length
        return input.length() == requiredLength;
    }

    /**
     * Validates the Order Value input against the defined range.
     * @param input The string order value to validate.
     * @return true if valid (integer 1-10), false otherwise.
     */
    private static boolean validateOrderValue(String input) {
        if (input == null || !input.matches("\\d+")) {
            return false; // E.C. (I-OV-03): Non-integer/Non-numeric input
        }
        try {
            int value = Integer.parseInt(input);
            // E.C. (V-OV-01, I-OV-01, I-OV-02): Checks for range 1 to 10
            return value >= 1 && value <= 10;
        } catch (NumberFormatException e) {
            return false; // Should be caught by the regex, but safe to include
        }
    }

    /**
     * Main validation function that processes the banking operation request.
     * @param bankCode 3-digit number
     * @param branchCode 4-digit number
     * @param accountNum 10-digit number
     * @param personalKey 4-digit number
     * @param orderValue Integer 1-10
     * @param orderType "Checkbook" or "Statement"
     * @return true if all inputs are valid, false otherwise.
     */
    public static boolean processRequest(String bankCode, String branchCode, String accountNum,
                                         String personalKey, String orderValue, String orderType) {

        System.out.println("\n--- Processing Request: " + orderType + " (Value: " + orderValue + ") ---");
        boolean isValid = true;

        if (!validateFixedLengthNumeric(bankCode, 3)) {
            System.err.println("[ERROR] Invalid Bank Code: Must be a 3-digit number. (E.C. I-BC)");
            isValid = false;
        }
        if (!validateFixedLengthNumeric(branchCode, 4)) {
            System.err.println("[ERROR] Invalid Branch Code: Must be a 4-digit number. (E.C. I-BRC)");
            isValid = false;
        }
        if (!validateFixedLengthNumeric(accountNum, 10)) {
            System.err.println("[ERROR] Invalid Account Number: Must be a 10-digit number. (E.C. I-AN)");
            isValid = false;
        }
        if (!validateFixedLengthNumeric(personalKey, 4)) {
            System.err.println("[ERROR] Invalid Personal Key: Must be a 4-digit number. (E.C. I-PK)");
            isValid = false;
        }
        if (!validateOrderValue(orderValue)) {
            System.err.println("[ERROR] Invalid Order Value: Must be an integer between 1 and 10. (E.C. I-OV)");
            isValid = false;
        }
        // E.C. (V-OT-01, V-OT-02, I-OT-01): Order type validation
        if (!orderType.equals("Checkbook") && !orderType.equals("Statement")) {
            System.err.println("[ERROR] Invalid Order Type: Must be 'Checkbook' or 'Statement'.");
            isValid = false;
        }

        if (isValid) {
            System.out.println("[SUCCESS] Application State: Request Pending.");
            System.out.println("Operation confirmed: " + orderValue + " " + orderType + "(s) requested.");
            return true;
        } else {
            System.out.println("[FAILURE] Application State: Error Message Displayed. Request Rejected.");
            return false;
        }
    }

    // =========================================================================
    // 2. MAIN EXECUTION WITH TEST CASES
    // =========================================================================

    public static void main(String[] args) {
        // NOTE: This simulation uses hardcoded inputs for demonstration,
        // but it could easily be adapted to use a Scanner for user input.

        System.out.println("--- Banking Application Input Simulation (Validation based on E.C.) ---");

        // Example 1: VALID Test Case (Representative: TC-001)
        System.out.println("\n=============================================");
        System.out.println("TEST CASE 1 (VALID: TC-001)");
        System.out.println("Expected Output: SUCCESS. Request Submitted.");
        System.out.println("=============================================");
        processRequest(
                "550",          // Bank Code (V-BC-01)
                "2045",         // Branch Code (V-BRC-01)
                "1234567890",   // Account Num (V-AN-01)
                "1234",         // Personal Key (V-PK-01)
                "3",            // Order Value (V-OV-01)
                "Checkbook"     // Order Type (V-OT-01)
        );

        // Example 2: BOUNDARY Test Case (Representative: TC-003)
        System.out.println("\n=============================================");
        System.out.println("TEST CASE 2 (BOUNDARY: TC-003 - Max Order Value)");
        System.out.println("Expected Output: SUCCESS. Request Submitted.");
        System.out.println("=============================================");
        processRequest(
                "550",
                "2045",
                "1234567890",
                "1234",
                "10",           // Order Value (V-OV-01 - Max Boundary)
                "Checkbook"
        );

        // Example 3: INVALID Test Case (Representative: TC-004 - Invalid Branch Code)
        System.out.println("\n=============================================");
        System.out.println("TEST CASE 3 (INVALID: TC-004 - Branch Code Too Short)");
        System.out.println("Expected Output: FAILURE. Invalid Branch Code error message.");
        System.out.println("=============================================");
        processRequest(
                "550",
                "999",          // Branch Code (I-BRC-01 - Too Short)
                "1234567890",
                "1234",
                "3",
                "Checkbook"
        );
    }
}
