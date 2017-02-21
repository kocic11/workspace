package cohclient;

import com.tangosol.net.Service;

import javax.security.auth.Subject;

public class IdentityTransformer implements com.tangosol.net.security.IdentityTransformer {
  public IdentityTransformer() {
    super();
  }

  @Override
  public Object transformIdentity(Subject subject, Service service) throws SecurityException {
    // TODO Implement this method
    System.out.println("transformIdentity method. subject = " + subject + ", service = " + service);
    return "clientName=TestCoh";
  }
}
