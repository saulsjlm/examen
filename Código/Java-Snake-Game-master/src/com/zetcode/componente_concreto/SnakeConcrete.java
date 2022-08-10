package com.zetcode.componente_concreto;

import com.zetcode.componente.Snake;

import javax.swing.*;
import java.awt.*;

public class SnakeConcrete extends Snake {

    final int ALL_DOTS = 900;
    final int snake_x[] = new int[ALL_DOTS];
    final int snake_y[] = new int[ALL_DOTS];
    int dots;
    Image ball;
    Image head;
    boolean decorated;

    public SnakeConcrete() {
        ImageIcon ballImage = new ImageIcon("src/resources/dot.png");
        ImageIcon headImage = new ImageIcon("src/resources/head.png");

        this.dots = 3;
        this.ball = ballImage.getImage();
        this.head = headImage.getImage();
        this.decorated = false;
    }

    @Override
    public int getSnakeDots() {
        return this.dots;
    }

    @Override
    public void setSnakeDots(int dots) {
        this.dots = dots;
    }

    @Override
    public int getSnake_x(int id) {
        return this.snake_x[id];
    }

    @Override
    public int getSnake_y(int id) {
        return this.snake_y[id];
    }

    @Override
    public void setSnake_x(int id, int pos_x) {
        this.snake_x[id] = pos_x;
    }

    @Override
    public void setSnake_y(int id, int pos_y) {
        this.snake_y[id] = pos_y;
    }

    @Override
    public Image getBall() {
        return this.ball;
    }

    @Override
    public Image getHead() {
        return this.head;
    }

    @Override
    public boolean getDecorated() {
        return this.decorated;
    }

    @Override
    public void setDecorated(boolean decorated) {
        this.decorated = decorated;
    }
}