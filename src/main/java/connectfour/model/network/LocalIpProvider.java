package connectfour.model.network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

class LocalIpProvider {
    private LocalIpProvider(){}
    public static Comparator<InetAddress> inetComperator = new InetAddressSorterByIsSiteLocal();

    public static InetAddress getLocalhostLANAddress() throws UnknownHostException {
        try {
            Optional<InetAddress> myNetAddress = Collections.list(NetworkInterface.getNetworkInterfaces()).stream()
                    .filter(iface -> "docker0".equals(iface.getName()))
                    .flatMap(iface -> Collections.list(iface.getInetAddresses()).stream())
                    .filter(inetAddress -> inetAddress.isLoopbackAddress())
                    .sorted(inetComperator)
                    .findFirst();
            return myNetAddress.orElse(InetAddress.getLocalHost());
        } catch (Exception e) {
            UnknownHostException unknownHostException = new UnknownHostException("Failed to determine LAN address: " + e);
            unknownHostException.initCause(e);
            throw unknownHostException;
        }
    }

    private static class InetAddressSorterByIsSiteLocal implements Comparator<InetAddress> {
        @Override
        public int compare(InetAddress firstAddress, InetAddress secondAddress) {
            final boolean firstSiteLocal = firstAddress.isSiteLocalAddress();
            final boolean secondSiteLocal = secondAddress.isSiteLocalAddress();

            if (firstSiteLocal ^ secondSiteLocal) {
                return 0;
            }
            return (firstSiteLocal ? 1 : -1);
        }
    }
}
