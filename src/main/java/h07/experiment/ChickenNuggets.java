package h07.experiment;

import h07.algebra.IntegerAddition;
import h07.algorithm.Dijkstra;

import java.util.Comparator;

public class ChickenNuggets {
  public static int[] computePackageNumbers(int numberOfNuggets) {
    int[] nuggetBoxNumbers = new int[3];
    var nuggetPathTraverser = new Dijkstra<Integer, Integer>().shortestPaths(
      new ChickenNuggetGraphFactory().createDirectedGraph(), 0,
      new IntegerAddition(),
      Comparator.naturalOrder()).get(Math.floorMod(numberOfNuggets, 6)).traverser();

    while (nuggetPathTraverser.hasNextNode()) {
      if (nuggetPathTraverser.getDistanceToNextNode() == 20)
        nuggetBoxNumbers[2]++;
      else
        nuggetBoxNumbers[1]++;
      nuggetPathTraverser.walkToNextNode();
    }
    var rest = numberOfNuggets - (20 * nuggetBoxNumbers[2] + 9 * nuggetBoxNumbers[1]);
    if (rest >= 0 && Math.floorMod(rest, 6) == 0) {
      nuggetBoxNumbers[0] = rest / 6;
      return nuggetBoxNumbers;
    } else
      return null;

  }
}
