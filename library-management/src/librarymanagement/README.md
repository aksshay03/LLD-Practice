# Library Management System — LLD

A low-level design implementation of a simple Library Management System in Java.

## Requirements

- Manage multiple books.
- Each book has ISBN, title, author and category.
- A logical book can have multiple physical copies.
- Manage multiple library members.
- Search books by title.
- Members can borrow available physical copies.
- A physical copy cannot be borrowed when it is already borrowed.
- Track which member currently has a physical copy.
- Members can borrow multiple books, subject to a borrowing limit.
- Track due dates.
- Calculate a fine for late returns.
- Preserve borrowing history.

## Out of Scope

- Database persistence
- Authentication/authorization
- Multiple library branches
- Reservations/waitlists
- Notifications
- Online payment gateway

## Architecture

### Main Classes

- **Library** — Coordinates the main use cases: member/book registration, search, borrow and return.
- **Book** — Represents a logical book and owns its physical copies.
- **BookCopy** — Represents an individual physical copy and its availability state.
- **Member** — Represents a library member.
- **BorrowRecord** — Represents a borrowing transaction between a member and a physical copy.
- **FineCalculator** — Calculates late-return fines.

### Enums

- **BookCategory** — SCIENCE, PHYSICS, HISTORY, PROGRAMMING, FICTION.
- **CopyStatus** — AVAILABLE, BORROWED.

## Important Design Decision: Book vs BookCopy

A `Book` is a logical title, while a `BookCopy` represents a physical copy.

Example:

```text
Clean Code
├── C001 → BORROWED
├── C002 → AVAILABLE
└── C003 → AVAILABLE
```

This allows multiple copies of the same book to be borrowed independently.

## Borrowing Flow

```text
Member
  ↓
Library.borrowBook(memberId, isbn)
  ↓
Validate member
  ↓
Check borrowing limit
  ↓
Find Book by ISBN
  ↓
Find available BookCopy
  ↓
Mark BookCopy as BORROWED
  ↓
Create BorrowRecord
  ↓
Store in borrowRecords
  ↓
Store in activeBorrowRecords
```

## Return Flow

```text
Library.returnBook(borrowId)
  ↓
Find BorrowRecord
  ↓
Set return date
  ↓
FineCalculator.calculateFine()
  ↓
Store fine in BorrowRecord
  ↓
Mark BookCopy AVAILABLE
  ↓
Remove record from activeBorrowRecords
```

## Why Two Borrow Record Maps?

`borrowRecords` stores the complete borrowing history.

`activeBorrowRecords` stores only currently active borrowings.

When a book is returned:

```text
borrowRecords           → record remains
activeBorrowRecords     → record is removed
```

This preserves history while making active-borrow operations simpler.

## SOLID / OOP Concepts Used

### Encapsulation
Each class owns its state and exposes controlled operations.

### Single Responsibility
- `Book` manages book/copy relationships.
- `BookCopy` manages physical-copy state.
- `BorrowRecord` represents a borrowing transaction.
- `FineCalculator` calculates fines.
- `Library` coordinates use cases.

### Dependency Injection
`FineCalculator` is supplied to `Library` through the constructor instead of being created inside `Library`.

### Composition
A `Book` has multiple `BookCopy` objects.

## Design Patterns

No design pattern is forced into the current implementation. The problem can be solved cleanly using OOP, composition and dependency injection.

Potential future extensions:

- **Strategy Pattern** for different search algorithms.
- **Strategy Pattern** for different fine policies.
- **Observer Pattern** for due-date/return notifications.
- **Factory Pattern** if creation rules for different book/member types become complex.

## Complexity

Let:

- `B` = number of books
- `C` = number of copies of a particular book
- `A` = number of active borrow records

| Operation | Complexity |
|---|---|
| Add member | Average O(1) |
| Add book | Average O(1) |
| Search by title | O(B) |
| Find book by ISBN | Average O(1) |
| Find available copy | O(C) |
| Check active borrow count | O(A) |
| Borrow | O(A + C) |
| Return by borrow ID | Average O(1) |
| Fine calculation | O(1) |

## Diagrams

- `library_class_diagram.png` — Class relationships and responsibilities.
- `library_borrow_sequence_diagram.png` — Borrow-book interaction flow.

## Test Scenarios

The implementation was tested for:

1. Searching for a book.
2. Borrowing the first physical copy.
3. Borrowing a second physical copy of the same book.
4. Rejecting a third borrow when no physical copy is available.
5. Returning a borrowed copy.
6. Making the returned copy available again.
7. Allowing another member to borrow the returned copy.
8. Calculating and storing fines on return.

## Future Improvements

For a production system, the design could be extended with:

- Database persistence and transactions.
- Concurrency control for simultaneous borrowing.
- Search by author, ISBN and category.
- Reservation/waitlist support.
- Different borrowing limits by member type.
- Different loan periods/fine policies.
- Notification service.
- Payment processing for fines.
