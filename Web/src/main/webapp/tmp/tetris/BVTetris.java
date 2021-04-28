package by.gourianova.apptrainer.entity.tetris;

import by.gourianova.apptrainer.entity.tetris.ui.Window;

public class BVTetris {
    public void run(){
        Window window=new Window();
        window.run();
        javax.swing.SwingUtilities.invokeLater(window);
    }
}
