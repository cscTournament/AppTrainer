package by.gourianova.apptrainer.entity.tetris.service;

import by.gourianova.apptrainer.entity.tetris.model.Coordinates;
import by.gourianova.apptrainer.entity.tetris.model.Figures;
import by.gourianova.apptrainer.entity.tetris.ui.Box;
import by.gourianova.apptrainer.entity.tetris.ui.Config;

public class PlayFigure {
    private Figures figures;
    private Coordinates coordinates;
    private Box[][] boxes;
    private boolean isLanded;

    public boolean isLanded() {
        return isLanded;
    }

    public void setLanded(boolean quest) {
        this.isLanded = quest;
    }

    public Figures getFigures() {
        return figures;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setBoxes(Box[][] boxes) {
        this.boxes = boxes;
    }

    public PlayFigure() {
        figures = Figures.getRandom();
        coordinates = new Coordinates(Config.WIDTH / 2, -figures.top.y);
    }


    public void showFigure(Figures figures, Coordinates at, int color) {
        for (Coordinates dot : figures.dots) {
            setBoxColor(at.x + dot.x, at.y + dot.y, color);
        }
    }

    public void setBoxColor(int x, int y, int color) {
        if (x < 0 || x >= Config.WIDTH) return;
        if (y < 0 || y >= Config.HEIGHT) return;
        boxes[x][y].setColor(color);
    }

    private boolean canMoveFigure(int sx, int sy) {
        int left = coordinates.x + sx + figures.top.x;
        if (left < 0) return false;
        int right = coordinates.x + sx + figures.bottom.x;
        if (right >= Config.WIDTH) return false;
        int down = coordinates.y + sy + figures.top.y;
        if (down < 0) {
            return false;
        }
        int up = coordinates.y + sy + figures.bottom.y;

        if ((up >= Config.HEIGHT) || (boxes[coordinates.x + sx + 1][coordinates.y + sy + 1].getColor() == 2)) {
            setLanded(true);

            return false;
        }

        for (Coordinates dot : figures.dots)
            if (boxes[coordinates.x + dot.x + sx][coordinates.y + dot.y + sy].getColor() != 0) {
                setLanded(true);
                return false;
            }

        return true;
    }

    public void moveFigure(int sx, int sy) {
        hideFigure();
        if (canMoveFigure(sx, sy)) {
            coordinates = coordinates.plus(sx, sy);
        }
        showFigure();
    }

    public void turnFigure() {
        Figures rotated = figures.turnRight();
        if (canMoveFigure(0, 0)) {
            figures = rotated;
        } else if (canMoveFigure(1, 0)) {
            figures = rotated;
            moveFigure(1, 0);
        } else if (canMoveFigure(-1, 0)) {
            figures = rotated;
            moveFigure(-1, 0);
            figures = rotated;
        }
    }


    private void showFigure() {
        showFigure(figures, coordinates, 1);
    }

    private void hideFigure() {
        showFigure(figures, coordinates, 0);
    }


}



