package com.zetcode.componente;

import java.awt.*;

public abstract class Snake {
    public abstract int 	getSnakeDots() ;
    public abstract void 	setSnakeDots(int dots) ;
    public abstract int 	getSnake_x(int id) ;
    public abstract int 	getSnake_y(int id);
    public abstract void 	setSnake_x(int id, int pos_x) ;
    public abstract void 	setSnake_y(int id, int pos_x);
    public abstract Image   getBall();
    public abstract Image   getHead();
    public abstract boolean getDecorated();
    public abstract void setDecorated(boolean decorated);
}
