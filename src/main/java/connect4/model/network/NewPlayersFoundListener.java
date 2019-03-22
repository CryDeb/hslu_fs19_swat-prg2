package connect4.model.network;

import java.util.List;

public  interface NewPlayersFoundListener {

	void NewPlayersFound(List<String> newPlayers);
}
