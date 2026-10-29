# Snake & Ladder — Low-Level Design

## Requirements
- Board positions are numbered 1–100.
- Two or more players take turns rolling a six-sided die.
- Players move forward by the dice result.
- Landing on a snake's head moves the player down to its tail.
- Landing on a ladder's bottom moves the player up to its top.
- A player wins only by reaching position 100 exactly.
- A move beyond 100 leaves the player at the current position.
- The game ends as soon as a player wins.
- A new game uses fresh player objects/positions.

## Out of scope
GUI, online multiplayer, AI, persistence, multiple dice, custom board sizes, and extra turns on a six.

## Classes and responsibilities
- **Player** — identity and current position.
- **Board** — board size, jump configuration, overshoot handling, and jump resolution.
- **BoardJump** — starting and ending positions for a snake or ladder.
- **DiceRoller** — interface abstracting dice rolls.
- **Dice** — random production implementation.
- **FixedDice** — deterministic implementation for tests.
- **GameManager** — coordinates turns, moves, winner detection, and game state.

## Design decisions
### DiceRoller
`GameManager` depends on `DiceRoller`, not the concrete `Dice`. Production uses random rolls; tests inject `FixedDice` to make scenarios repeatable. This demonstrates Dependency Inversion and improves testability.

### Map for jumps
Jumps are stored in `Map<Integer, BoardJump>`, keyed by starting position. A `HashMap` provides average O(1) lookup, compared with O(J) for scanning a list of J jumps.

### Single-jump rule
For this version, keep jump resolution unambiguous:
- A snake must go downward (`start > end`).
- A ladder must go upward (`start < end`).
- Start/end positions must be within the board.
- Starting positions must be unique.
- A jump destination cannot be another jump's starting position.

**Implementation note:** Add explicit direction and chained-jump validation to `Board.addJump()` and tests for those rules before treating these configuration constraints as implemented. The earlier tests passed for the behavior already implemented, not for these proposed additional validations.

## Complexity
Let J be the number of jumps and P the number of players:
- Jump lookup per move: average O(1).
- Resolving a move: average O(1).
- Selecting/rotating the current player: O(1).
- Jump configuration storage: O(J).
- Player list storage: O(P).
Total game runtime depends on the dice sequence and number of turns.

## Tests
Existing plain Java tests cover snake/ladder resolution, normal movement, overshooting, duplicate jump starts, invalid board size, winner detection, no turns after game over, and turn rotation.
Recommended additions: invalid dice results; invalid jump direction; out-of-range jump endpoints; and chained-jump configuration.

## Running
Run `Main` for a random game, and run `BoardTest` and `GameManagerTest` for the plain Java test harnesses using your IDE or your project's package structure.

## Diagrams
- `snake_ladder_class_diagram.png`
- `snake_ladder_sequence_diagram.png`
