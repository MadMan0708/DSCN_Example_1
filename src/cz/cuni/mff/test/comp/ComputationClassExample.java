/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cuni.mff.test.comp;

import cz.cuni.mff.bc.api.main.ITask;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Jakub Hava
 */
public class ComputationClassExample implements ITask {

    Integer[] data;
    long timeout = 180000;

    @Override
    public void loadData(Path nameOfTheFile) {
        ArrayList<Integer> numbers = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(nameOfTheFile.toFile())))) {

            while (dis.available() != 0) {
                numbers.add(dis.readInt());
            }
            data = (Integer[]) numbers.toArray(new Integer[numbers.size()]);
        } catch (IOException e) {
        }
    }

    @Override
    public void calculate() {
        Arrays.sort(data);
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void saveData(Path nameOfTheFile) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(nameOfTheFile.toFile()))) {
            for (Integer number : data) {
                dos.writeInt(number);
            }
        } catch (IOException e) {
        }
    }
}
