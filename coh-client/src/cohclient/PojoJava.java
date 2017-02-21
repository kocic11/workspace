package cohclient;

import com.tangosol.util.ExternalizableHelper;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

public class PojoJava implements Serializable {
  //com.tangosol.io.ExternalizableLite {
  private String str;
  private long longVal;
  private int intVal1;
  private int intVal2;
  private int intVal3;

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

  public PojoJava() {
    super();
  }

  public PojoJava(String str, long longVal, int intVal1, int intVal2, int intVal3) {
    this.str = str;
    this.longVal = longVal;
    this.intVal1 = intVal1;
    this.intVal2 = intVal2;
    this.intVal3 = intVal3;
  }

  public PojoJava(String str) {
    this.str = str;
  }

  public void setStr(String str) {
    this.str = str;
  }

  public String getStr() {
    return str;
  }

  public void readExternal(DataInput dataInput) throws IOException {
    str = new String(ExternalizableHelper.read(dataInput));
  }

  public void writeExternal(DataOutput dataOutput) throws IOException {
    ExternalizableHelper.writeByteArray(dataOutput, str.getBytes());
  }

  @Override
  public String toString() {
    return str;
  }
}
