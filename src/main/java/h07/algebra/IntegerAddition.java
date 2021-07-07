package h07.algebra;

public class IntegerAddition implements Monoid<Integer>{
  @Override
  public Integer zero() {
    return 0;
  }

  @Override
  public Integer add(Integer a, Integer b) {
    return a+b;
  }
}
