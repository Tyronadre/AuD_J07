package h07.graph;

import java.util.Collection;

/**
 * Repräsentiert einen endlichen gerichteten Graphen mit gewichteten Kanten.
 *
 * @param <V> der Typ der Knoten dieses Graphen
 * @param <A> der Typ der Kantengewichte dieses Graphen
 */
public interface DirectedGraph<V, A> {
  /**
   * Liefert alle Knoten dieses Graphen als unveränderliche
   * {@link java.util.Collection} von Knoten.
   *
   * @return eine unveränderliche {@link java.util.Collection} mit allen
   * Knoten dieses Graphen als Elemente
   */
  Collection<V> getAllNodes();

  /**
   * Liefert die Kinder eines Knoten als unveränderliche
   * {@link java.util.Collection} von Knoten.
   *
   * @param node der Knoten, dessen Kinder abgefragt werden sollen
   * @return eine unveränderliche {@link java.util.Collection} mit den Kindern
   * des übergebenen Knoten als Elemente
   * @throws NullPointerException             falls der übergebene Knoten {@code null} ist
   * @throws java.util.NoSuchElementException falls der übergebene Knoten
   *                                          nicht in diesem Graphen existiert
   */
  Collection<V> getChildrenForNode(V node);

  /**
   * Liefert das Gewicht der Kante zwischen zwei Knoten.
   *
   * @param from der Knoten, von dem die Kante ausgeht
   * @param to   der Knoten, auf den die Kante zeigt
   * @return das Gewicht der Kante zwischen den beiden Knoten
   * @throws NullPointerException             falls für einen der beiden Knoten
   *                                          {@code null} als Parameter übergeben wird
   * @throws java.util.NoSuchElementException falls einer der beiden Knoten
   *                                          nicht in diesem Graphen existiert oder falls keine Kante vom
   *                                          ersten zum zweiten Knoten existiert
   */
  A getArcWeightBetween(V from, V to);

  /**
   * Fügt diesem Graphen einen Knoten hinzu, der bisher noch nicht im Graphen
   * enthalten ist.
   *
   * @param node der Knoten, der hinzugefügt werden soll
   * @throws NullPointerException          falls der hinzuzufügende Knoten {@code null}
   *                                       ist
   * @throws IllegalArgumentException      falls der übergebene Knoten bereits in
   *                                       diesem Graphen enthalten ist
   * @throws UnsupportedOperationException falls das Hinzufügen von Knoten von
   *                                       diesem Graphen nicht unterstützt wird
   */
  void addNode(V node);

  /**
   * Entfernt einen Knoten aus diesem Graphen.
   * Gleichzeitig werden alle vom zu entfernenden Knoten ausgehenden Kanten
   * aus diesem Graphen gelöscht.
   *
   * @param node der Knoten, der entfernt werden soll
   * @throws NullPointerException             falls der zu entfernende Knoten {@code null}
   *                                          ist
   * @throws java.util.NoSuchElementException falls der übergebene Knoten
   *                                          nicht in diesem Graphen enthalten ist
   * @throws UnsupportedOperationException    falls das Entfernen von Knoten von
   *                                          diesem Graphen nicht unterstützt wird
   */
  void removeNode(V node);

  /**
   * Verbindet zwei Knoten mit einer Kante.
   *
   * @param from   der Knoten von dem aus die neue Kante ausgehen soll
   * @param weight das Gewicht der neuen Kante
   * @param to     der Knoten, auf den die neue Kante zeigen soll
   * @throws NullPointerException             falls für einen der Parameter {@code null}
   *                                          übergeben wird
   * @throws java.util.NoSuchElementException falls einer der beiden Knoten
   *                                          nicht in diesem Graphen existiert
   * @throws IllegalArgumentException         falls bereits eine Kante vom ersten zum
   *                                          zweiten Knoten existiert
   * @throws UnsupportedOperationException    falls das Hinzufügen von Kanten von
   *                                          diesem Graphen nicht unterstützt wird
   */
  void connectNodes(V from, A weight, V to);

  /**
   * Entfernt die bestehende Kante zwischen zwei Knoten.
   *
   * @param from der Knoten, von dem die zu löschende Kante ausgeht
   * @param to   der Knoten, auf den die zu löschende Kante zeigt
   * @throws NullPointerException             falls für einen der beiden Knoten
   *                                          {@code null} als Parameter übergeben wird
   * @throws java.util.NoSuchElementException falls einer der beiden Knoten
   *                                          nicht in diesem Graphen existiert oder falls keine Kante vom
   *                                          ersten zum zweiten Knoten existiert
   * @throws UnsupportedOperationException    falls das Entfernen von Kanten von
   *                                          diesem Graphen nicht unterstützt wird
   */
  void disconnectNodes(V from, V to);
}
