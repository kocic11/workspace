package cohclient;

import com.tangosol.net.Service;

import javax.security.auth.Subject;

public class IdentityAsserter implements com.tangosol.net.security.IdentityAsserter {
  public IdentityAsserter() {
    super();
  }

  @Override
  public Subject assertIdentity(Object object, Service service) throws SecurityException {
    // TODO Implement this method
    System.out.println("assertIdentity method." + "object = " + object + ", service = " + service);
    return null;
  }
}
