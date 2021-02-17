package za.co.entelect.challenge;

import za.co.entelect.challenge.command.*;
import za.co.entelect.challenge.entities.*;
import za.co.entelect.challenge.enums.CellType;
import za.co.entelect.challenge.enums.Direction;

import java.util.*;
import java.util.stream.Collectors;

public class Bot {

    private Random random;
    private GameState gameState;
    private Opponent opponent;
    private MyWorm currentWorm;

    public Bot(Random random, GameState gameState) {
        this.random = random;
        this.gameState = gameState;
        this.opponent = gameState.opponents[0];
        this.currentWorm = getCurrentWorm(gameState);
    }

    private MyWorm getCurrentWorm(GameState gameState) {
        return Arrays.stream(gameState.myPlayer.worms)
                .filter(myWorm -> myWorm.id == gameState.currentWormId)
                .findFirst()
                .get();
    }

    public Command run() {
        // Assign Leader Worm
        MyWorm leaderWorm = setLeaderWorm();
        if (currentWorm == leaderWorm) {
            // currentWorm is the Leader
            if (isInMiddle()) {
                // If in middle then check for nearby enemy
                Worm enemyWorm = getFirstWormInRange();
                if (enemyWorm != null) {
                    Direction direction = resolveDirection(currentWorm.position, enemyWorm.position);
                    return new ShootCommand(direction);
                }
                // Else random move
                List<Cell> surroundingBlocks = getSurroundingCells(currentWorm.position.x, currentWorm.position.y);
                int cellIdx = random.nextInt(surroundingBlocks.size());
                Cell block = surroundingBlocks.get(cellIdx);
                if (block.type == CellType.AIR) {
                    return new MoveCommand(block.x, block.y);
                } else if (block.type == CellType.DIRT) {
                    return new DigCommand(block.x, block.y);
                }
            }
            else {
                // Not in range middle of the map
                if (isLavaNear()) {
                    // If near lava, move to middle
                    List<Cell> surroundingBlocks = getSurroundingCells(currentWorm.position.x, currentWorm.position.y);
                    int cellIdx = cellDirection(surroundingBlocks,16,16);
                    Cell block = surroundingBlocks.get(cellIdx);
                    if (block.type == CellType.AIR) {
                        return new MoveCommand(block.x, block.y);
                    }   else if (block.type == CellType.DIRT) {
                        return new DigCommand(block.x, block.y);
                    }
                }
                else {
                    // Not near, check for nearby enemy
                    Worm enemyWorm = getFirstWormInRange();
                    if (enemyWorm != null) {
                        Direction direction = resolveDirection(currentWorm.position, enemyWorm.position);
                        return new ShootCommand(direction);
                    }
                    // Else random move
                    List<Cell> surroundingBlocks = getSurroundingCells(currentWorm.position.x, currentWorm.position.y);
                    int cellIdx = random.nextInt(surroundingBlocks.size());
                    Cell block = surroundingBlocks.get(cellIdx);
                    if (block.type == CellType.AIR) {
                        return new MoveCommand(block.x, block.y);
                    } else if (block.type == CellType.DIRT) {
                        return new DigCommand(block.x, block.y);
                    }
                }
            }
        }
        else {
            // currentWorm is not the Leader
            if (isLeaderNear(leaderWorm)) {
                if (isLavaNear()) {
                    // If near lava, move to middle
                    List<Cell> surroundingBlocks = getSurroundingCells(currentWorm.position.x, currentWorm.position.y);
                    int cellIdx = cellDirection(surroundingBlocks,16,16);
                    Cell block = surroundingBlocks.get(cellIdx);
                    if (block.type == CellType.AIR) {
                        return new MoveCommand(block.x, block.y);
                    }   else if (block.type == CellType.DIRT) {
                        return new DigCommand(block.x, block.y);
                    }
                }
                else {
                    // Not near, check for nearby enemy
                    Worm enemyWorm = getFirstWormInRange();
                    if (enemyWorm != null) {
                        Direction direction = resolveDirection(currentWorm.position, enemyWorm.position);
                        return new ShootCommand(direction);
                    }
                    // Else random move
                    List<Cell> surroundingBlocks = getSurroundingCells(currentWorm.position.x, currentWorm.position.y);
                    int cellIdx = random.nextInt(surroundingBlocks.size());
                    Cell block = surroundingBlocks.get(cellIdx);
                    if (block.type == CellType.AIR) {
                        return new MoveCommand(block.x, block.y);
                    } else if (block.type == CellType.DIRT) {
                        return new DigCommand(block.x, block.y);
                    }
                }
            }
            else {
                // Not near Leader, move to Leader
                List<Cell> surroundingBlocks = getSurroundingCells(currentWorm.position.x, currentWorm.position.y);
                int cellIdx = cellDirection(surroundingBlocks,leaderWorm.position.x,leaderWorm.position.y);
                Cell block = surroundingBlocks.get(cellIdx);
                if (block.type == CellType.AIR) {
                    return new MoveCommand(block.x, block.y);
                }   else if (block.type == CellType.DIRT) {
                    return new DigCommand(block.x, block.y);
                }
            }
        }
        return new DoNothingCommand();
    }

