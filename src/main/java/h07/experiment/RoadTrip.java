package h07.experiment;

/*
import h07.algebra.IntegerAddition;
import h07.algorithm.Dijkstra;
import h07.graph.AdjacencyMatrixFactory;
*/

import h07.algebra.IntegerAddition;
import h07.algorithm.Dijkstra;
import h07.graph.AdjacencyMatrixFactory;

import java.util.Comparator;

public class RoadTrip {
  public static void main(String[] args) {
    String[] nodes = {
      "Muenchen",
      "Augsburg",
      "Karlsruhe",
      "Erfurt",
      "Nuernberg",
      "Kassel",
      "Wuerzburg",
      "Stuttgart",
      "Mannheim",
      "Frankfurt"
    };

    Integer[][] adjacencyMatrix = {
      {null, 84, null, null, 167, 502, null, null, null, null},
      {84, null, 250, null, null, null, null, null, null, null},
      {null, 250, null, null, null, null, null, null, 80, null},
      {null, null, null, null, null, null, 186, null, null, null},
      {167, null, null, null, null, null, 103, 183, null, null},
      {502, null, null, null, null, null, null, null, null, 173},
      {null, null, null, 186, 103, null, null, null, null, 217},
      {null, null, null, null, 183, null, null, null, null, null},
      {null, null, 80, null, null, null, null, null, null, 85},
      {null, null, null, null, null, 173, 217, null, 85, null}
    };

    var factory = new AdjacencyMatrixFactory<>(nodes, adjacencyMatrix);
    var graph = factory.createDirectedGraph();

    var dijkstra = new Dijkstra<String, Integer>();
    var paths = dijkstra.shortestPaths(
      graph, "Frankfurt",
      new IntegerAddition(),
      Comparator.naturalOrder());

    // Sollte "Frankfurt -217-> Würzburg -103-> Nürnberg -167-> München"
    // ausgeben.
    System.out.println(paths.get("Muenchen"));

  }
}
