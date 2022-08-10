package com.zetcode.decorador;

import com.zetcode.componente.Snake;

public abstract class DecoredSnake extends Snake {
    protected Snake cSnake;

    public Snake getSnake() {
        return cSnake;
    }
}
