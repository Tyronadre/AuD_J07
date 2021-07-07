package h07.graph;

public class Main {

  public static void main(String[] args) {
    String[] nodes = { "A", "B", "C", "D", "F" };
    Integer[][] adjacencyMatrix = {
      { null, 10, 3, null, null },
      { 9, null, 5, null, null },
      { null, null, null, 9, null },
      { null, null, null, null, null },
      { null, null, null, null, null }
    };
    DirectedGraphFactory<String, Integer> factory = new AdjacencyMatrixFactory<>(nodes, adjacencyMatrix);
    DirectedGraph<String, Integer> graph = factory.createDirectedGraph();


    Path<String, Integer> t = Path.of("A", 10, "B", 11,"C");
    Path<String, Integer> t2 = Path.of("A");
    var t3 = t2.concat("B", 10);
    var t4 = t.traverser();
    while ( t4.hasNextNode()) {
      var tr1 = t4.getCurrentNode();
      var tr2 = t4.getDistanceToNextNode();
      t4.walkToNextNode();
    }
    System.out.println("STOP");
  }
}
