enum ColorVariation {
  BLACK(1), WHITE(0);
  private final int value;
  ColorVariation(int value) { this.value = value; }
  public int getValue() { return value; }
}

interface ChessPieceVariation {
  int getRow();
  int getCol();
  Color getColor();
  boolean canMove(int row, int col);
  boolean canKill(ChessPieceVariation piece);
}

abstract class AbstractChessPieceVariation implements ChessPieceVariation {
  private int row, col;
  private Color color;

  public AbstractChessPieceVariation(int row, int col, Color color) {
    if (row > 7 || row < 0 || col > 7 || col < 0) {
      throw new IllegalArgumentException("ChessPiece out of bounds");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  public AbstractChessPieceVariation(AbstractChessPieceVariation original) {
    this(original.row, original.col, original.color);
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getCol() {
    return this.col;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public abstract boolean canMove(int row, int col);

  @Override
  public abstract boolean canKill(ChessPieceVariation piece);
}

class BishopVariation extends AbstractChessPieceVariation {
  public BishopVariation(int row, int col, Color color) {
    super(row, col, color);
  }

  public BishopVariation(BishopVariation original) {
    this(original.getRow(), original.getCol(), original.getColor());
  }

  @Override
  public boolean canMove(int row, int col) {
    if (row < 0 || row > 7 || col < 0 || col > 7
      || row == this.getRow() || col == this.getCol()){
      return false;
    }
    int rowDiff = Math.abs(row - this.getRow());
    int colDiff =Math.abs(col - this.getCol());
    return (rowDiff == colDiff);
  }

  @Override
  public boolean canKill(ChessPieceVariation piece) {
    return (piece.getColor() != this.getColor()
      && this.canMove(piece.getRow(), piece.getCol()));
  }
}

class KingVariation extends AbstractChessPieceVariation {
  private static final int VALID_KING_1 = 1;
  private static final int VALID_KING_0 = 0;

  public KingVariation(int row, int col, Color color) {
    super(row, col, color);
  }

  public KingVariation(KingVariation original) {
    this(original.getRow(), original.getCol(), original.getColor());
  }

  @Override
  public boolean canMove(int row, int col) {
    if (row < 0 || row > 7 || col < 0 || col > 7
      || (row == this.getRow() && col == this.getCol())){
      return false;
    }

    int rowDiff = Math.abs(row-this.getRow());
    int colDiff = Math.abs(col-this.getCol());

    return (rowDiff == VALID_KING_1 && colDiff == VALID_KING_1)
      || (rowDiff == VALID_KING_1 && colDiff == VALID_KING_0)
      || (rowDiff == VALID_KING_0 && colDiff == VALID_KING_1);
  }

  @Override
  public boolean canKill(ChessPieceVariation piece) {
    return (piece.getColor() != this.getColor()
      && this.canMove(piece.getRow(), piece.getCol()));
  }
}

class KnightVariation extends AbstractChessPieceVariation {
  private final static int VALID_COL_DIFF_2 = 2;
  private final static int VALID_COL_DIFF_1 = 1;
  private final static int VALID_ROW_DIFF_2 = 2;
  private final static int VALID_ROW_DIFF_1 = 1;

  public KnightVariation(int row, int col, Color color) {
    super(row, col, color);
  }

  public KnightVariation(KnightVariation original) {
    this(original.getRow(), original.getCol(), original.getColor());
  }

  @Override
  public boolean canMove(int row, int col) {
    if (row < 0 || row > 7 || col < 0 || col > 7
      || row == this.getRow() || col == this.getCol()){
      return false;
    }

    int rowDiff = Math.abs(row-this.getRow());
    int colDiff = Math.abs(col-this.getCol());

    return ((rowDiff == VALID_ROW_DIFF_2 && colDiff == VALID_COL_DIFF_1)
      || (rowDiff == VALID_ROW_DIFF_1 && colDiff == VALID_COL_DIFF_2));
  }

  @Override
  public boolean canKill(ChessPieceVariation piece) {
    return (piece.getColor() != this.getColor()
      && this.canMove(piece.getRow(), piece.getCol()));
  }
}

class PawnVariation extends AbstractChessPieceVariation {
  private static final int BLACK_START_ROW = 6;
  private static final int WHITE_START_ROW = 1;
  private static final int VALID_BLACK_ROW_DIFF_1 = -1;
  private static final int VALID_BLACK_ROW_DIFF_2 = -2;
  private static final int VALID_WHITE_ROW_DIFF_1 = 1;
  private static final int VALID_WHITE_ROW_DIFF_2 = 2;
  private static final int VALID_COL_DIFF = 0;
  private static final int VALID_COL_KILL_DIFF = 1;

  public PawnVariation(int row, int col, Color color) {
    super(row, col, color);
    if (color == Color.WHITE && row != 1) {
      throw new IllegalArgumentException("Set white pawns at the wrong row!");
    } else if (color == Color.BLACK && row != 6) {
      throw new IllegalArgumentException("Set black pawns at the wrong row!");
    }
  }

  public PawnVariation(PawnVariation original) {
    this(original.getRow(), original.getCol(), original.getColor());
  }

  @Override
  public boolean canMove(int row, int col) {
    if (row < 0 || row > 7 || col < 0 || col > 7
      || (row == this.getRow() && col == this.getCol())) {
      return false;
    }

    int rowDiff = row-this.getRow();
    int colDiff = col-this.getCol();

    if (this.getColor() == Color.BLACK) {
      if (this.getRow() == BLACK_START_ROW) {
        return (rowDiff == VALID_BLACK_ROW_DIFF_1 && colDiff == VALID_COL_DIFF)
          || (rowDiff == VALID_BLACK_ROW_DIFF_2 && colDiff == VALID_COL_DIFF);
      } else {
        return rowDiff == VALID_BLACK_ROW_DIFF_1 && colDiff == VALID_COL_DIFF;
      }
    }

    if (this.getColor() == Color.WHITE) {
      if (this.getRow() == WHITE_START_ROW) {
        return (rowDiff == VALID_WHITE_ROW_DIFF_1 && colDiff == VALID_COL_DIFF)
          || (rowDiff == VALID_WHITE_ROW_DIFF_2 && colDiff == VALID_COL_DIFF);
      } else {
        return rowDiff == VALID_WHITE_ROW_DIFF_1 && colDiff == VALID_COL_DIFF;
      }
    }
    return false;
  }

  @Override
  public boolean canKill(ChessPieceVariation piece) {
    if (this.getColor() == piece.getColor()
      || piece.getRow() > 7 || piece.getRow() < 0 || piece.getCol() > 7 || piece.getCol() < 0) {
      return false;
    }

    int rowDiff = piece.getRow()-this.getRow();
    int colDiff = Math.abs(piece.getCol()-this.getCol());

    if (this.getColor() == Color.BLACK) {
      return rowDiff == VALID_BLACK_ROW_DIFF_1 && colDiff == VALID_COL_KILL_DIFF;
    } else {
      return rowDiff == VALID_WHITE_ROW_DIFF_1 && colDiff == VALID_COL_KILL_DIFF;
    }
  }

  protected boolean hasMoved() {
    if (this.getColor() == Color.BLACK && this.getRow() == BLACK_START_ROW) {
      return false;
    }
    if (this.getColor() == Color.WHITE && this.getRow() == WHITE_START_ROW) {
      return false;
    }
    return true;
  }
}

class QueenVariation extends AbstractChessPieceVariation {
  public QueenVariation(int row, int col, Color color) {
    super(row, col, color);
  }

  public QueenVariation(QueenVariation original) {
    this(original.getRow(), original.getCol(), original.getColor());
  }

  @Override
  public boolean canMove(int row, int col) {
    if (row < 0 || row > 7 || col < 0 || col > 7
      || (row == this.getRow() && col == this.getCol())){
      return false;
    }

    int rowDiff = Math.abs(row-this.getRow());
    int colDiff = Math.abs(col-this.getCol());
    return (rowDiff == colDiff || rowDiff == 0 || colDiff == 0);
  }

  @Override
  public boolean canKill(ChessPieceVariation piece) {
    return (piece.getColor() != this.getColor()
      && this.canMove(piece.getRow(), piece.getCol()));
  }
}

class RookVariation extends AbstractChessPieceVariation {
  private static final int VALID_ROOK_0 = 0;

  public RookVariation(int row, int col, Color color) {
    super(row, col, color);
  }

  public RookVariation(RookVariation original) {
    this(original.getRow(), original.getCol(), original.getColor());
  }

  @Override
  public boolean canMove(int row, int col) {
    if (row < 0 || row > 7 || col < 0 || col > 7
      || row == this.getRow() || col == this.getCol()){
      return false;
    }

    int rowDiff = Math.abs(row-this.getRow());
    int colDiff = Math.abs(col-this.getCol());
    return (rowDiff == VALID_ROOK_0 || colDiff == VALID_ROOK_0);
  }

  @Override
  public boolean canKill(ChessPieceVariation piece) {
    return (piece.getColor() != this.getColor()
      && this.canMove(piece.getRow(), piece.getCol()));
  }
}

public class ACDIGE2 {
  public static void main(String[] args) {
    BishopVariation whiteB = new BishopVariation(2, 0, Color.WHITE);
    KingVariation   blackK = new KingVariation(7, 4, Color.BLACK);
    KnightVariation whiteN = new KnightVariation(0, 1, Color.WHITE);
    PawnVariation   blackP = new PawnVariation(6, 3, Color.BLACK);
    QueenVariation  whiteQ = new QueenVariation(3, 3, Color.WHITE);
    RookVariation   blackR = new RookVariation(7, 7, Color.BLACK);

    ChessPieceVariation[] pieces = { whiteB, blackK, whiteN, blackP, whiteQ, blackR };
    for (ChessPieceVariation p : pieces) {
      System.out.printf(
        "%s at (%d,%d) – color=%s%n",
        p.getClass().getSimpleName(),
        p.getRow(), p.getCol(),
        p.getColor()
      );
    }
    System.out.println();

    System.out.println("Move tests:");
    System.out.printf("  Bishop can move to 5,3? %b%n", whiteB.canMove(5, 3));
    System.out.printf("  King   can move to 6,4? %b%n", blackK.canMove(6, 4));
    System.out.printf("  Knight can move to 2,2? %b%n", whiteN.canMove(2, 2));
    System.out.printf("  Pawn   can move to 5,3? %b%n", blackP.canMove(5, 3));
    System.out.printf("  Queen  can move to 3,7? %b%n", whiteQ.canMove(3, 7));
    System.out.printf("  Rook   can move to 0,7? %b%n", blackR.canMove(0, 7));
    System.out.println();

    System.out.println("Kill tests:");
    PawnVariation targetPawn   = new PawnVariation(6, 4, Color.BLACK);
    System.out.printf(
      "  Bishop at (%d,%d) can kill pawn at (%d,%d)? %b%n",
      whiteB.getRow(), whiteB.getCol(),
      targetPawn.getRow(), targetPawn.getCol(),
      whiteB.canKill(targetPawn)
    );

    KnightVariation targetKnight = new KnightVariation(6, 3, Color.WHITE);
    System.out.printf(
      "  King   at (%d,%d) can kill knight at (%d,%d)? %b%n",
      blackK.getRow(), blackK.getCol(),
      targetKnight.getRow(), targetKnight.getCol(),
      blackK.canKill(targetKnight)
    );

    System.out.printf(
      "  Queen  at (%d,%d) can kill rook at (%d,%d)? %b%n",
      whiteQ.getRow(), whiteQ.getCol(),
      blackR.getRow(), blackR.getCol(),
      whiteQ.canKill(blackR)
    );
  }
}