# Tic-Tac-Toe — LLD Practice

A two-player, console-oriented Tic-Tac-Toe implementation for practicing object-oriented design and low-level design (LLD).

## Requirements
- Two players take turns on a 3×3 board.
- Each player has a symbol (`X` or `O`).
- A move is allowed only for an in-bounds, empty cell.
- Detect wins across rows, columns, and both diagonals.
- Detect a draw when the board is full and nobody has won.
- Reject moves after a win or draw.
- Track the current player, game state, and winner.

## Out of scope
GUI, networking, persistence, AI opponents, matchmaking, undo/redo, and configurable board rules.

## Design

### `Player`
Represents a player with an ID, name, and assigned symbol.

### `Board`
Owns the cell matrix and board-related behavior:
- `placeSymbol(row, col, symbol)` validates bounds and cell availability.
- `hasWon(symbol)` checks rows, columns, and diagonals.
- `isFull()` checks whether any empty cells remain.

### `GameManager`
Coordinates the game:
- Keeps the two players and current player.
- Delegates move placement and board checks to `Board`.
- Updates `GameState` and records the winner.
- Switches turns only when the game continues.

### Enums
- `Symbol`: `X`, `O`, `EMPTY`
- `GameState` (named `VendingMachineState` in the initial implementation): `IN_PROGRESS`, `WIN`, `DRAW`

## Move flow
1. Reject the move if the game has already ended.
2. Ask the board to place the current player's symbol.
3. Reject invalid moves.
4. Check whether the current player has won.
5. If not, check whether the board is full and declare a draw if so.
6. Otherwise, switch the current player.

## Complexity
Let `n` be the board dimension.

| Operation | Time | Space |
|---|---:|---:|
| Initialize board | O(n²) | O(n²) total board storage |
| Place symbol | O(1) | O(1) |
| Check win | O(n²) | O(1) auxiliary |
| Check full | O(n²) | O(1) auxiliary |
| Play one move | O(n²) | O(1) auxiliary |

## SOLID discussion
- **SRP:** `Board` owns board state and board checks; `Player` models a player; `GameManager` coordinates the game.
- **OCP:** The solution is intentionally focused on fixed 3×3 rules. If multiple rule sets become a requirement, a `WinningStrategy` abstraction could be introduced.
- **LSP / ISP:** No inheritance hierarchy or interfaces are needed for the current scope.
- **DIP:** `GameManager` depends directly on `Board`, which is reasonable for this small design. Introduce abstractions only if there is a real need for interchangeable implementations.

## Design patterns
No pattern is forced into the current solution. Potential future extensions:
- **Strategy** for interchangeable winning rules.
- **Observer** to notify a UI or listeners about moves and game completion.
- **Command** for move history and undo/redo.

## Test scenarios
The manual test harness covers:
1. X wins by row.
2. O wins by row.
3. X wins by column.
4. O wins by column.
5. X wins by main diagonal.
6. O wins by opposite diagonal.
7. Draw.
8. Occupied-cell rejection.
9. Out-of-bounds rejection.
10. Reject moves after a win.
11. Winner tracking.
12. Current-player switching.

## Diagrams
- `tictactoe_class_diagram.png`
- `tictactoe_sequence_diagram.png`

## Potential improvements
- Rename `VendingMachineState` to `GameState` and `playSymbol()` to `placeSymbol()` for clarity.
- Make immutable fields `final`.
- Reject `null` and `Symbol.EMPTY` as move symbols.
- Replace manual console checks with JUnit tests if this becomes a maintained project.
- Add a restart/new-game API if restart behavior is required.
