package io.tw;

import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Position {

  final int x;
  final int y;

  private Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public static Position of(int x, int y) {
    return new Position(x, y);
  }

  static Stream<Position> createPositionsWithRange(int lowerX, int upperX, int lowerY, int upperY) {
    return IntStream.range(lowerX, upperX + 1).boxed().flatMap(x ->
      IntStream.range(lowerY, upperY + 1).mapToObj(y ->
        of(x, y)
      )
    );
  }

  Stream<Position> getAroundPositions(Predicate<Position> predicate) {
    return getMaybeAroundPositions().filter(predicate);
  }

  private Stream<Position> getMaybeAroundPositions() {
    return createPositionsWithRange(x - 1, x + 1, y - 1, y + 1).
      filter(p -> !this.equals(p));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Position)) return false;
    Position position = (Position) o;
    return x == position.x && y == position.y;
  }

  @Override
  public int hashCode() {
    return 31 * x + y;
  }

  @Override
  public String toString() {
    return "Position(" +
      "x = " + x +
      ", y = " + y +
      ')';
  }

}
