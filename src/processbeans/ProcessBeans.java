package processbeans;

import java.util.ArrayList;
import processing.core.PApplet;

/**
 *
 * @author behac6513
 */
public class ProcessBeans extends PApplet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PApplet.main("processbeans.ProcessBeans");
    }
    int seq = 1;
    int players = 2;
    int round = 1;
    boolean[] first = new boolean[4];
    boolean[] second = new boolean[4];
    float pY, pX, pX2, pY2;
    ArrayList<bracket> brackets = new ArrayList<bracket>();
    soccer s;

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        //size(500, 500);
        pY = height / 2;
        pY2 = height / 2;
    }

    public void draw() {
        if (seq == 1) {
            firstSeq();
        }
        if (seq == 2) {
            secondSeq();
            s = new soccer();
            pX = 25;
            pX2 = width - 25;
            pY = height / 2;
            pY2 = height / 2;
        }
        if (seq == 3) {
            thirdSeq();
        }
    }

    public void thirdSeq() {
        background(0);
        fill(0, 200, 0);
        rect(50, 50, width - 100, height - 100);
        s.update();
        fill(255);
        ellipse(pX, pY, 50, 50);
        ellipse(pX2, pY2, 50, 50);
        fill(0);
        textAlign(CENTER, CENTER);
        text("P1", pX, pY);
        text("P2", pX2, pY2);
        if (pX < 0) {
            pX = 0;
        }
        if (pX > width) {
            pX = width;
        }
        if (pY < 0) {
            pY = 0;
        }
        if (pY > height) {
            pY = height;
        }
        if (pX2 < 0) {
            pX2 = 0;
        }
        if (pX2 > width) {
            pX2 = width;
        }
        if (pY2 < 0) {
            pY2 = 0;
        }
        if (pY2 > height) {
            pY2 = height;
        }
        if (first[0]) {
            pX -= 5;
        }
        if (first[1]) {
            pY -= 5;
        }
        if (first[2]) {
            pY += 5;
        }
        if (first[3]) {
            pX += 5;
        }
        if (second[0]) {
            pX2 -= 5;
        }
        if (second[1]) {
            pY2 -= 5;
        }
        if (second[2]) {
            pY2 += 5;
        }
        if (second[3]) {
            pX2 += 5;
        }
    }

    public void keyPressed() {
        if (key == 'a' || key == 'A') {
            first[0] = true;
        }
        if (key == 'w' || key == 'W') {
            first[1] = true;
        }
        if (key == 's' || key == 'S') {
            first[2] = true;
        }
        if (key == 'd' || key == 'D') {
            first[3] = true;
        }
        if (keyCode == LEFT) {
            second[0] = true;
        }
        if (keyCode == UP) {
            second[1] = true;
        }
        if (keyCode == DOWN) {
            second[2] = true;
        }
        if (keyCode == RIGHT) {
            second[3] = true;
        }
    }

    public void keyReleased() {
        if (key == 'a' || key == 'A') {
            first[0] = false;
        }
        if (key == 'w' || key == 'W') {
            first[1] = false;
        }
        if (key == 's' || key == 'S') {
            first[2] = false;
        }
        if (key == 'd' || key == 'D') {
            first[3] = false;
        }
        if (keyCode == LEFT) {
            second[0] = false;
        }
        if (keyCode == UP) {
            second[1] = false;
        }
        if (keyCode == DOWN) {
            second[2] = false;
        }
        if (keyCode == RIGHT) {
            second[3] = false;
        }
    }

    public void secondSeq() {
        background(0);
        fill(155);
        rect(0,0,width/4,height);
        rect(width/4,0,width/4,height);
        rect(width/2,0,width/4,height);
        rect(width/4*3,0,width/4,height);
        fill(100);
        rect(width/2-50,25,100,50);
        fill(255);
        textAlign(CENTER, CENTER);
        text("Round " + round + " start", width / 2, 50);

        for (int i = 0; i < brackets.size(); i++) {
            brackets.get(i).update();
            if (brackets.get(i).points == 3) {
                brackets.get(i).round += 1;
                brackets.get(i).x += 100;
                brackets.get(i).y += 25;//**********************************************************************************CHANGE DEPENDING ON POSITION
                brackets.get(i).points = 0;
            }
            for (int x = 0; x <= brackets.size(); x++) {
                if (brackets.get(i).position == x) {
                    if (brackets.get(i).isPressed) {
                        brackets.get(i).isFirstPlayer = true;
                        seq = 3;
                        brackets.get(i).isPressed = false;
                        for (int j = 0; j < brackets.size(); j++) {
                            if (brackets.get(j).position == (x+1)) {
                                brackets.get(j).isSecondPlayer = true;
                            }
                        }

                    }
                }
            }
        }
    }

    public void firstSeq() {
        background(0);
        button two_player = new button(50, 50, 100, 100, "Two Player");
        two_player.update();
        if (two_player.isPressed) {
            bracket first_player = new bracket(50, 100, 100, 25, 1, "John");
            brackets.add(first_player);
            bracket second_player = new bracket(50, 150, 100, 25, 2, "Bradley");
            brackets.add(second_player);
            players = 2;
            seq = 2;
        }
        button four_player = new button(200, 50, 100, 100, "Four Player");
        four_player.update();
        if (four_player.isPressed) {
            bracket first_player = new bracket(50, 100, 100, 25, 1, "John");
            brackets.add(first_player);
            bracket second_player = new bracket(50, 150, 100, 25, 2, "Bradley");
            brackets.add(second_player);
            bracket third_player = new bracket(50, 200, 100, 25, 3, "Bohn");
            brackets.add(third_player);
            bracket fourth_player = new bracket(50, 250, 100, 25, 4, "Dradley");
            brackets.add(fourth_player);
            players = 4;
            seq = 2;
        }
        button eight_player = new button(350, 50, 100, 100, "Eight Player");
        eight_player.update();
        if (eight_player.isPressed) {
            bracket first_player = new bracket(50, 100, 100, 25, 1, "John");
            brackets.add(first_player);
            bracket second_player = new bracket(50, 150, 100, 25, 2, "Bradley");
            brackets.add(second_player);
            bracket third_player = new bracket(50, 200, 100, 25, 3, "Bohn");
            brackets.add(third_player);
            bracket fourth_player = new bracket(50, 250, 100, 25, 4, "Dradley");
            brackets.add(fourth_player);
            bracket fifth_player = new bracket(50, 300, 100, 25, 5, "Hunk");
            brackets.add(fifth_player);
            bracket sixth_player = new bracket(50, 350, 100, 25, 6, "Splone");
            brackets.add(sixth_player);
            bracket seventh_player = new bracket(50, 400, 100, 25, 7, "Swaggy");
            brackets.add(seventh_player);
            bracket eighth_player = new bracket(50, 450, 100, 25, 8, "Sporty McGee");
            brackets.add(eighth_player);
            players = 8;
            seq = 2;

        }
    }

    public class soccer {

        float nX1, nX2, nY;
        float bX, bY;
        float w, h, bD;
        float mX, mY;

        soccer() {
            nX1 = 25;
            nX2 = width - 75;
            nY = height / 2 - 75;
            w = 50;
            h = 150;
            bX = width / 2;
            bY = height / 2;
            bD = 25;
            mX = 0;
            mY = 0;
        }

        public void update() {
            fill(255);
            ellipse(nX1 + w / 2, nY + h / 2, w, h);
            ellipse(nX2 + w / 2, nY + h / 2, w, h);
            ellipse(bX, bY, bD, bD);
            bX += mX;
            bY += mY;

            fill(0);
            ellipse(bX - 5, bY + 5, bD / 3, bD / 3);
            ellipse(bX + 5, bY + 5, bD / 3, bD / 3);
            ellipse(bX + 5, bY - 5, bD / 3, bD / 3);
            ellipse(bX - 5, bY - 5, bD / 3, bD / 3);

            fill(255);
            ellipse(bX, bY + 8, bD / 3, bD / 3);
            ellipse(bX + 8, bY, bD / 3, bD / 3);
            ellipse(bX, bY - 8, bD / 3, bD / 3);
            ellipse(bX - 8, bY, bD / 3, bD / 3);

            if (bX < 0) {
                mX *= -1;
                bX = 0;
            }
            if (bX > width) {
                mX *= -1;
                bX = width;
            }
            if (bY < 0) {
                mY *= -1;
                bY = 0;
            }
            if (bY > height) {
                mY *= -1;
                bY = height;
            }
            //player one ball collision (corners indicated by black circles)
            if (dist(pX, pY, bX - 5, bY + 5) < 25 + (bD / 2) / 3) {
                if (mX < 0) {
                    mX = 0;
                }
                if (mY > 0) {
                    mY = 0;
                }
                mX += 1;
                mY -= 1;
            }
            if (dist(pX, pY, bX + 5, bY + 5) < 25 + (bD / 2) / 3) {
                if (mX > 0) {
                    mX = 0;
                }
                if (mY > 0) {
                    mY = 0;
                }
                mX -= 1;
                mY -= 1;
            }
            if (dist(pX, pY, bX - 5, bY - 5) < 25 + (bD / 2) / 3) {
                if (mX < 0) {
                    mX = 0;
                }
                if (mY < 0) {
                    mY = 0;
                }
                mX += 1;
                mY += 1;
            }
            if (dist(pX, pY, bX + 5, bY - 5) < 25 + (bD / 2) / 3) {
                if (mX > 0) {
                    mX = 0;
                }
                if (mY < 0) {
                    mY = 0;
                }
                mX -= 1;
                mY += 1;
            }
            //player one ball collision (sides indicated by white circles)
            if (dist(pX, pY, bX - 5, bY) < 25 + (bD / 2) / 3) {
                if (mX < 0) {
                    mX = 0;
                }
                mX += 1;
            }
            if (dist(pX, pY, bX + 5, bY) < 25 + (bD / 2) / 3) {
                if (mX > 0) {
                    mX = 0;
                }
                mX -= 1;
            }
            if (dist(pX, pY, bX, bY - 5) < 25 + (bD / 2) / 3) {
                if (mY < 0) {
                    mY = 0;
                }
                mY += 1;
            }
            if (dist(pX, pY, bX, bY + 5) < 25 + (bD / 2) / 3) {
                if (mY > 0) {
                    mY = 0;
                }
                mY -= 1;
            }
            //player two ball collision (corners indicated by black circles)
            if (dist(pX2, pY2, bX - 5, bY + 5) < 25 + (bD / 2) / 3) {
                if (mX < 0) {
                    mX = 0;
                }
                if (mY > 0) {
                    mY = 0;
                }
                mX += 1;
                mY -= 1;
            }
            if (dist(pX2, pY2, bX + 5, bY + 5) < 25 + (bD / 2) / 3) {
                if (mX > 0) {
                    mX = 0;
                }
                if (mY > 0) {
                    mY = 0;
                }
                mX -= 1;
                mY -= 1;
            }
            if (dist(pX2, pY2, bX - 5, bY - 5) < 25 + (bD / 2) / 3) {
                if (mX < 0) {
                    mX = 0;
                }
                if (mY < 0) {
                    mY = 0;
                }
                mX += 1;
                mY += 1;
            }
            if (dist(pX2, pY2, bX + 5, bY - 5) < 25 + (bD / 2) / 3) {
                if (mX > 0) {
                    mX = 0;
                }
                if (mY < 0) {
                    mY = 0;
                }
                mX -= 1;
                mY += 1;
            }
            //player one ball collision (sides indicated by white circles)
            if (dist(pX2, pY2, bX - 5, bY) < 25 + (bD / 2) / 3) {
                if (mX < 0) {
                    mX = 0;
                }
                mX += 1;
            }
            if (dist(pX2, pY2, bX + 5, bY) < 25 + (bD / 2) / 3) {
                if (mX > 0) {
                    mX = 0;
                }
                mX -= 1;
            }
            if (dist(pX2, pY2, bX, bY - 5) < 25 + (bD / 2) / 3) {
                if (mY < 0) {
                    mY = 0;
                }
                mY += 1;
            }
            if (dist(pX2, pY2, bX, bY + 5) < 25 + (bD / 2) / 3) {
                if (mY > 0) {
                    mY = 0;
                }
                mY -= 1;
            }

            if (mX > 0) {
                mX -= 0.1;
            }
            if (mX < 0) {
                mX += 0.1;
            }
            if (mY > 0) {
                mY -= 0.1;
            }
            if (mY < 0) {
                mY += 0.1;
            }

            if (dist(bX, bY, nX1, nY + h / 2) < bD / 2 + (w) / 2) {
                for (int i = 0; i < brackets.size(); i++) {
                    if (brackets.get(i).isSecondPlayer) {
                        brackets.get(i).points += 1;
                        brackets.get(i).isSecondPlayer = false;
                    }
                }
                seq = 2;
                //do win code
            }
            if (dist(bX, bY, nX2 + w / 2, nY + h / 2) < bD / 2 + (w) / 2) {
                for (int i = 0; i < brackets.size(); i++) {
                    if (brackets.get(i).isFirstPlayer) {
                        brackets.get(i).points += 1;
                        brackets.get(i).isFirstPlayer = false;
                    }
                }
                seq = 2;
                //do win code
            }
        }
    }

    public class bracket {

        float x, y, w, h;
        boolean isPressed, isPlaying, isFirstPlayer, isSecondPlayer;
        int position = 1;
        String t = "player";
        int points = 0;
        int round = 1;

        bracket(float X, float Y, float W, float H, int P, String T) {
            x = X;
            y = Y;
            w = W;
            h = H;
            isPressed = false;
            t = T;
            position = P;
            isPlaying = false;
            isFirstPlayer = false;
            isSecondPlayer = false;
            if (players == 2) {
                boolean isFirstSet;
            } else if (players == 4) {
                boolean isFirstSet, isSecondSet;
            } else if (players == 8) {
                boolean isFirstSet, isSecondSet, isThirdSet, isFourthSet;
            }
        }

        public void update() {
            fill(100);
            if (mouseX > x && mouseX < x + w) {
                if (mouseY > y && mouseY < y + h) {
                    fill(175);
                    if (mousePressed) {
                        isPressed = true;
                    }
                }
            }
            rect(x, y, w, h);
            fill(255);
            textAlign(CENTER, CENTER);
            text(t+" : "+points, x + w / 2, y + h / 2);
        }
    }

    public class button {

        float x, y, w, h;
        boolean isPressed;
        String t = "button";

        button(float X, float Y, float W, float H, String T) {
            x = X;
            y = Y;
            w = W;
            h = H;
            isPressed = false;
            t = T;
        }

        public void update() {
            fill(100);
            if (mouseX > x && mouseX < x + w) {
                if (mouseY > y && mouseY < y + h) {
                    fill(175);
                    if (mousePressed) {
                        isPressed = true;
                    }
                }
            }
            rect(x, y, w, h);
            fill(255);
            textAlign(CENTER, CENTER);
            text(t, x + w / 2, y + h / 2);
        }
    }

}
