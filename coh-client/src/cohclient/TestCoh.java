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

}
