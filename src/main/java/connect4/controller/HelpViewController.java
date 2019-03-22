package connect4.controller;

import connect4.views.interfaces.HelpViewListener;

public class HelpViewController implements HelpViewListener {

    private final Navigator navigator;

    HelpViewController(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void BackPressed() {
        this.navigator.navigateToStartView();
    }

    void init() {
    }
}
