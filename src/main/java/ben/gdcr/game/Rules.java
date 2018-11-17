package ben.gdcr.game;

import ben.gdcr.game.Board.Mark;
import ben.gdcr.game.Board.TileIndex;

/**
 * Utility class containing the rules according to which the game state
 * progresses.
 */
public class Rules {
  
  /**
   * Move the board from one state to the next according to the rules.
   * @param board
   * @return
   */
  public static Board apply(Board board) {
    Board clone = board.clone();
    for (int row = 0; row < 29; row ++) {
      for (int column = 0; column < 29; column ++) {
        // TODO this is definitely not optimal:
        // This cell will share three neighbors with the next...
        clone.getTiles()[row][column] = nextState(new TileIndex(row, column), board);
      }
    }
    return clone;
  }
  
  /**
   * Get the next state for the provided tile based on its current state and
   * the state of its neighbors.
   * @param tile
   * @param board
   * @return
   */
  public static Mark nextState(TileIndex tile, Board board) {
    Mark mark = board.getTiles()[tile.row][tile.column];
    
    int alive = 0;
    int white = 0;
    int black = 0;
    
    for (Mark neighbor : getNeighbors(tile, board)) {
      if (Mark.WHITE.equals(neighbor)) {
        white ++;
        alive ++;
      } else if (Mark.BLACK.equals(neighbor)) {
        black ++;
        alive ++;
      }
    }
    
    // The tile has exactly 3 living neighbors,
    // it will come to life
    if (mark == null && alive == 3) {
      return white > black ? Mark.WHITE : Mark.BLACK;
    } else if (mark != null) {
      if (alive < 2 || alive > 3) {
        return null;
      }
    }
    
    return mark;
  }
  
  /**
   * The neighbors of a tile are the tiles at its immediate top, bottom, left,
   * right, and corners.
   * This utility method returns the marks (either 'b', 'w', or '-') contained in these neighbors.
   * @param tile
   * @param board
   * @return
   */
  public static Mark[] getNeighbors(TileIndex tile, Board board) {
    Mark[] marks = new Mark[8];
    TileIndex[] neighbors = tile.getNeighbors();
    for (int i = 0; i < 8; i++) {
      marks[i] = null;
      TileIndex neighbor = neighbors[i];
      if (isOnBoard(neighbor)) {
        marks[i] = board.getTiles()[neighbor.row][neighbor.column];
      }
    }
    return marks;
  }
  
  /**
   * Return true if the tile is not off the board e.g. (-1, -1)
   * @param tile
   */
  public static boolean isOnBoard(TileIndex tile) {
    if (
      tile.row < 0 ||
      tile.row > 28 ||
      tile.column < 0 ||
      tile.column > 28
    ) {
      return false;
    }
    return true;
  }
}
