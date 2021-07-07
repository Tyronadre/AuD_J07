package h07.algorithm;

import h07.algebra.Monoid;
import h07.graph.DirectedGraph;
import h07.graph.Path;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Dijkstra<V, A> implements ShortestPathsAlgorithm<V, A> {

  @Override
  @SuppressWarnings("unchecked")
  public Map<V, Path<V, A>> shortestPaths(DirectedGraph<V, A> graph, V startNode, Monoid<A> monoid, Comparator<? super A> comparator) {
    if (graph == null || startNode == null || monoid == null || comparator == null)
      throw new NullPointerException();
    if (!graph.getAllNodes().contains(startNode))
      throw new NoSuchElementException();
    Map<V, Path<V,A>> pathMap = new HashMap<>();
    boolean[] finished = new boolean[graph.getAllNodes().size()];
    A[] pathLength = (A[]) new Object[graph.getAllNodes().size()];

    for (V node: graph.getAllNodes()) {
      A shortestPath;
      var temp = graph.getChildrenForNode(node).stream().findFirst();
      if (temp.isPresent())
        shortestPath = graph.getArcWeightBetween(node, temp.get());
      else
        break;



      for (V childNode: graph.getChildrenForNode(node))
        shortestPath = comparator.compare(shortestPath, graph.getArcWeightBetween(node,childNode)) < 0 ? graph.getArcWeightBetween(node,childNode) : shortestPath ;
    }

    return null;
  }
}
