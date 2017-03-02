import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JmxClient {
  private static final String USER = "user";
  private static final String PASSWORD = "password";
  private static final String PROTOCOL_PROVIDER_PACKAGES = "weblogic.management.remote";

  public JmxClient() {
    super();
  }

  public static void main(String[] args) throws Exception {
    //    String jmxServiceURL = "service:jmx:iiop://localhost.localdomain:7003/jndi/weblogic.management.mbeanservers.runtime";
    //    String appName = "clientonboarding-cache-1.0-SNAPSHOT";

    if (args != null && args.length < 3) {
      System.out.println("Usage: java JmxClient serviceURL appName properties");
      System.exit(1);
    }

    String jmxServiceURL = args[0];
    String appName = args[1];


    JmxClient jmxClient = new JmxClient();
    Properties prop = jmxClient.getPropValues(args[2]);

    JMXServiceURL url = new JMXServiceURL(jmxServiceURL);
    String[] creds = { prop.getProperty(USER), prop.getProperty(PASSWORD) };

    Map<String, Object> env = new HashMap<String, Object>();
    env.put(JMXConnector.CREDENTIALS, creds);
    env.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES, PROTOCOL_PROVIDER_PACKAGES);
    JMXConnector jmxc = JMXConnectorFactory.connect(url, env);

    MBeanServerConnection mBeanServerConnection = jmxc.getMBeanServerConnection();
    System.out.println("Invoking resetStatistics() method on:");
    jmxClient.resetStatistics(mBeanServerConnection, new StringBuffer("Coherence:type=*,name=").append("\"")
                                                                                               .append(appName)
                                                                                               .append(":*\",*")
                                                                                               .toString());

    jmxClient.resetStatistics(mBeanServerConnection, new StringBuffer("Coherence:type=*,service=").append("\"")
                                                                                                  .append(appName)
                                                                                                  .append(":*\",*")
                                                                                                  .toString());
  }

  private Properties getPropValues(String fileName) throws Exception {
    InputStream inputStream = null;
    Properties prop = new Properties();
    try {
      String propFileName = fileName;
      inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
      if (inputStream != null) {
        prop.load(inputStream);
      } else {
        throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
      }
    } finally {
      inputStream.close();
    }
    return prop;
  }

  private void resetStatistics(MBeanServerConnection mBeanServerConnection, String name) throws IOException,
                                                                                                MalformedObjectNameException {
    ObjectName serviceInfo = ObjectName.getInstance(name);
    Set<ObjectName> names = mBeanServerConnection.queryNames(serviceInfo, null);
    if (names == null || names.isEmpty()) {
      throw new RuntimeException("No services found.");
    }

    for (ObjectName mBeanName : names) {
      try {
        System.out.println(mBeanName);
        mBeanServerConnection.invoke(mBeanName, "resetStatistics", null, null);
      } catch (Exception e) {
      }
    }
  }
}
