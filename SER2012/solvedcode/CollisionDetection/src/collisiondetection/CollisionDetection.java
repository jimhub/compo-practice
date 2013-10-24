/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package collisiondetection;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class CollisionDetection {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("../../judge/collisiondetection.judge"));//new Scanner(System.in); 
        PrintStream ps = System.out; //new PrintStream( new FileOutputStream( "../../solution/candystore.out" ) ); 

        double t1a;

        double simStart = 0, simEnd = 0, simStep = 0.0001;

        while ((t1a = sc.nextDouble()) > -0.001) {
            
            Car carA = new Car(t1a, sc.nextDouble(), sc.nextDouble(), sc.nextDouble(),
                        sc.nextDouble(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble());
            Car carB = new Car(sc.nextDouble(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble(),
                        sc.nextDouble(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble());
            

            simStart = Math.max(carA.t, carB.t);
            simEnd = simStart + 30.0;
            
            boolean hasCollision = false;
            
            //System.out.println("Start: "+simStart+" End: "+simEnd+" "+simStep);
            
            // Run both cars up to the same starting point
            carA.stepTo(simStart, simStep);
            carB.stepTo(simStart, simStep);
            
            for (double s = simStart; s < simEnd; s += simStep) {
                carA.step(simStep);
                carB.step(simStep);
                
                double d = carA.dist(carB);
                //System.out.println("d: "+d);
                
                if(d < 18) {
                    hasCollision = true;
                    break;
                }
            }
            
            System.out.println((hasCollision ? "1" : "0"));
            
       }

    }
    
    static class Car {
        
        public double px, py, vx, vy, ax, ay, t;
        
        public double a, v, dir;
        
        public Car( double t1, double x1, double y1, double v1,
                    double t2, double x2, double y2, double v2) {
            
            px = x2;
            py = y2;
            
            t = t2;
            
            a = (v2 - v1) / (t2 - t1);
            
            dir = Math.atan2(y2-y1, x2-x1);
            
            vx = Math.cos(dir)*v2;
            vy = Math.sin(dir)*v2;
            
            v = mag(vx, vy);
            
            ax = Math.cos(dir)*a;
            ay = Math.sin(dir)*a;
        }
        
        public void step(double ts) {
            
            if(v > 0) {
                vx += ax*ts;
                vy += ay*ts;
                v = mag(vx, vy);
                
                if(v > 0) {
                    px += vx*ts;
                    py += vy*ts;
                }
            }
            
        }
        
        public void stepTo(double toTime, double ts) {
            for(double ct = t; ct < toTime; ct += ts) {
                step(ts);
            }
        }
        
        public double mag(double x, double y) {
            return Math.sqrt((x*x)+(y*y));
        }
        
        public double dist(Car c) {
            return Math.sqrt((px - c.px) * (px - c.px) + (py - c.py) * (py - c.py));
        }
    }

}
