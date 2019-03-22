package connect4.views.Interfaces;

import java.util.List;

public interface NetworkView extends ListenerSetter<NetworkViewListener> {
    void showAvailablePlayers(List<String> allPlayers);
}
