package h07.graph;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

class DirectedGraphImpl<V, A> implements DirectedGraph<V, A> {
  private final List<V> vertices;
  private final List<ArcItem<A, V>> arcs;

  DirectedGraphImpl() {
    this.vertices = new LinkedList<>();
    this.arcs = new LinkedList<>();
  }

  //EIGENLICH SOLLTE MAN DAS FÜR LAUFZEIT ALLES MIT HASHMAPS MACHEN


  @Override
  public Collection<V> getAllNodes() {
    return vertices.stream().collect(Collectors.toUnmodifiableSet());
  }


  @Override
  public Collection<V> getChildrenForNode(V node) {
    if (node == null)
      throw new NullPointerException();
    if (!vertices.contains(node))
      throw new NoSuchElementException();
    List<V> children = new LinkedList<>();

    for (ArcItem<A, V> arcItem : arcs) {
      if (arcItem.node1.equals(node) && !children.contains(arcItem.node1))
        children.add(arcItem.node2);

    }
    return children;
  }

  @Override
  public A getArcWeightBetween(V from, V to) {
    if (from == null || to == null)
      throw new NullPointerException();
    if (!getChildrenForNode(from).contains(to))
      throw new NoSuchElementException("Entweder ist from nicht im Graphen oder to kein Child von from");
    for (ArcItem<A, V> arcItem : arcs) {
      if (arcItem.node1.equals(from))
        return arcItem.weight;
    }
    return null;
  }

  @Override
  public void addNode(V node) {
    if (node == null)
      throw new NullPointerException();
    if (getAllNodes().contains(node))
      throw new IllegalArgumentException("addNode");
    vertices.add(node);
  }

  @Override
  public void removeNode(V node) {
    if (node == null)
      throw new NullPointerException();
    if (!getAllNodes().contains(node))
      throw new IllegalArgumentException("removeNode");
    arcs.removeIf(arc -> arc.node1.equals(node) || arc.node2.equals(node));
  }

  @Override
  public void connectNodes(V from, A weight, V to) {
    if (from == null || to == null || weight == null)
      throw new NullPointerException();
    if (!vertices.contains(from) || !vertices.contains(to))
      throw new NoSuchElementException();
    if (arcs.stream().anyMatch(arc -> arc.node1.equals(from) && arc.node2.equals(to)))
      throw new IllegalArgumentException();
    arcs.add(new ArcItem<>(from, to, weight));
  }

  @Override
  public void disconnectNodes(V from, V to) {
    if (from == null || to == null)
      throw new NullPointerException();
    if (!vertices.contains(from) || !vertices.contains(to))
      throw new NoSuchElementException();
    if (!arcs.removeIf(arc -> arc.node1.equals(from) && arc.node2.equals(to)))
      throw new NoSuchElementException();
  }

  private static class ArcItem<A, V> {
    V node1;
    V node2;
    A weight;

    public ArcItem(V node1, V node2, A weight) {
      this.node1 = node1;
      this.node2 = node2;
      this.weight = weight;
    }

    @Override
    public String toString() {
      return "ArcItem{" +
        "node1=" + node1 +
        ", node2=" + node2 +
        ", weight=" + weight +
        '}';
    }
  }

  @Override
  public String toString() {
    return "DirectedGraphImpl{" +
      "vertices=" + vertices +
      ", arcs=" + arcs +
      '}';
  }
}
