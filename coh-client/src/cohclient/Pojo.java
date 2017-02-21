package cohclient;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;

@Portable
public class Pojo {
  @PortableProperty(0)
  private String str;
  @PortableProperty(1)
  private long longVal;
  @PortableProperty(2)
  private int intVal1;
  @PortableProperty(3)
  private int intVal2;
  @PortableProperty(4)
  private int intVal3;

  public Pojo() {
    super();
  }

  public Pojo(String str, long longVal, int intVal1, int intVal2, int intVal3) {
    this.str = str;
    this.longVal = longVal;
    this.intVal1 = intVal1;
    this.intVal2 = intVal2;
    this.intVal3 = intVal3;
  }

  public void setLongVal(long longVal) {
    this.longVal = longVal;
  }

  public long getLongVal() {
    return longVal;
  }

  public void setIntVal1(int intVal1) {
    this.intVal1 = intVal1;
  }

  public int getIntVal1() {
    return intVal1;
  }

  public void setIntVal2(int intVal2) {
    this.intVal2 = intVal2;
  }

  public int getIntVal2() {
    return intVal2;
  }

  public void setIntVal3(int intVal3) {
    this.intVal3 = intVal3;
  }

  public int getIntVal3() {
    return intVal3;
  }

  public Pojo(String str) {
    this.str = str;
  }

  public void setStr(String str) {
    this.str = str;
  }

  public String getStr() {
    return str;
  }

  @Override
  public String toString() {
    // TODO Implement this method
    return str;
  }
}
