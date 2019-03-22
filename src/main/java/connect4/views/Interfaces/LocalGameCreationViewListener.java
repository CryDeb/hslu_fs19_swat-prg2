package connect4.views.Interfaces;

public interface LocalGameCreationViewListener {
    void ResumeGamePressed();
    void NewGamePressed(int width, int height);
    void BackPressed();
}
