package cohclient;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import java.util.HashMap;
import java.util.Map;

public class TestCoh {

  private static final String str = "a";

  public TestCoh() {
    super();
  }

  public static void main(String[] args) throws Exception {
    TestCoh testCoh = new TestCoh();
    if (args != null && args.length < 2) {
      System.out.println("Usage: java TestCoh java|pof <size>");
      System.exit(1);
    }
    int size = Integer.valueOf(args[1]);
    switch (args[0]) {
    case "java":
      System.out.println("Running Java serialization.");
      testCoh.putAllJava(size);
      break;
    case "pof":
      System.setProperty("tangosol.pof.config", "cob-pof-config.xml");
      System.out.println("Running POF serialization.");
      testCoh.putAllPof(size);
      break;
    default:
      System.out.println("Usage: java TestCoh java|pof <size>");
    }
  }

  //  private void putPof(String key, Pojo value) {
  //    NamedCache cache = CacheFactory.getCache("ClientonboardingPof-1");
  //    Map<String, Pojo> values = new HashMap<String, Pojo>();
  //    values.put(key, value);
  //    long start = System.currentTimeMillis();
  //    cache.putAll(values);
  //    long time = System.currentTimeMillis() - start;
  //    System.out.println("Time to put POF string: " + time);
  //  }

  private void putAllJava(int size) {
    PojoJava value = new PojoJava(str, 1L, 1, 2, 3);
    putAll(size, value, "ClientonboardingJava-1");
  }

  private void putAllPof(int size) {
    Pojo value = new Pojo(str, 1L, 1, 2, 3);
    putAll(size, value, "ClientonboardingPof-1");
  }

  private void putAll(int size, Object value, String cacheName) {
    NamedCache cache = CacheFactory.getCache(cacheName);
    Map<String, Object> values = new HashMap<String, Object>();
    long time = 0;
    for (int i = 1; i < size + 1; i++) {
      values.put("key" + i, value);
      if (i % 1000 == 0) {
        long start = System.currentTimeMillis();
        System.out.println("Put: " + values.size());
        cache.putAll(values);
        time += System.currentTimeMillis() - start;

        values.clear();
      }
    }
    if (values.size() > 0) {
      long start = System.currentTimeMillis();
      System.out.println("Put: " + values.size());
      cache.putAll(values);
      time += System.currentTimeMillis() - start;
    }
    System.out.println("Time to put POF object(s): " + time);
  }

  //
  //  private void putJava(String key, PojoJava value) {
  //    NamedCache cache = CacheFactory.getCache("ClientonboardingJava-1");
  //    Map<String, PojoJava> values = new HashMap<String, PojoJava>();
  //    values.put(key, value);
  //    long start = System.currentTimeMillis();
  //    cache.putAll(values);
  //    long time = System.currentTimeMillis() - start;
  //    System.out.println("Time to put Java string: " + time);
  //  }
  //
  //  private Object getPof(String key) {
  //    NamedCache cache = CacheFactory.getCache("ClientonboardingPof-1");
  //    return cache.get(key);
  //  }
  //
  //  private Object getJava(String key) {
  //    NamedCache cache = CacheFactory.getCache("ClientonboardingJava-1");
  //    return cache.get(key);
  //  }
  //
  //  private void testJava(int size) {
  //    char[] value = getValue(size);
  //    putJava("key2", new PojoJava(new String(value)));
  //    getJava("key2");
  //    System.out.println("Received value.");
  //  }
  //
  //  private void testJava() {
  //    putJava("key2", new PojoJava("qwertyuiop", 1L, 1, 2, 3));
  //    getJava("key2");
  //    System.out.println("Received value.");
  //  }
  //
  //  private void testPof(int size) {
  //    char[] value = getValue(size);
  //    putPof("key1", new Pojo(new String(value)));
  //    System.out.println(getPof("key1"));
  //  }
  //
  //  private void testPof() {
  //    Map<String, Pojo> map = new HashMap<String, Pojo>();
  //
  //    putPof("key1", new Pojo("qwertyuiop", 1L, 1, 2, 3));
  //    getPof("key1");
  //    System.out.println("Received value.");
  //  }
  //
  //  private char[] getValue(int size) {
  //    char[] value = new char[size];
  //    for (int i = 0; i < size; i++) {
  //      value[i] = (char) i;
  //    }
  //    return value;
  //  }
}
