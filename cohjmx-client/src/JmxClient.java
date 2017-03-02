import java.io.IOException;

import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JmxClient {

  public JmxClient() {
    super();
  }

  public static void main(String[] args) throws Exception {
    if (args != null && args.length < 2) {
      System.out.println("Usage: java JmxClient serviceURL appName");
      System.exit(1);
    }

    String jmxServiceURL = args[0];
    String appName = args[1];


    JmxClient jmxClient = new JmxClient();

    JMXServiceURL url = new JMXServiceURL(jmxServiceURL);
    JMXConnector jmxc = JMXConnectorFactory.connect(url);

    MBeanServerConnection mBeanServerConnection = jmxc.getMBeanServerConnection();
    System.out.println("Invoking resetStatistics() method on:");
    jmxClient.resetStatistics(mBeanServerConnection, new StringBuffer("Coherence:type=*,").append("name=")
                                                                                          .append("\"")
                                                                                          .append(appName)
                                                                                          .append(":*\",*")
                                                                                          .toString());

    jmxClient.resetStatistics(mBeanServerConnection, new StringBuffer("Coherence:type=*,").append("service=")
                                                                                          .append("\"")
                                                                                          .append(appName)
                                                                                          .append(":*\",*")
                                                                                          .toString());
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
