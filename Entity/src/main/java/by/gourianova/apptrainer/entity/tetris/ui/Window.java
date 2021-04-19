package by.gourianova.apptrainer.entity.tetris.ui;

import by.gourianova.apptrainer.entity.tetris.model.Coordinates;
import by.gourianova.apptrainer.entity.tetris.service.PlayFigure;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Window extends JFrame implements Runnable {
    private final by.gourianova.apptrainer.entity.tetris.ui.Box[][] boxes;
    private PlayFigure play;

    public PlayFigure getPlay() {
        return play;
    }

    public void setPlay(PlayFigure play) {
        this.play = play;
    }

    public by.gourianova.apptrainer.entity.tetris.ui.Box[][] getBoxes() {
        return boxes;
    }

    public Window() {
        boxes = new by.gourianova.apptrainer.entity.tetris.ui.Box[Config.WIDTH][Config.HEIGHT];
        initForm();
        initBoxes();
        addKeyListener(new KeyAdapter());
        play = new PlayFigure();
        play.setBoxes(boxes);

        TimeAdapter timeAdapter = new TimeAdapter();
        Timer timer = new Timer(1000, timeAdapter);
        timer.start();
        showFigure(1);

    }


    public void initBoxes() {
        for (int x = 0; x < Config.WIDTH; x++) {
            for (int y = 0; y < Config.HEIGHT; y++) {
                boxes[x][y] = new by.gourianova.apptrainer.entity.tetris.ui.Box(x, y);
                add(boxes[x][y]);
            }
        }
    }

    public void setBoxColor(int x, int y, int color) {
        if (x < 0 || x >= Config.WIDTH) return;
        if (y < 0 || y >= Config.HEIGHT) return;
        boxes[x][y].setColor(color);
    }

    public void addFigure(PlayFigure play) {
        setPlay(play);

    }


    public void initForm() {
        setSize(Config.WIDTH * Config.HEIGHT + Config.SIZE, Config.HEIGHT * Config.HEIGHT + Config.HEIGHT + Config.WIDTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setTitle("BinocularVisionTetris");
        setVisible(true);

    }

    public void movePlay(int sx, int sy) {
        hideFigure();
        play.moveFigure(sx, sy);
        showFigure(1);
    }

    public void showFigure(int color) {
        for (Coordinates dots : play.getFigures().dots) {
            setBoxColor(play.getCoordinates().x + dots.x, play.getCoordinates().y + dots.y, color);
        }
    }

    private void hideFigure() {
        showFigure(0);
    }

    public void run() {
        repaint();
    }

    private void stripLines() {
        for (int y = Config.HEIGHT - 1; y >= 0; y--) {
            while (isFullLine(y)) {
                dropLine(y);
            }
        }
    }

    private void dropLine(int y) {
        for (int my = y - 1; my > 0; my--) {
            for (int x = 0; x < Config.WIDTH; x++) {
                setBoxColor(x, my + 1, getBoxColor(x, my));
            }
        }
        for (int x = 0; x < Config.WIDTH; x++) {
            setBoxColor(x, 0, 0);
        }
    }

    private boolean isFullLine(int y) {
        for (int x = 0; x < Config.WIDTH; x++) {
            if (getBoxColor(x, y) != 2) {
                return false;
            }
        }
        return true;
    }

    private int getBoxColor(int x, int y) {
        return boxes[x][y].getColor();
    }


    class KeyAdapter implements KeyListener {
        public void keyTyped(KeyEvent e) {

        }

        public void keyPressed(KeyEvent e) {
            hideFigure();
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    movePlay(-1, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    movePlay(+1, 0);
                    break;
                case KeyEvent.VK_DOWN:
                    movePlay(0, +1);
                    break;
                case KeyEvent.VK_UP:
                    movePlay(0, -1);
                    break;
                case KeyEvent.VK_SPACE:
                    play.turnFigure();
            }
            showFigure(1);
        }

        public void keyReleased(KeyEvent e) {
        }

    }

    public class TimeAdapter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            play.moveFigure(0, 1);
            if (play.isLanded()) {
                play = new PlayFigure();
                play.setBoxes(boxes);
                addFigure(play);
                for (int i = 0; i < boxes.length; i++)
                    for (int j = 0; j < boxes[i].length; j++)
                        if (boxes[i][j].getColor() != 0) boxes[i][j].setColor(2);
            }
            stripLines();
        }
    }

}


