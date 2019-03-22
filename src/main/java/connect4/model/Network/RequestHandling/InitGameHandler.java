package connect4.model.Network.RequestHandling;

import connect4.controller.Navigator;
import connect4.model.Network.ProtocolKeywords;
import java.io.DataOutputStream;
import java.io.IOException;

public class InitGameHandler implements RequestHandler {

    private final DataOutputStream streamOut;
    private final String opponentHost;
    private final Navigator navigator;

    public InitGameHandler(DataOutputStream streamOut, String opponentHost, Navigator navigator) {
        this.streamOut = streamOut;
        this.opponentHost = opponentHost;
        this.navigator = navigator;
    }

    @Override
    public void handle() throws IOException {
        this.streamOut.writeBytes(ProtocolKeywords.InitGameAnswer + "\n");
        this.streamOut.flush();

        this.navigator.navigateToGameViewForAcceptingNetworkPlay(this.opponentHost);
    }
}
