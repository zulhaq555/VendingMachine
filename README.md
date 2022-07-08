# VendingMachine
A simple vending machine console application where a user inputs money, selects an item, and recieves change.

This program utilizes the mvc design pattern, using spring for dependency injection.
The program also contains custom exception classes used to handle application specific errors.

How to use:
1. Start the app class
2. Enter you money (£)
3. Enter the item name from the options
4. If valid purchase your change will be displayed and the specific coins dispensed
5. All changes made to item stock are updated in the Stock txt file
6. All items purchased a logged in the Audit txt file
