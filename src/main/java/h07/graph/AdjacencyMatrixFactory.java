package h07.graph;

import java.util.Arrays;
import java.util.Objects;

public class AdjacencyMatrixFactory<V, A> implements DirectedGraphFactory<V, A> {
  private final A[][] adjacencyMatrix;
  private final V[] nodes;

  public AdjacencyMatrixFactory(V[] nodes, A[][] adjacencyMatrix) {
    if (adjacencyMatrix == null || nodes == null || Arrays.stream(nodes).anyMatch(Objects::isNull))
      throw new NullPointerException();
    if (nodes.length != adjacencyMatrix.length)
      throw new IllegalArgumentException();
    this.adjacencyMatrix = adjacencyMatrix;
    this.nodes = nodes;
  }

  @Override
  public DirectedGraph<V, A> createDirectedGraph() {
    var directedGraph = new DirectedGraphImpl<V,A>();
    for (V node : nodes) {
      directedGraph.addNode(node);
    }
    for (int i = 0; i < adjacencyMatrix.length; i++)
      for (int j = 0; j < adjacencyMatrix[i].length; j++) {
        if (adjacencyMatrix[i][j] != null)
          directedGraph.connectNodes(nodes[i], adjacencyMatrix[i][j], nodes[j]);
      }
    return directedGraph;
  }
}
