package h07.experiment;

import h07.graph.AdjacencyMatrixFactory;
import h07.graph.DirectedGraph;
import h07.graph.DirectedGraphFactory;

import java.util.Collection;

public class ChickenNuggetGraphFactory implements DirectedGraphFactory<Integer, Integer> {



  @Override
  public DirectedGraph<Integer, Integer> createDirectedGraph() {
    Integer[] nodes = new Integer[6];
    Integer[][] arcs = new Integer[6][6];

    for (int i = 0; i < nodes.length; i++) {
      nodes [i] = i;
      arcs[i][Math.floorMod(i+9,6)] = 9;
      arcs[i][Math.floorMod(i+20,6)] = 20;
    }

    var graph = new AdjacencyMatrixFactory<>(nodes, arcs).createDirectedGraph();



    return new DirectedGraph<>() {
      @Override
      public Collection<Integer> getAllNodes() {
        return graph.getAllNodes();
      }

      @Override
      public Collection<Integer> getChildrenForNode(Integer node) {
        return graph.getChildrenForNode(node);
      }

      @Override
      public Integer getArcWeightBetween(Integer from, Integer to) {
        return graph.getArcWeightBetween(from, to);
      }

      @Override
      public void addNode(Integer node) {
        throw new UnsupportedOperationException();
      }

      @Override
      public void removeNode(Integer node) {
        throw new UnsupportedOperationException();
      }

      @Override
      public void connectNodes(Integer from, Integer weight, Integer to) {
        throw new UnsupportedOperationException();
      }

      @Override
      public void disconnectNodes(Integer from, Integer to) {
        throw new UnsupportedOperationException();
      }
    };
  }
}
