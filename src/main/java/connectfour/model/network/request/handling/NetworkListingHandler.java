package connectfour.model.network.request.handling;

import connectfour.model.network.ProtocolKeywords;
import java.io.DataOutputStream;
import java.io.IOException;

public class NetworkListingHandler implements RequestHandler {

    private final DataOutputStream streamOut;

    NetworkListingHandler(DataOutputStream streamOut) {
        this.streamOut = streamOut;
    }

    @Override
    public void handle() throws IOException {
        this.streamOut.writeBytes(ProtocolKeywords.AvailableNetworkPlayerListingAnswer + "\n");
        this.streamOut.flush();
    }
}