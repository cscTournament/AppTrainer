package by.gourianova.apptrainer.entity.tetris.model;

import java.util.ArrayList;
import java.util.List;

public enum Figures {
    I1(0, 1,  1, 1, 2, 1, 3, 1),//ooooo
    I2(   1, 0,                   // vertical orientation
                  1, 1,
                  1, 2,
                  1, 3),
    J1(   1, 0,
                  1, 1,
            0, 2, 1, 2),
    J2( 0, 0,
                0, 1, 1, 1, 2, 1),
    J3(1, 0, 2, 0,
               1, 1,
               1, 2),
    J4(0, 1, 1, 1, 2, 1,
                           2, 2),
    L1(1, 0,
               1, 1,
               1, 2, 2, 2),
    L2(0, 1, 1, 1, 2, 1,
               0, 2),
    L3(0, 0, 1, 0,
                     1, 1,
                     1, 2),
    L4(           2, 0,
              0, 1, 1, 1, 2, 1),
    O(0, 0, 1, 0,
              0, 1, 1, 1),
    S1(   1, 1, 2, 1,
            0, 2, 1, 2),
    S2(0, 0,
               0, 1, 1, 1,
                     1, 2),
    T1(0, 1, 1, 1, 2, 1,
                     1, 2),
    T2(        1, 0,
               0, 1, 1, 1,
                     1, 2),
    T3(   1, 0,
            0, 1, 1, 1, 2, 1),
    T4(1, 0,
               1, 1,   2, 1,
               1, 2),
    Z1(  0, 1, 1, 1,
                       1, 2, 2, 2),
    Z2(       2, 0,
                1, 1, 2, 1,
                1, 2);


    public List<Coordinates> dots;

    public final Coordinates top;
    public final Coordinates bottom;

    Figures(int... coords) {
        dots = new ArrayList<Coordinates>();
        for (int j = 0; j < coords.length; j += 2)
            dots.add(new Coordinates(coords[j], coords[j + 1]));
        top = setTop();
        bottom = setBottom();
    }

    private Coordinates setTop() {
        int x = dots.get(0).x;
        int y = dots.get(0).y;
        for (Coordinates coordinates : dots) {
            if (x > coordinates.x) x = coordinates.x;
            if (y > coordinates.y) y = coordinates.y;
        }
        return new Coordinates(x, y);
    }

    private Coordinates setBottom() {
        int x = dots.get(0).x;
        int y = dots.get(0).y;
        for (Coordinates coordinates : dots) {
            if (x < coordinates.x) x = coordinates.x;
            if (y < coordinates.y) y = coordinates.y;
        }
        return new Coordinates(x, y);
    }


    public Figures turnLeft() {
        return turnRight().turnRight().turnRight();


    }

    public Figures turnRight() {
        switch (this) {
            case I1:
                return I2;
            case I2:
                return I1;
            case J1:
                return J2;
            case J2:
                return J3;
            case J3:
                return J4;
            case J4:
                return J1;
            case L1:
                return L2;
            case L2:
                return L3;
            case L3:
                return L4;
            case L4:
                return L1;
            case O:
                return O;
            case S1:
                return S2;
            case S2:
                return S1;
           case T1:
                return T2;
            case T2:
                return T3;
            case T3:
                return T4;
            case T4:
                return T1;
            case Z1:
                return Z2;
            case Z2:
            default:
                return Z1;
        }
    }

    public static Figures getRandom() {
        int minAmountOfDifferentFigures = 0;
        int maxAmountOfDifferentFigures = 6;
        int random = (int) (Math.floor(Math.random() * (maxAmountOfDifferentFigures - minAmountOfDifferentFigures + 1)) + minAmountOfDifferentFigures);

        switch (random) {
            case 0:
                return Z1;
            case 1:
                return T1;
            case 2:
                return S1;
            case 3:
                return O;
            case 4:
                return L1;
            case 5:
                return J1;
            case 6:
                default:
                return I1;
        }

    }
}