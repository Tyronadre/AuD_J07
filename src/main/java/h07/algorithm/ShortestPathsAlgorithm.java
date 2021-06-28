package h07.algorithm;

import h07.algebra.Monoid;
import h07.graph.DirectedGraph;
import h07.graph.Path;

import java.util.Comparator;
import java.util.Map;

public interface ShortestPathsAlgorithm<V, A> {
  Map<V, Path<V, A>> shortestPaths(
    DirectedGraph<V, A> graph, V startNode,
    Monoid<A> monoid, Comparator<? super A> comparator);
}
