/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eatingZoneTest;

import com.sergiosierra.ants.control.Controller;
import com.sergiosierra.ants.helpers.Listener;
import com.sergiosierra.ants.helpers.Logger;
import com.sergiosierra.ants.models.Colony;
import com.sergiosierra.ants.models.WorkerAnt;

/**
 *
 * @author ssierra
 */
public class EatingZoneTest {
    
    public static void main(String[] args) throws InterruptedException {
        Colony colony = new Colony();
        Controller controller = new Controller(colony);
        Listener listener = new Listener(colony);

        WorkerAnt ant1;
        WorkerAnt ant2;
        WorkerAnt ant3;
        WorkerAnt ant4;
        WorkerAnt ant5;

        ant1 = controller.spawnWorkerAnt();
        ant2 = controller.spawnWorkerAnt();
        ant3 = controller.spawnWorkerAnt();
        ant4 = controller.spawnWorkerAnt();
        ant5 = controller.spawnWorkerAnt();
        
        controller.colony().getColony().getOutside().add(ant1);
        controller.colony().getColony().getOutside().add(ant2);
        controller.colony().getColony().getOutside().add(ant3);
        controller.colony().getColony().getOutside().add(ant4);
        controller.colony().getColony().getOutside().add(ant5);
        
        Thread.sleep(1000);
        Logger.println("Program starts!", true);
        // This Logger.println function delays the actual timestamp.
        // however, after adding these two lines above, this test
        // Can be considered as passed.
        
        
        //listener.start();
        
        ant1.start();
        ant2.start();
        ant3.start();
        ant4.start();
        ant5.start();
        
        
    }
    
    /*
        OVERRIDEN RUN METHOD FOR WorkerAnt USED FOR THIS TEST GOES HERE:
        
            @Override
            public void run() {
    
        
                controller.colony().enterColony();
                this.holdItems(1);
                //Logger.syncPrintln("I'm in! - " + antId, true);

                if (antId % 2 == 0) {

                    controller.colony().enterEatingZone();
                    try {
                        controller.colony().eat(1);
                        sleep(2000 + (int) (2000*Math.random()));
                    } catch (Exception e) {
                    }
                    controller.colony().exitEatingZone();
                    controller.colony().exitColony();

                } else {

                    controller.colony().enterEatingZone(1);
                    this.dropItems(1);
                    try {
                        sleep(2000 + (int) (2000*Math.random()));
                    } catch (Exception e) {
                    }
                    controller.colony().exitEatingZone();
                    controller.colony().exitColony();
                    //Logger.syncPrintln("I'm out! - " + antId, true);

                }
    
            }
    
        EATING ZONE TESTER METHODS:
    
            public void enterEatingZone() {

                if(colony.getInside().contains((Ant) Thread.currentThread())) {

                    colony.getEatingZone().add((Ant) Thread.currentThread());

                }

            }

            public void exitEatingZone() {

                if(colony.getEatingZone().contains((Ant) Thread.currentThread())) {

                    colony.getEatingZone().remove((Ant) Thread.currentThread());

                }

            }

            public synchronized void eat(int amount) throws InterruptedException {

                // Check whether there is enough food available at the
                // eating zone.
                while(colony.getEatingZoneFoodCount() < amount) {

                    Logger.println("Not enough food!!" + Thread.currentThread().toString(), Boolean.TRUE);
                    wait();
                    Logger.println("Woke up!!" + Thread.currentThread().toString(), Boolean.TRUE);

                }

                System.out.println(Thread.currentThread().toString() + " Miñamm!");
                colony.setEatingZoneFoodCount(
                    colony.getEatingZoneFoodCount() - amount
                );

            }

            public synchronized void enterEatingZone(int amount) {

                enterEatingZone();
                // Add amount of food to the eating zone.
                colony.setEatingZoneFoodCount(
                    colony.getEatingZoneFoodCount() + amount
                );
                System.out.println(Thread.currentThread().toString() + " - Food count: " + colony.getEatingZoneFoodCount());
                System.out.println("Notificasao!");
                notifyAll();

            }

            */
    
    
}
