package h07.algebra;

/**
 * Modelliert eine binäre Operation über einem Datentypen mit neutralem Element.
 *
 * @param <M> die Trägermenge des Monoids
 */
public interface Monoid<M> {
  /**
   * Liefert das neutrale Element dieses Monoids.
   *
   * @return das neutrale Element dieses Monoids
   */
  M zero();

  /**
   * Die Verknüpfung zweier Operanden durch das Monoid.
   *
   * @param a der erste Operand
   * @param b der zweite Operand
   * @return das Ergebnis der Verknüpfung beider Operanden
   */
  M add(M a, M b);
}
