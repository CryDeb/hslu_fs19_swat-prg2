package connect4.views.interfaces;

public interface GameViewListener {
	void DiskColumnPressed(int column);
	void SaveGamePressed();
	void CloseGamePressed();
}
