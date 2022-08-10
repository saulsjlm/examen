package com.zetcode.decorador_concreto;

import com.zetcode.componente.Snake;
import com.zetcode.decorador.DecoredSnake;

import javax.swing.*;
import java.awt.*;

public class PurpleSnake extends DecoredSnake {

    public PurpleSnake(Snake pSnake) {
        this.cSnake = pSnake;
        this.setDecorated(true);
    }

    @Override
    public int getSnakeDots() {
        return this.cSnake.getSnakeDots();
    }

    @Override
    public void setSnakeDots(int dots) {
        this.cSnake.setSnakeDots(dots);
    }

    @Override
    public int getSnake_x(int id) {
        return this.cSnake.getSnake_x(id);
    }

    @Override
    public int getSnake_y(int id) {
        return this.cSnake.getSnake_y(id);
    }

    @Override
    public void setSnake_x(int id, int pos_x) {
        this.cSnake.setSnake_x(id, pos_x);
    }

    @Override
    public void setSnake_y(int id, int pos_y) {
        this.cSnake.setSnake_y(id, pos_y);
    }

    @Override
    public Image getBall() {
        ImageIcon ballImage = new ImageIcon("src/resources/dotPurple.png");
        return ballImage.getImage();
    }

    @Override
    public Image getHead() {
        return this.cSnake.getHead();
    }

    @Override
    public boolean getDecorated() {
        return this.cSnake.getDecorated();
    }

    @Override
    public void setDecorated(boolean decorated) {
        this.cSnake.setDecorated(decorated);
    }
}