    private MyWorm setLeaderWorm() {
        int min_health = 100;
        for (Worm myWorm: gameState.myPlayer.worms) {
            if (myWorm.health < min_health) {
                min_health = myWorm.health;
            }
        }

        if (min_health == 100) {
            // Set Commmando for Leader
            return Arrays.stream(gameState.myPlayer.worms)
                    .filter(myWorm -> myWorm.id == 1)
                    .findFirst()
                    .get();
        }
        else {
            // Set Worm with lowest health for Leader
            int finalMin_health = min_health;
            return Arrays.stream(gameState.myPlayer.worms)
                    .filter(myWorm -> myWorm.health == finalMin_health)
                    .findFirst()
                    .get();
        }
    }

    /*
    private boolean isTeammateNear() {
        List<Cell> surroundingCells = getSurroundingCells(3,currentWorm.position.x,currentWorm.position.y);
        boolean teammateNear = false;
        for (Worm myWorm: gameState.myPlayer.worms) {
            teammateNear = teammateNear ||
                    surroundingCells.contains(gameState.map[myWorm.position.y][myWorm.position.x]);
        }
        return teammateNear;
    }
    */

    private boolean isLeaderNear(MyWorm LeaderWorm) {
        // Check if Leader Worm in range 3x3 of currentWorm position
        // Preconditions currentWorm is not the LeaderWorm
        List<Cell> surroundingCells = getSurroundingCells(3,currentWorm.position.x,currentWorm.position.y);
        return surroundingCells.contains(gameState.map[LeaderWorm.position.y][LeaderWorm.position.x]);
    }

    private boolean isInMiddle() {
        List<Cell> middleBlocks = getSurroundingCells(3, 16, 16);
        return middleBlocks.contains(gameState.map[currentWorm.position.y][currentWorm.position.x]);
    }

    private boolean isLavaNear() {
        List<Cell> surroundingBlocks = getSurroundingCells(3, currentWorm.position.x, currentWorm.position.y);
        for (Cell block : surroundingBlocks) {
            if (block.type == CellType.LAVA) {
                return true;
            }
        }
        return false;
    }

    private Worm getFirstWormInRange() {

        Set<String> cells = constructFireDirectionLines(currentWorm.weapon.range)
                .stream()
                .flatMap(Collection::stream)
                .map(cell -> String.format("%d_%d", cell.x, cell.y))
                .collect(Collectors.toSet());

        for (Worm enemyWorm : opponent.worms) {
            String enemyPosition = String.format("%d_%d", enemyWorm.position.x, enemyWorm.position.y);
            if (cells.contains(enemyPosition)) {
                return enemyWorm;
            }
        }

        return null;
    }

    private List<List<Cell>> constructFireDirectionLines(int range) {
        List<List<Cell>> directionLines = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            List<Cell> directionLine = new ArrayList<>();
            for (int directionMultiplier = 1; directionMultiplier <= range; directionMultiplier++) {

                int coordinateX = currentWorm.position.x + (directionMultiplier * direction.x);
                int coordinateY = currentWorm.position.y + (directionMultiplier * direction.y);

                if (!isValidCoordinate(coordinateX, coordinateY)) {
                    break;
                }

                if (euclideanDistance(currentWorm.position.x, currentWorm.position.y, coordinateX, coordinateY) > range) {
                    break;
                }

                Cell cell = gameState.map[coordinateY][coordinateX];
                if (cell.type != CellType.AIR) {
                    break;
                }

                directionLine.add(cell);
            }
            directionLines.add(directionLine);
        }

        return directionLines;
    }

    private List<Cell> getSurroundingCells(int x, int y) {
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                // Don't include the current position
                if (i != x && j != y && isValidCoordinate(i, j)) {
                    cells.add(gameState.map[j][i]);
                }
            }
        }

        return cells;
    }

    private List<Cell> getSurroundingCells(int k, int x, int y) {
        // Get Surrounding Cells in kx,Ky
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = x - k; i <= x + k; i++) {
            for (int j = y - k; j <= y + k; j++) {
                // Don't include the current position
                if (i != x && j != y && isValidCoordinate(i, j)) {
                    cells.add(gameState.map[j][i]);
                }
            }
        }

        return cells;
    }

    private int euclideanDistance(int aX, int aY, int bX, int bY) {
        return (int) (Math.sqrt(Math.pow(aX - bX, 2) + Math.pow(aY - bY, 2)));
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < gameState.mapSize
                && y >= 0 && y < gameState.mapSize;
    }

    private int cellDirection(List<Cell> surroundingBlocks, int target_x, int target_y) {
        // Return idx of surrounding Blocks where closest to target coordinate
        int distance = euclideanDistance(surroundingBlocks.get(0).x, surroundingBlocks.get(0).y, target_x, target_y);
        int retIdx = 0;
        for (int idx = 1; idx < surroundingBlocks.size(); idx++) {
            int distance2 = euclideanDistance(surroundingBlocks.get(idx).x, surroundingBlocks.get(idx).y, target_x, target_y);
            if (distance2 < distance) {
                distance = distance2;
                retIdx = idx;
            }
        }
        return retIdx;
    }

    private Direction resolveDirection(Position a, Position b) {
        StringBuilder builder = new StringBuilder();

        int verticalComponent = b.y - a.y;
        int horizontalComponent = b.x - a.x;

        if (verticalComponent < 0) {
            builder.append('N');
        } else if (verticalComponent > 0) {
            builder.append('S');
        }

        if (horizontalComponent < 0) {
            builder.append('W');
        } else if (horizontalComponent > 0) {
            builder.append('E');
        }

        return Direction.valueOf(builder.toString());
    }
}
