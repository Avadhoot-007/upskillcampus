# Banking Information System

Banking Information System developed using Core Java as part of the USC + UCT Industrial Internship project. The system simulates core banking operations including account management, transactions, authentication, reporting, and file-based persistence.

## Features

### Authentication
- Admin Login
- Customer Login
- Active account validation

### Account Management
- Create account
- Update account
- Delete account
- Search account
- View account details
- Account status management

### Banking Operations
- Deposit money
- Withdraw money
- Balance inquiry
- Fund transfer

### Transaction Management
- Transaction history
- Mini statement
- Transaction ID generation
- Audit logging support

### Additional Features
- Savings interest application
- Account sorting
- Statement export support
- File handling support
- Interest state persistence

---

## Project Structure

```text
BankingInformationSystem.java
```

Internal Classes:

```text
BankingInformationSystem
Account
Transaction
Authentication
BankManager
```

---

## Technologies Used

- Java
- Core Java
- Object-Oriented Programming (OOP)
- Collections (ArrayList)
- File Handling
- Exception Handling
- Comparator Sorting

---

## Account Fields

```text
Account ID
Customer Name
Password
Account Type
Balance
Phone Number
Address
Status
```

Supported Account Types:

```text
Savings
Current
```

Supported Status:

```text
Active
Inactive
Blocked
```

---

## Default Admin Credentials

```text
Username: admin
Password: admin123
```

---

## System Flow

```text
Main Menu
   │
   ├── Admin Login
   │      ├── Account Management
   │      ├── Reports
   │      ├── Transactions
   │      ├── Sorting
   │      └── Interest Management
   │
   └── Customer Login
          ├── Deposit
          ├── Withdraw
          ├── Transfer
          ├── Balance Inquiry
          └── Statements
```

---

## How to Run

Compile:

```bash
javac BankingInformationSystem.java
```

Run:

```bash
java BankingInformationSystem
```

---

## Internship Details

**Program:** USC + The IoT Academy + UCT Industrial Internship  
**Track:** Summer Internship in Python  
**Project:** Banking Information System  

---

## Repository Files

```text
BankingInformationSystem.java
BankingInformationSystem_Arjun_USC_UCT.pdf
```

---

## Future Enhancements

- MySQL / JDBC integration
- GUI implementation
- Encryption and security enhancements
- Online banking support
- Advanced analytics and reports
- Enhanced persistence layer
