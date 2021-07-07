package h07.graph;

import java.util.Arrays;
import java.util.Iterator;

class PathImpl<V, A> extends AbstractPath<V, A> {
  private final V[] nodes;
  private final A[] weights;

  PathImpl(V[] nodes, A[] weights) {
    this.nodes = nodes;
    this.weights = weights;
  }

  @SuppressWarnings("unchecked")
  PathImpl(V node) {
    var t1 = (V[]) new Object[1];
    t1[0] = node;
    this.nodes = t1;
    this.weights = null;
  }

  @SuppressWarnings("unchecked")
  PathImpl(V[] nodes, A weight) {
    var t1 = (A[]) new Object[1];
    t1[0] = weight;
    this.nodes = nodes;
    this.weights = t1;
  }

  @Override
  public Traverser<V, A> traverser() {
    return new Traverser<>() {
      int currentNode;

      @Override
      public V getCurrentNode() {
        return nodes[currentNode];
      }

      @Override
      public A getDistanceToNextNode() {
        if (weights == null || currentNode >= weights.length)
          throw new IllegalStateException();
        return weights[currentNode];
      }

      @Override
      public void walkToNextNode() {
        if (currentNode++ == nodes.length - 1)
          throw new IllegalStateException();
      }

      @Override
      public boolean hasNextNode() {
        return  currentNode + 1 < nodes.length ;
      }
    };
  }

  @Override
  public Path<V, A> concat(V node, A distance) {
    if (node == null || distance == null)
      throw new NullPointerException();
    var t1 = Arrays.copyOf(nodes,nodes.length +1);
    t1[nodes.length] = node;
    if (weights != null){
    var t2 = Arrays.copyOf(weights, weights.length + 1);
    t2[weights.length] = distance;
      return new PathImpl<>(t1,t2);
    } else
      return new PathImpl<>(t1,distance);


  }

  @Override
  public Iterator<V> iterator() {
    return new Iterator<V>() {
      int currentNode;

      @Override
      public boolean hasNext() {
        return currentNode < nodes.length;
      }

      @Override
      public V next() {
        return nodes[currentNode++ - 1];
      }
    };
  }
}
