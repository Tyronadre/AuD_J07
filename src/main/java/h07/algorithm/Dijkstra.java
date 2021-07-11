package h07.algorithm;

import h07.algebra.Monoid;
import h07.graph.DirectedGraph;
import h07.graph.Path;

import java.util.*;

public class Dijkstra<V, A> implements ShortestPathsAlgorithm<V, A> {

  @Override
  @SuppressWarnings("unchecked")
  public Map<V, Path<V, A>> shortestPaths(DirectedGraph<V, A> graph, V startNode, Monoid<A> monoid, Comparator<? super A> comparator) {
    if (graph == null || startNode == null || monoid == null || comparator == null)
      throw new NullPointerException();
    if (!graph.getAllNodes().contains(startNode))
      throw new NoSuchElementException();
    Map<V, Path<V, A>> pathMap = new HashMap<>();
    List<Path<V, A>> pathList = new LinkedList<>();
    List<V> finished = new LinkedList<>();
    V lastNode = startNode;
    pathMap.put(startNode, Path.of(startNode));

    while (!finished.containsAll(graph.getAllNodes())) {
      for (var childNode : graph.getChildrenForNode(lastNode)) {
        if (!finished.contains(childNode)) {
          Path<V, A> path = Path.of(lastNode, graph.getArcWeightBetween(lastNode, childNode), childNode);
          if (lastNode != startNode) {
            path = pathList.get(0).concat(childNode, graph.getArcWeightBetween(lastNode, childNode));
          }
          //wenn dieser pfad kürzer als der aktuell in der map gespeicherte, dann überschreiben
          if (!pathMap.containsKey(childNode) || comparator.compare(getPathLength(pathMap.get(childNode), monoid), getPathLength(path, monoid)) > 0)
            pathMap.put(childNode, path);
          pathList.add(path);
        }
      }
      finished.add(lastNode);
      pathList.removeIf(item1 -> finished.contains(getLastNode(item1)));
      pathList.sort((item1, item2) -> comparator.compare(getPathLength(item1, monoid), getPathLength(item2, monoid)));
      if (!pathList.isEmpty())
        lastNode = getLastNode(pathList.get(0));
    }
    return pathMap;
  }

  private V getLastNode(Path<V, A> path) {
    var t = path.traverser();
    while (t.hasNextNode())
      t.walkToNextNode();
    return t.getCurrentNode();
  }

  private A getPathLength(Path<V, A> path, Monoid<A> monoid) {
    var traverser = path.traverser();
    A length = null;
    while (traverser.hasNextNode()) {
      if (length == null)
        length = traverser.getDistanceToNextNode();
      else
        length = monoid.add(traverser.getDistanceToNextNode(), length);
      traverser.walkToNextNode();
    }
    return length;
  }
}
