package com.zetcode.prototipo;

import com.zetcode.iPrototipo.IApple;
import java.awt.Image;

public class Apple implements IApple {
    private int id;
    private Image imagePath;
    private int apple_x;
    private int apple_y;
    private int score;

    public Apple (int id, Image image, int posX, int posY, int score) {
        this.setId(id);
        this.setImagePath(image);
        this.setApple_x(posX);
        this.setApple_y(posY);
        this.setScore(score);
    }

    public int getId() { return id; }
    public Image getImagePath() { return imagePath; }
    public void setImagePath(Image imagePath) { this.imagePath = imagePath; }
    public int getApple_x() { return apple_x; }
    public void setApple_x(int apple_x) { this.apple_x = apple_x; }
    public int getApple_y() { return apple_y; }
    public void setApple_y(int apple_y) { this.apple_y = apple_y; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score;}

    public void setId(int id) { this.id = id; }

    public Apple clone() {
        return new Apple(this.getId(), this.getImagePath(), this.getApple_x(), this.getApple_y(), this.getScore());
    }
}
