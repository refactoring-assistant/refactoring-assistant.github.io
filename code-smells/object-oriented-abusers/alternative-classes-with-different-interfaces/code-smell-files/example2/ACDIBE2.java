enum Color {
  BLACK(1), WHITE(0);
  private final int value;
  Color(int value) { this.value = value; }
  public int getValue() { return value; }
}

class Bishop {
  private int row, col;
  private Color color;

  public Bishop(int row, int col, Color color) {
    if (row > 7 || row < 0 || col > 7 || col < 0) {
      throw new IllegalArgumentException("ChessPiece out of bounds");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  public Bishop(Bishop original) {
    this(original.getBishopRow(), original.getBishopCol(), original.getBishopColor());
  }

  public int getBishopRow() { return this.row; }
  public int getBishopCol() { return this.col; }
  public Color getBishopColor() { return this.color; }

  public boolean bishopCanMove(int row, int col) {
    if (row < 0 || row > 7 || col < 0 || col > 7
      || row == this.row || col == this.col) {
      return false;
    }
    int rowDiff = Math.abs(row - this.row);
    int colDiff = Math.abs(col - this.col);
    return rowDiff == colDiff;
  }

  public boolean bishopCanKillAnotherBishop(Bishop otherBishop) {
    return otherBishop.getBishopColor() != this.color
      && this.bishopCanMove(otherBishop.getBishopRow(), otherBishop.getBishopCol());
  }

  public boolean bishopCanKillKing(King otherKing) {
    return otherKing.getKingColor() != this.color
      && this.bishopCanMove(otherKing.getKingRow(), otherKing.getKingCol());
  }

  public boolean bishopCanKillKnight(Knight otherKnight) {
    return otherKnight.getKnightColor() != this.color
      && this.bishopCanMove(otherKnight.getKnightRow(), otherKnight.getKnightCol());
  }

  public boolean bishopCanKillPawn(Pawn otherPawn) {
    return otherPawn.getPawnColor() != this.color
      && this.bishopCanMove(otherPawn.getPawnRow(), otherPawn.getPawnCol());
  }

  public boolean bishopCanKillQueen(Queen otherQueen) {
    return otherQueen.getQueenColor() != this.color
      && this.bishopCanMove(otherQueen.getQueenRow(), otherQueen.getQueenCol());
  }

  public boolean bishopCanKillRook(Rook otherRook) {
    return otherRook.getRookColor() != this.color
      && this.bishopCanMove(otherRook.getRookRow(), otherRook.getRookCol());
  }
}

class King {
  private int row, col;
  private Color color;
  private static final int VALID_KING_1 = 1;
  private static final int VALID_KING_0 = 0;

  public King(int row, int col, Color color) {
    if (row > 7 || row < 0 || col > 7 || col < 0) {
      throw new IllegalArgumentException("ChessPiece out of bounds");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  public King(King original) {
    this(original.getKingRow(), original.getKingCol(), original.getKingColor());
  }

  public int getKingRow() { return this.row; }
  public int getKingCol() { return this.col; }
  public Color getKingColor() { return this.color; }

  public boolean kingCanMove(int row, int col) {
    if (row < 0 || row > 7 || col < 0 || col > 7
      || (row == this.row && col == this.col)) {
      return false;
    }
    int rowDiff = Math.abs(row - this.row);
    int colDiff = Math.abs(col - this.col);
    return (rowDiff == VALID_KING_1 && colDiff == VALID_KING_1)
      || (rowDiff == VALID_KING_1 && colDiff == VALID_KING_0)
      || (rowDiff == VALID_KING_0 && colDiff == VALID_KING_1);
  }

  public boolean kingCanKillAnotherKing(King otherKing) {
    return otherKing.getKingColor() != this.color
      && this.kingCanMove(
      otherKing.getKingRow(),
      otherKing.getKingCol()
    );
  }

  public boolean kingCanKillBishop(Bishop otherBishop) {
    return otherBishop.getBishopColor() != this.color
      && this.kingCanMove(
      otherBishop.getBishopRow(),
      otherBishop.getBishopCol()
    );
  }

  public boolean kingCanKillKnight(Knight otherKnight) {
    return otherKnight.getKnightColor() != this.color
      && this.kingCanMove(
      otherKnight.getKnightRow(),
      otherKnight.getKnightCol()
    );
  }

  public boolean kingCanKillPawn(Pawn otherPawn) {
    return otherPawn.getPawnColor() != this.color
      && this.kingCanMove(
      otherPawn.getPawnRow(),
      otherPawn.getPawnCol()
    );
  }

  public boolean kingCanKillQueen(Queen otherQueen) {
    return otherQueen.getQueenColor() != this.color
      && this.kingCanMove(
      otherQueen.getQueenRow(),
      otherQueen.getQueenCol()
    );
  }

  public boolean kingCanKillRook(Rook otherRook) {
    return otherRook.getRookColor() != this.color
      && this.kingCanMove(
      otherRook.getRookRow(),
      otherRook.getRookCol()
    );
  }
}

class Knight {
  private int row, col;
  private Color color;
  private static final int VALID_ROW_DIFF_2 = 2;
  private static final int VALID_ROW_DIFF_1 = 1;
  private static final int VALID_COL_DIFF_2 = 2;
  private static final int VALID_COL_DIFF_1 = 1;

  public Knight(int row, int col, Color color) {
    if (row > 7 || row < 0 || col > 7 || col < 0) {
      throw new IllegalArgumentException("ChessPiece out of bounds");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  public Knight(Knight original) {
    this(original.getKnightRow(), original.getKnightCol(), original.getKnightColor());
  }

  public int getKnightRow() { return this.row; }
  public int getKnightCol() { return this.col; }
  public Color getKnightColor() { return this.color; }

  public boolean knightCanMove(int row, int col) {
    if (row < 0 || row > 7 || col < 0 || col > 7
      || row == this.row || col == this.col) {
      return false;
    }
    int rowDiff = Math.abs(row - this.row);
    int colDiff = Math.abs(col - this.col);
    return (rowDiff == VALID_ROW_DIFF_2 && colDiff == VALID_COL_DIFF_1)
      || (rowDiff == VALID_ROW_DIFF_1 && colDiff == VALID_COL_DIFF_2);
  }

  public boolean knightCanKillAnotherKnight(Knight otherKnight) {
    return otherKnight.getKnightColor() != this.color
      && this.knightCanMove(
      otherKnight.getKnightRow(),
      otherKnight.getKnightCol()
    );
  }

  public boolean knightCanKillKing(King otherKing) {
    return otherKing.getKingColor() != this.color
      && this.knightCanMove(
      otherKing.getKingRow(),
      otherKing.getKingCol()
    );
  }

  public boolean knightCanKillBishop(Bishop otherBishop) {
    return otherBishop.getBishopColor() != this.color
      && this.knightCanMove(
      otherBishop.getBishopRow(),
      otherBishop.getBishopCol()
    );
  }

  public boolean knightCanKillPawn(Pawn otherPawn) {
    return otherPawn.getPawnColor() != this.color
      && this.knightCanMove(
      otherPawn.getPawnRow(),
      otherPawn.getPawnCol()
    );
  }

  public boolean knightCanKillQueen(Queen otherQueen) {
    return otherQueen.getQueenColor() != this.color
      && this.knightCanMove(
      otherQueen.getQueenRow(),
      otherQueen.getQueenCol()
    );
  }

  public boolean knightCanKillRook(Rook otherRook) {
    return otherRook.getRookColor() != this.color
      && this.knightCanMove(
      otherRook.getRookRow(),
      otherRook.getRookCol()
    );
  }
}

class Pawn {
  private int row, col;
  private Color color;
  private static final int BLACK_START_ROW = 6;
  private static final int WHITE_START_ROW = 1;
  private static final int VALID_BLACK_ROW_DIFF_1 = -1;
  private static final int VALID_BLACK_ROW_DIFF_2 = -2;
  private static final int VALID_WHITE_ROW_DIFF_1 = 1;
  private static final int VALID_WHITE_ROW_DIFF_2 = 2;
  private static final int VALID_COL_DIFF = 0;
  private static final int VALID_COL_KILL_DIFF = 1;

  public Pawn(int row, int col, Color color) {
    if (row > 7 || row < 0 || col > 7 || col < 0) {
      throw new IllegalArgumentException("ChessPiece out of bounds");
    }
    if (color == Color.WHITE && row != WHITE_START_ROW) {
      throw new IllegalArgumentException("Set white pawns at the wrong row!");
    } else if (color == Color.BLACK && row != BLACK_START_ROW) {
      throw new IllegalArgumentException("Set black pawns at the wrong row!");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  public Pawn(Pawn original) {
    this(original.getPawnRow(), original.getPawnCol(), original.getPawnColor());
  }

  public int getPawnRow() { return this.row; }
  public int getPawnCol() { return this.col; }
  public Color getPawnColor() { return this.color; }

  public boolean pawnCanMove(int row, int col) {
    if (row < 0 || row > 7 || col < 0 || col > 7
      || (row == this.row && col == this.col)) {
      return false;
    }
    int rowDiff = row - this.row;
    int colDiff = col - this.col;

    if (this.color == Color.BLACK) {
      if (this.row == BLACK_START_ROW) {
        return (rowDiff == VALID_BLACK_ROW_DIFF_1 && colDiff == VALID_COL_DIFF)
          || (rowDiff == VALID_BLACK_ROW_DIFF_2 && colDiff == VALID_COL_DIFF);
      } else {
        return rowDiff == VALID_BLACK_ROW_DIFF_1 && colDiff == VALID_COL_DIFF;
      }
    }
    if (this.color == Color.WHITE) {
      if (this.row == WHITE_START_ROW) {
        return (rowDiff == VALID_WHITE_ROW_DIFF_1 && colDiff == VALID_COL_DIFF)
          || (rowDiff == VALID_WHITE_ROW_DIFF_2 && colDiff == VALID_COL_DIFF);
      } else {
        return rowDiff == VALID_WHITE_ROW_DIFF_1 && colDiff == VALID_COL_DIFF;
      }
    }
    return false;
  }

  public boolean pawnCanKill(ChessPiece piece) {
    if (this.color == piece.getColor()
      || piece.getRow() > 7 || piece.getRow() < 0 || piece.getCol() > 7 || piece.getCol() < 0) {
      return false;
    }
    int rowDiff = piece.getRow() - this.row;
    int colDiff = Math.abs(piece.getCol() - this.col);

    if (this.color == Color.BLACK) {
      return rowDiff == VALID_BLACK_ROW_DIFF_1 && colDiff == VALID_COL_KILL_DIFF;
    } else {
      return rowDiff == VALID_WHITE_ROW_DIFF_1 && colDiff == VALID_COL_KILL_DIFF;
    }
  }

  public boolean pawnCanKillAnotherPawn(Pawn otherPawn) {
    if (this.color == otherPawn.getPawnColor()
      || otherPawn.getPawnRow() > 7 || otherPawn.getPawnRow() < 0 || otherPawn.getPawnCol() > 7 || otherPawn.getPawnCol() < 0) {
      return false;
    }
    int rowDiff = otherPawn.getPawnRow() - this.row;
    int colDiff = Math.abs(otherPawn.getPawnCol() - this.col);

    if (this.color == Color.BLACK) {
      return rowDiff == VALID_BLACK_ROW_DIFF_1 && colDiff == VALID_COL_KILL_DIFF;
    } else {
      return rowDiff == VALID_WHITE_ROW_DIFF_1 && colDiff == VALID_COL_KILL_DIFF;
    }
  }

  public boolean pawnCanKillKing(King otherKing) {
    if (this.color == otherKing.getKingColor()
      || otherKing.getKingRow() > 7 || otherKing.getKingRow() < 0 || otherKing.getKingCol() > 7 || otherKing.getKingCol() < 0) {
      return false;
    }
    int rowDiff = otherKing.getKingRow() - this.row;
    int colDiff = Math.abs(otherKing.getKingCol() - this.col);

    if (this.color == Color.BLACK) {
      return rowDiff == VALID_BLACK_ROW_DIFF_1 && colDiff == VALID_COL_KILL_DIFF;
    } else {
      return rowDiff == VALID_WHITE_ROW_DIFF_1 && colDiff == VALID_COL_KILL_DIFF;
    }
  }

  public boolean pawnCanKillBishop(Bishop otherBishop) {
    if (this.color == otherBishop.getBishopColor()
      || otherBishop.getBishopRow() > 7 || otherBishop.getBishopRow() < 0 || otherBishop.getBishopCol() > 7 || otherBishop.getBishopCol() < 0) {
      return false;
    }
    int rowDiff = otherBishop.getBishopRow() - this.row;
    int colDiff = Math.abs(otherBishop.getBishopCol() - this.col);

    if (this.color == Color.BLACK) {
      return rowDiff == VALID_BLACK_ROW_DIFF_1 && colDiff == VALID_COL_KILL_DIFF;
    } else {
      return rowDiff == VALID_WHITE_ROW_DIFF_1 && colDiff == VALID_COL_KILL_DIFF;
    }
  }

  public boolean pawnCanKillKnight(Knight otherKnight) {
    if (this.color == otherKnight.getKnightColor()
      || otherKnight.getKnightRow() > 7 || otherKnight.getKnightRow() < 0 || otherKnight.getKnightCol() > 7 || otherKnight.getKnightCol() < 0) {
      return false;
    }
    int rowDiff = otherKnight.getKnightRow() - this.row;
    int colDiff = Math.abs(otherKnight.getKnightCol() - this.col);

    if (this.color == Color.BLACK) {
      return rowDiff == VALID_BLACK_ROW_DIFF_1 && colDiff == VALID_COL_KILL_DIFF;
    } else {
      return rowDiff == VALID_WHITE_ROW_DIFF_1 && colDiff == VALID_COL_KILL_DIFF;
    }
  }

  public boolean pawnCanKillQueen(Queen otherQueen) {
    if (this.color == otherQueen.getQueenColor()
      || otherQueen.getQueenRow() > 7 || otherQueen.getQueenRow() < 0 || otherQueen.getQueenCol() > 7 || otherQueen.getQueenCol() < 0) {
      return false;
    }
    int rowDiff = otherQueen.getQueenRow() - this.row;
    int colDiff = Math.abs(otherQueen.getQueenCol() - this.col);

    if (this.color == Color.BLACK) {
      return rowDiff == VALID_BLACK_ROW_DIFF_1 && colDiff == VALID_COL_KILL_DIFF;
    } else {
      return rowDiff == VALID_WHITE_ROW_DIFF_1 && colDiff == VALID_COL_KILL_DIFF;
    }
  }

  public boolean pawnCanKillRook(Rook otherRook) {
    if (this.color == otherRook.getRookColor()
      || otherRook.getRookRow() > 7 || otherRook.getRookRow() < 0 || otherRook.getRookCol() > 7 || otherRook.getRookCol() < 0) {
      return false;
    }
    int rowDiff = otherRook.getRookRow() - this.row;
    int colDiff = Math.abs(otherRook.getRookCol() - this.col);

    if (this.color == Color.BLACK) {
      return rowDiff == VALID_BLACK_ROW_DIFF_1 && colDiff == VALID_COL_KILL_DIFF;
    } else {
      return rowDiff == VALID_WHITE_ROW_DIFF_1 && colDiff == VALID_COL_KILL_DIFF;
    }
  }

  protected boolean pawnHasMoved() {
    if (this.color == Color.BLACK && this.row == BLACK_START_ROW) {
      return false;
    }
    if (this.color == Color.WHITE && this.row == WHITE_START_ROW) {
      return false;
    }
    return true;
  }
}

class Queen {
  private int row, col;
  private Color color;

  public Queen(int row, int col, Color color) {
    if (row > 7 || row < 0 || col > 7 || col < 0) {
      throw new IllegalArgumentException("ChessPiece out of bounds");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  public Queen(Queen original) {
    this(original.getQueenRow(), original.getQueenCol(), original.getQueenColor());
  }

  public int getQueenRow() { return this.row; }
  public int getQueenCol() { return this.col; }
  public Color getQueenColor() { return this.color; }

  public boolean queenCanMove(int row, int col) {
    if (row < 0 || row > 7 || col < 0 || col > 7
      || (row == this.row && col == this.col)) {
      return false;
    }
    int rowDiff = Math.abs(row - this.row);
    int colDiff = Math.abs(col - this.col);
    return rowDiff == colDiff || rowDiff == 0 || colDiff == 0;
  }

  public boolean queenCanKillAnotherQueen(Queen otherQueen) {
    return otherQueen.getQueenColor() != this.color
      && this.queenCanMove(
      otherQueen.getQueenRow(),
      otherQueen.getQueenCol()
    );
  }

  public boolean queenCanKillKing(King otherKing) {
    return otherKing.getKingColor() != this.color
      && this.queenCanMove(
      otherKing.getKingRow(),
      otherKing.getKingCol()
    );
  }

  public boolean queenCanKillBishop(Bishop otherBishop) {
    return otherBishop.getBishopColor() != this.color
      && this.queenCanMove(
      otherBishop.getBishopRow(),
      otherBishop.getBishopCol()
    );
  }

  public boolean queenCanKillKnight(Knight otherKnight) {
    return otherKnight.getKnightColor() != this.color
      && this.queenCanMove(
      otherKnight.getKnightRow(),
      otherKnight.getKnightCol()
    );
  }

  public boolean queenCanKillPawn(Pawn otherPawn) {
    return otherPawn.getPawnColor() != this.color
      && this.queenCanMove(
      otherPawn.getPawnRow(),
      otherPawn.getPawnCol()
    );
  }

  public boolean queenCanKillRook(Rook otherRook) {
    return otherRook.getRookColor() != this.color
      && this.queenCanMove(
      otherRook.getRookRow(),
      otherRook.getRookCol()
    );
  }
}

class Rook {
  private int row, col;
  private Color color;
  private static final int VALID_ROOK_0 = 0;

  public Rook(int row, int col, Color color) {
    if (row > 7 || row < 0 || col > 7 || col < 0) {
      throw new IllegalArgumentException("ChessPiece out of bounds");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  public Rook(Rook original) {
    this(original.getRookRow(), original.getRookCol(), original.getRookColor());
  }

  public int getRookCol() { return this.col; }
  public int getRookRow() { return this.row; }
  public Color getRookColor() { return this.color; }

  public boolean rookCanMove(int row, int col) {
    if (row < 0 || row > 7 || col < 0 || col > 7
      || row == this.row || col == this.col) {
      return false;
    }
    int rowDiff = Math.abs(row - this.row);
    int colDiff = Math.abs(col - this.col);
    return rowDiff == VALID_ROOK_0 || colDiff == VALID_ROOK_0;
  }

  public boolean rookCanKillAnotherRook(Rook otherRook) {
    return otherRook.getRookColor() != this.color
      && this.rookCanMove(
      otherRook.getRookRow(),
      otherRook.getRookCol()
    );
  }

  public boolean rookCanKillKing(King otherKing) {
    return otherKing.getKingColor() != this.color
      && this.rookCanMove(
      otherKing.getKingRow(),
      otherKing.getKingCol()
    );
  }

  public boolean rookCanKillBishop(Bishop otherBishop) {
    return otherBishop.getBishopColor() != this.color
      && this.rookCanMove(
      otherBishop.getBishopRow(),
      otherBishop.getBishopCol()
    );
  }

  public boolean rookCanKillKnight(Knight otherKnight) {
    return otherKnight.getKnightColor() != this.color
      && this.rookCanMove(
      otherKnight.getKnightRow(),
      otherKnight.getKnightCol()
    );
  }

  public boolean rookCanKillPawn(Pawn otherPawn) {
    return otherPawn.getPawnColor() != this.color
      && this.rookCanMove(
      otherPawn.getPawnRow(),
      otherPawn.getPawnCol()
    );
  }

  public boolean rookCanKillQueen(Queen otherQueen) {
    return otherQueen.getQueenColor() != this.color
      && this.rookCanMove(
      otherQueen.getQueenRow(),
      otherQueen.getQueenCol()
    );
  }
}

interface ChessPiece {
  int getRow();
  int getCol();
  Color getColor();
  boolean canMove(int row, int col);
  boolean canKill(ChessPiece piece);
}

abstract class BaseChessPiece {
  private int row, col;
  private Color color;

  public BaseChessPiece(int row, int col, Color color) {
    if (row > 7 || row < 0 || col > 7 || col < 0) {
      throw new IllegalArgumentException("ChessPiece out of bounds");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  public BaseChessPiece(BaseChessPiece original) {
    this(original.row, original.col, original.color);
  }

  public int getRow() {
    return this.row;
  }
  public int getCol() {
    return this.col;
  }
  public Color getColor() {
    return this.color;
  }
  public abstract boolean canMove(int row, int col);
  public abstract boolean canKill(ChessPiece piece);
}

class ACDIBE1 {
  public static void main(String[] args) {
    Bishop whiteB = new Bishop(2, 0, Color.WHITE);
    King   blackK = new King(7, 4, Color.BLACK);
    Knight whiteN = new Knight(0, 1, Color.WHITE);
    Pawn   blackP = new Pawn(6, 3, Color.BLACK);
    Queen  whiteQ = new Queen(3, 3, Color.WHITE);
    Rook   blackR = new Rook(7, 7, Color.BLACK);

    System.out.println("Piece info:");
    System.out.printf("  Bishop at (%d,%d) – color=%s%n",
      whiteB.getBishopRow(), whiteB.getBishopCol(), whiteB.getBishopColor());
    System.out.printf("  King   at (%d,%d) – color=%s%n",
      blackK.getKingRow(),   blackK.getKingCol(),   blackK.getKingColor());
    System.out.printf("  Knight at (%d,%d) – color=%s%n",
      whiteN.getKnightRow(), whiteN.getKnightCol(), whiteN.getKnightColor());
    System.out.printf("  Pawn   at (%d,%d) – color=%s%n",
      blackP.getPawnRow(),   blackP.getPawnCol(),   blackP.getPawnColor());
    System.out.printf("  Queen  at (%d,%d) – color=%s%n",
      whiteQ.getQueenRow(),  whiteQ.getQueenCol(),  whiteQ.getQueenColor());
    System.out.printf("  Rook   at (%d,%d) – color=%s%n",
      blackR.getRookRow(),   blackR.getRookCol(),   blackR.getRookColor());
    System.out.println();

    System.out.println("Move tests:");
    System.out.printf("  Bishop can move to 5,3? %b%n",
      whiteB.bishopCanMove(5, 3));
    System.out.printf("  King   can move to 6,4? %b%n",
      blackK.kingCanMove(6, 4));
    System.out.printf("  Knight can move to 2,2? %b%n",
      whiteN.knightCanMove(2, 2));
    System.out.printf("  Pawn   can move to 5,3? %b%n",
      blackP.pawnCanMove(5, 3));
    System.out.printf("  Queen  can move to 3,7? %b%n",
      whiteQ.queenCanMove(3, 7));
    System.out.printf("  Rook   can move to 0,7? %b%n",
      blackR.rookCanMove(0, 7));
    System.out.println();

    System.out.println("Pawn movement state:");
    System.out.printf("  Black pawn at start row hasMoved()? %b%n",
      blackP.pawnHasMoved());
    System.out.println();

    System.out.println("Kill tests:");
    Pawn targetPawn   = new Pawn(6, 4, Color.BLACK);
    System.out.printf("  Bishop can kill pawn at (%d,%d)? %b%n",
      targetPawn.getPawnRow(), targetPawn.getPawnCol(),
      whiteB.bishopCanKillPawn(targetPawn));

    Knight targetKnight = new Knight(6, 3, Color.WHITE);
    System.out.printf("  King   can kill knight at (%d,%d)? %b%n",
      targetKnight.getKnightRow(), targetKnight.getKnightCol(),
      blackK.kingCanKillKnight(targetKnight));

    Pawn knightTargetPawn = new Pawn(6, 5, Color.BLACK);
    System.out.printf("  Knight can kill pawn at (%d,%d)? %b%n",
      knightTargetPawn.getPawnRow(), knightTargetPawn.getPawnCol(),
      whiteN.knightCanKillPawn(knightTargetPawn));

    Pawn whiteStartPawn = new Pawn(1, 4, Color.WHITE);
    Pawn targetWhitePawn = new Pawn(1, 5, Color.WHITE);
    System.out.printf("  Pawn   can kill pawn at (%d,%d)? %b%n",
      targetWhitePawn.getPawnRow(), targetWhitePawn.getPawnCol(),
      blackP.pawnCanKillAnotherPawn(targetWhitePawn));

    System.out.printf("  Queen  can kill rook at (%d,%d)? %b%n",
      blackR.getRookRow(), blackR.getRookCol(),
      whiteQ.queenCanKillRook(blackR));

    Bishop straightBishop = new Bishop(2, 7, Color.WHITE);
    System.out.printf("  Rook   can kill bishop at (%d,%d)? %b%n",
      straightBishop.getBishopRow(), straightBishop.getBishopCol(),
      blackR.rookCanKillBishop(straightBishop));
  }
}
