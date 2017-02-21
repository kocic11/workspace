package main.java.cohgopal.client;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import java.io.IOException;

public class App {

  public App() {
    super(); 
  }

  public static void main(String[] args) {
    App App = new App();

    App.putJava("key2", "value2");
    try {
      System.out.println("Put-1");
      System.in.read();
    } catch (IOException e) {
    }
    System.out.println(App.getJava("key2"));
    try {
      System.in.read();
    } catch (IOException e) {
    }
    
    App.putJava("key3", "value3");
    try {
      System.out.println("Put-2");
      System.in.read();
    } catch (IOException e) {
    }
    System.out.println(App.getJava("key3"));
    try {
      System.in.read();
    } catch (IOException e) {
    }

  }

  private void putJava(String key, String value) {
    NamedCache<String, String> cache = CacheFactory.getCache("STSNoTransFeeLkup");
    cache.put(key, value);
  }

  private String getJava(String key) {
    NamedCache<String, String> cache = CacheFactory.getCache("STSNoTransFeeLkup");
    return cache.get(key);
  }
}
