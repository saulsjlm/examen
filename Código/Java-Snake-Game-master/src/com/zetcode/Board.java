package com.zetcode;

import com.zetcode.componente_concreto.SnakeConcrete;
import com.zetcode.decorador.DecoredSnake;
import com.zetcode.decorador_concreto.OranceSnake;
import com.zetcode.decorador_concreto.PurpleSnake;
import com.zetcode.prototipo.Apple;
import com.zetcode.componente.Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private ArrayList<Apple> arrApples = new ArrayList<Apple>();
    private ArrayList<Snake> global_array_snake = new ArrayList<Snake>();
    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private Apple applePrototipe;
    private Image appleImage;
    private int apple_x;
    private int apple_y;
    private int apple_score = 1;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;

    public Board() {
        
        initBoard();
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();

        applePrototipe = new Apple(1, appleImage, apple_x, apple_y, apple_score);
        this.newApple();
        this.updateCloneApple();
        this.newSnake();
    }

    private void loadImages() {
        ImageIcon iia = new ImageIcon("src/resources/appleRed.png");
        appleImage = iia.getImage();
    }

    private void initGame() {

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void newApple () {
        arrApples.add(applePrototipe.clone());
    }

    private void updateCloneApple() {
        Apple currentApple = this.getCurentApple(0);
        currentApple.setImagePath(appleImage);
        currentApple.setApple_x(apple_x);
        currentApple.setApple_y(apple_y);
        currentApple.setScore(apple_score);
    }

    private Apple getCurentApple (int id) {
        return arrApples.get(id);
    }

    public void newSnake () {
        Snake currentSnake = new SnakeConcrete();
        for (int z = 0; z < currentSnake.getSnakeDots(); z++) {
            int pos_x = 50 - z * 10;
            int pos_y = 50;
            currentSnake.setSnake_x(z, pos_x);
            currentSnake.setSnake_y(z, pos_y);
        }
        this.global_array_snake.add(currentSnake);
    }

    public void decorateSnake (int idSnake, int pDecorationType) {

        Snake currentSnake = this.getCurentSnake(idSnake);
        switch(pDecorationType) {
            case 1:
                currentSnake.setDecorated(true);
                replaceSnake_Array(idSnake,new PurpleSnake(currentSnake));
                break;
            case 2:
                currentSnake.setDecorated(true);
                replaceSnake_Array(idSnake,new OranceSnake(currentSnake));
                break;
            case 3:
                currentSnake.setDecorated(false);
                break;
        }
    }

    public void deleteDecoration (int idSnake) {
        Snake currentSake = getCurentSnake(idSnake);

        if (currentSake.getDecorated()) {
            DecoredSnake pDecorada = (DecoredSnake) currentSake;
            Snake snakeL = pDecorada.getSnake();
            replaceSnake_Array(idSnake,snakeL);
        }
    }

    private Snake getCurentSnake (int id) {
        return global_array_snake.get(id);
    }

    public void replaceSnake_Array(int pId, DecoredSnake pP) {
        this.global_array_snake.set(pId, pP);
    }

    public void replaceSnake_Array(int pId, Snake pP) {
        this.global_array_snake.set(pId, pP);
    }

    private void newAppleValues() {
        this.locateApple();
        int appleOption = (int) (Math.random() * 3) + 1;
        ImageIcon image;

        switch (appleOption){
            case 1:
                this.apple_score = 1;
                image = new ImageIcon("src/resources/appleRed.png");
                break;
            case 2:
                this.apple_score = 2;
                image = new ImageIcon("src/resources/appleBlue.png");
                break;
            case 3:
                this.apple_score = 3;
                image = new ImageIcon("src/resources/appleYellow.png");
                break;
            default:
                this.apple_score = 1;
                image = new ImageIcon("src/resources/apple.png");
                break;
        }
        appleImage = image.getImage();
    }
    
    private void doDrawing(Graphics g) {
        if (inGame) {
            Apple apple = this.getCurentApple(0);
            Snake snake = this.getCurentSnake(0);
            g.drawImage(apple.getImagePath(), apple.getApple_x(), apple.getApple_y(), this);

            for (int z = 0; z < snake.getSnakeDots(); z++) {
                if (z == 0) {
                    g.drawImage(snake.getHead(), snake.getSnake_x(z), snake.getSnake_y(z), this);
                } else {
                    g.drawImage(snake.getBall(), snake.getSnake_x(z), snake.getSnake_y(z), this);
                }
            }

            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checkApple() {
        Apple apple = this.getCurentApple(0);
        Snake snake = this.getCurentSnake(0);

        if ((snake.getSnake_x(0) == apple.getApple_x()) && (snake.getSnake_y(0) == apple.getApple_y())) {

            snake.setSnakeDots(snake.getSnakeDots()+1);
            this.deleteDecoration(0);
            this.decorateSnake(0, getCurentApple(0).getScore());
            this.newAppleValues();
            this.updateCloneApple();
        }
    }

    private void move() {
        Snake snake = this.getCurentSnake(0);
        for (int z = snake.getSnakeDots(); z > 0; z--) {
            int pos_x = snake.getSnake_x((z - 1));
            int pos_y = snake.getSnake_y((z - 1));
            snake.setSnake_x(z, pos_x);
            snake.setSnake_y(z, pos_y);
        }
        if (leftDirection) {
            int value = snake.getSnake_x(0);
            snake.setSnake_x(0, value -= DOT_SIZE);
        }
        if (rightDirection) {
            int value = snake.getSnake_x(0);
            snake.setSnake_x(0, value += DOT_SIZE);
        }
        if (upDirection) {
            int value = snake.getSnake_y(0);
            snake.setSnake_y(0, value -= DOT_SIZE);
        }
        if (downDirection) {
            int value = snake.getSnake_y(0);
            snake.setSnake_y(0, value += DOT_SIZE);
        }
    }

    private void checkCollision() {
        Snake snake = this.getCurentSnake(0);
        for (int z = snake.getSnakeDots(); z > 0; z--) {
            if ((z > 4) && (snake.getSnake_x(0) == snake.getSnake_x(z)) && (snake.getSnake_y(0) == snake.getSnake_y(z))) {
                inGame = false;
            }
        }
        if (snake.getSnake_y(0) >= B_HEIGHT) {
            inGame = false;
        }
        if (snake.getSnake_y(0) < 0) {
            inGame = false;
        }
        if (snake.getSnake_x(0) >= B_WIDTH) {
            inGame = false;
        }
        if (snake.getSnake_x(0) < 0) {
            inGame = false;
        }
        if (!inGame) {
            timer.stop();
        }
    }

    private void locateApple() {
        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}
