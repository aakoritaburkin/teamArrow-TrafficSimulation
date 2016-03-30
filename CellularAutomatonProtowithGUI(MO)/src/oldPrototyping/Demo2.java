/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oldPrototyping;

import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author Alexander
 */
public class Demo2 {

    public static class RoadThread extends Thread {

        int maxV = 5;
        int roadXLen = 60;
        int roadYLen = 2;
        int road[][] = new int[roadXLen][roadYLen];

        public RoadThread() {
            setRoad();
        }

        public synchronized void run() {
            int counter = 0;
            while (counter < 100) {

//                try {
                System.out.println(counter);
                updateRoad();
                addCar();
                displayRoad();
                counter++;
//                    displayRoad();

//                    Thread.sleep(20);
//                } catch (InterruptedException ex) {
//                }
            }

        }

        public void addCar() {
            if (road[0][0] == -1) {
                road[0][0] = 0;
            }

        }

        public void displayRoad() {
//            StringBuilder labelTxt = new StringBuilder("");
            for (int y = 0; y < roadYLen; y++) {
                for (int x = 0; x < roadXLen; x++) {
//                    System.out.print(road[x][y]);
//                }
                    if (road[x][y] == -1) {
                        System.out.print("-");
                    } else {
                        System.out.print(road[x][y]);
                    }
                }
                System.out.println();
//                labelTxt.append("/n");
            }
            System.out.println(";");
        }

        public void setRoad() {
            for (int y = 0; y < roadYLen; y++) {
                for (int x = 0; x < roadXLen; x++) {
                    road[x][y] = -1;
                }
            }

        }

        public void updateRoad() {
            for (int y = 0; y < roadYLen; y++) {
                int x = 0;
                while (x < roadXLen) {
                    if (road[x][y] != -1) {// there is a car in this current cell
                        int currentX = x; // index of current car
                        int currentV = road[x][y]; // current car's velocity
                        int nextX = -1; // index of next car, -1 : no car in front
                        int nextCarD = -1; // distance to next car, -1 : no car in front
                        int dToTravel = 0; // distance the current car will travel this time step
//                        boolean endOfRoad; // is this cell the last cell?

                        if (x == roadXLen - 1) { // are we at the last cell?                           
//                            endOfRoad = true; this is the last cell
                            road[x][y] = -1;
                            break;

                        } else {
//                            endOfRoad = false; this isn't the last cell
//                            find the index of next car in front:
                            for (int i = x + 1; i < roadXLen; i++) {// find the next car in front's location
                                if (road[i][y] != -1) {
                                    nextX = i;
                                    break;
                                }
                            }

                            // step 1 + 2: Acceleration and braking
//                            has a car been found in front of the current car? 
                            if (nextX == -1) { // no, the road ahead is clear
                                currentV = acceleration(currentV);

                            } else { // yes, there's a car in front
                                nextCarD = nextX - currentX;
                                currentV = acceleration(currentV);
                                currentV = braking(currentV, nextCarD);
                            }

                            // step 3: speed randomisation (to be add later)
                            // step 4: driving
                            dToTravel = driving(currentX, currentV);
                            // will the car drive past the end of the road? 
                            if (currentX + dToTravel > roadXLen) { //yes
                                road[x][y] = -1;
                            } else { //no
                                road[x][y] = -1;
                                road[currentX + dToTravel][y] = currentV;
                            }
                            //move the counter to the cell ahead of were the car has been moved to
                            x = currentX + dToTravel + 1;
                        }
                    } else {//no car on this cell, check next cell
                        x++;
                    }
                }

            }
        }

        public int acceleration(int currentV) {
            int v = currentV + 1;
            if (v <= maxV) {
                return v;
            } else {
                return maxV;
            }
        }

        public int braking(int currentV, int nextCarD) {
            int d = nextCarD - 1;
            if (d <= currentV) {
                return d;
            } else {
                return currentV;
            }

        }

        public int driving(int currentX, int currentV) {
            return (currentX + currentV);

        }

//        public int Randomization(int currentV){
//            int v = currentV - 1;
//            
//        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame();

        RoadThread demoThread = new RoadThread();
        demoThread.run();
//        JTable table = new JTable(2, 60);
//        frame.add(table);
//        frame.pack();
//        frame.setVisible(true);

    }

    public class block {

        int x;
        int y;
        int state;

        public block(int x, int y) {
            this.x = x;
            this.y = y;
            state = 0;

        }

    }

}
