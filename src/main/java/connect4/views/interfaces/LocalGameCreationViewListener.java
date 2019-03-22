package connect4.views.interfaces;

public interface LocalGameCreationViewListener {
    void ResumeGamePressed();
    void NewGamePressed(int width, int height);
    void BackPressed();
}
