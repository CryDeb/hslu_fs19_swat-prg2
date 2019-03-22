package connect4.model.network.request.handling;

import java.io.IOException;

public interface RequestHandler {
    void handle() throws IOException;
}
