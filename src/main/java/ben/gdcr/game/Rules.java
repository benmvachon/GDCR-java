package ben.gdcr.game;

import ben.gdcr.game.Board.Mark;
import ben.gdcr.game.Board.TileIndex;

public class Rules {
  
  public static Board apply(Board board) {
    // TODO apply all the rules and follow the board to it's natural end state
    return board;
  }
  
  /**
   * A tile will die due to either underpopulation (not enough living
   * neighbors), or overpopulation (too many living neighbors).
   * @param tile
   * @param board
   * @return
   */
  public static boolean willDie(TileIndex tile, Board board) {
    // TODO return the value
    return false;
  }
  
  /**
   * A tile will turn white only if:<br>
   * (1) exactly three of its neighbors are alive <br>
   * (2) exactly two of its neighbors are white
   * @param tile
   * @param board
   * @return
   */
  public static boolean willTurnWhite(TileIndex tile, Board board) {
    // TODO return the value
    return false;
  }
  
  /**
   * A tile will turn white only if:<br>
   * (1) exactly three of its neighbors are alive <br>
   * (2) exactly two of its neighbors are black
   * @param tile
   * @param board
   * @return
   */
  public static boolean willTurnBlack(TileIndex tile, Board board) {
    // TODO return the value
    return false;
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
    // TODO return marks filling the (maximum) eight neighbors of the tile
    return new Mark[8];
  }
}
