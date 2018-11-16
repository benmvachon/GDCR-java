package ben.gdcr.game.test;

import org.junit.Assert;
import org.junit.Test;

import ben.gdcr.game.Board;
import ben.gdcr.game.Board.Mark;
import ben.gdcr.game.Board.TileIndex;
import ben.gdcr.game.Solver;
import ben.gdcr.game.test.util.TestBoards;

public class SolverTest {
  
  @Test
  public void testBoardFromString() {
    Board board = TestBoards.BOARD_TWO;
    Assert.assertEquals("The board was read incorrectly", Mark.BLACK, board.getTurn());
    Assert.assertEquals("The board was read incorrectly", Mark.WHITE, board.getTiles()[1][2]);
    Assert.assertEquals("The board was read incorrectly", Mark.WHITE, board.getTiles()[6][8]);
    Assert.assertEquals("The board was read incorrectly", Mark.BLACK, board.getTiles()[21][18]);
    Assert.assertEquals("The board was read incorrectly", TestBoards.RAW_BOARD_TWO, board.toString());
  }
  
  @Test
  public void testSolver() {
    Board board = TestBoards.BOARD_ONE;
    TileIndex tile = Solver.chooseNextMove(board);
    Assert.assertEquals("The solver chose the wrong tile.", new TileIndex(0, 0), tile);
  }
}
