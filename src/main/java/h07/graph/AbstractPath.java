package h07.graph;

abstract class AbstractPath<V, A> implements Path<V, A> {
  @Override
  public String toString() {
    var traverser = traverser();
    var builder = new StringBuilder(traverser.getCurrentNode().toString());
    while (traverser.hasNextNode()) {
      builder.append(" -");
      builder.append(traverser.getDistanceToNextNode());
      traverser.walkToNextNode();
      builder.append("-> ");
      builder.append(traverser.getCurrentNode());
    }
    return builder.toString();
  }
}
