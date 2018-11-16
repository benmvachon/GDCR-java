package ben.gdcr.game;

/**
 * Holds the state of the game board in a two dimensional array of
 * {@link Mark}s called <code>tiles</code> as well as the next player's turn.
 * A <code>null</code> value in the tile array represents an unmarked tile.
 */
public class Board {
  
  /**
   * A statically accessible utility model to represent a completely unmarked row.
   */
  public static Mark[] BLANK_ROW = new Mark[29];
  
  static {
    for (int i = 0; i < 29; i++) {
      BLANK_ROW[i] = null;
    }
  }
  
  /**
   * The marked state of a tile.
   * Either <code>WHITE</code>, <code>BLACK</code>, or <code>null</code>.
   */
  public static enum Mark {
    WHITE,
    BLACK,
  }
  
  /**
   * A column/row pair
   */
  public static class TileIndex {
    int row = 0;
    int column = 0;
    
    public TileIndex() {}
    
    public TileIndex(int row, int column) {
      this.row = row;
      this.column = column;
    }
    
    public TileIndex(String raw) {
      String[] parts = raw.split(" ");
      this.row = Integer.parseInt(parts[0]);
      this.column = Integer.parseInt(parts[1]);
    }
    
    public int getRow() {
      return row;
    }
    
    public void setRow(int row) {
      this.row = row;
    }
    
    public int getColumn() {
      return column;
    }
    
    public void setColumn(int column) {
      this.column = column;
    }
    
    @Override
    public String toString() {
      return this.row + " " + this.column;
    }
    
    @Override
    public boolean equals(Object other) {
      if (other == null && this != null) {
        return false;
      }
      if (!(other instanceof TileIndex)) {
        return false;
      }
      if (((TileIndex) other).row != this.row) {
        return false;
      }
      if (((TileIndex) other).column != this.column) {
        return false;
      }
      return true;
    }
  }
  
  Mark[][] tiles = new Mark[29][29];
  Mark turn = Mark.WHITE;
  
  /**
   * Initialize a blank row.
   */
  public Board() {
    for (int i = 0; i < 29; i++) {
      tiles[i] = BLANK_ROW.clone();
    }
  }
  
  public Board(Mark[][] tiles, Mark turn) {
    this.tiles = tiles;
    this.turn = turn;
  }
  
  public Board(String raw) {
    String[] rows = raw.split("\n");
    if ("b".equals(rows[0])) {
      this.turn = Mark.BLACK;
    }
    for (int rowIndex = 0; rowIndex < 29; rowIndex++) {
      String rawRow = rows[rowIndex+1];
      Mark[] row = new Mark[29];
      for (int columnIndex = 0; columnIndex < 29; columnIndex++) {
        switch (rawRow.charAt(columnIndex)) {
        case 'w':
          row[columnIndex] = Mark.WHITE;
          break;
        case 'b':
          row[columnIndex] = Mark.BLACK;
          break;
        default:
          row[columnIndex] = null;
          break;
        }
      }
      this.tiles[rowIndex] = row;
    }
  }
  
  public Mark[][] getTiles() {
    return tiles;
  }
  
  public void setTiles(Mark[][] tiles) {
    this.tiles = tiles;
  }
  
  public Mark getTurn() {
    return turn;
  }
  
  public void setTurn(Mark turn) {
    this.turn = turn;
  }
  
  /**
   * Apply the current player's mark to the provided tile index and then
   * increment the turn to the next player.
   * @param tile
   */
  public void markTile(TileIndex tile) {
    this.tiles[tile.row][tile.column] = turn;
    this.turn = Mark.WHITE.equals(turn) ? Mark.BLACK : Mark.WHITE;
  }
  
  /**
   * Apply the provided mark to the provided tile index in the underlying two
   * dimensional array representing this board.
   * @param tile
   * @param mark
   */
  public void markTile(TileIndex tile, Mark mark) {
    this.tiles[tile.row][tile.column] = mark;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(Mark.WHITE.equals(this.turn) ? "w" : "b");
    sb.append("\n");
    for (Mark[] row : this.tiles) {
      for (Mark mark : row) {
        String representation = "-";
        if (Mark.WHITE.equals(mark)) {
          representation = "w";
        } else if (Mark.BLACK.equals(mark)) {
          representation = "b";
        }
        sb.append(representation);
      }
      sb.append("\n");
    }
    return sb.substring(0, sb.length() - 1);
  }

}
