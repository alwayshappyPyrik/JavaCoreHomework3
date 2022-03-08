package HomeWork32;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {

    public static void main(String[] args) {
            savaGames(gameProgress1, gameProgress2, gameProgress3);
            createZipArchive();
            deleteFile();
            openZip();
            openProgress();
    }

    private static final long serialVersionUID = 1L;
    private static final String pathForSaveFile1 = "C:" + File.separator + "Games" + File.separator + "savegames" + File.separator + "save1.txt";
    private static final String pathForSaveFile2 = "C:" + File.separator + "Games" + File.separator + "savegames" + File.separator + "save2.txt";
    private static final String pathForSaveFile3 = "C:" + File.separator + "Games" + File.separator + "savegames" + File.separator + "save3.txt";
    private static final String pathForZip = "C:" + File.separator + "Games" + File.separator + "savegames" + File.separator + "savegame.zip";
    private static final String pathForOpenZip = "C:" + File.separator + "Games" + File.separator + "savegames" + File.separator + "save1.txt";
    private static final String pathForOpenZip2 = "C:" + File.separator + "Games" + File.separator + "savegames" + File.separator + "save2.txt";
    private static final String pathForOpenZip3 = "C:" + File.separator + "Games" + File.separator + "savegames" + File.separator + "save3.txt";

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    static GameProgress gameProgress1 = new GameProgress(2,1,10,4);
    static GameProgress gameProgress2 = new GameProgress(4,2,11,5.5);
    static GameProgress gameProgress3 = new GameProgress(15,3,20,10.5);

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }


    public static void savaGames(GameProgress gameProgresses1, GameProgress gameProgresses2, GameProgress gameProgresses3) {
        try (FileOutputStream fos1 = new FileOutputStream(pathForSaveFile1); ObjectOutputStream oos = new ObjectOutputStream(fos1);
             FileOutputStream fos2 = new FileOutputStream(pathForSaveFile2); ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
             FileOutputStream fos3 = new FileOutputStream(pathForSaveFile3); ObjectOutputStream oos3 = new ObjectOutputStream(fos3)) {
            oos.writeObject(gameProgresses1);
            oos2.writeObject(gameProgresses2);
            oos3.writeObject(gameProgresses3);
        } catch (IOException ex) {
            ex.getMessage();
        }
    }


    public static void createZipArchive() {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathForZip)); FileInputStream fis = new FileInputStream(pathForSaveFile1);
             FileInputStream fis2 = new FileInputStream(pathForSaveFile2); FileInputStream fis3 = new FileInputStream(pathForSaveFile3)) {
            zout.putNextEntry(new ZipEntry("save.txt"));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.putNextEntry(new ZipEntry("save2.txt"));
            byte[] buffer2 = new byte[fis2.available()];
            fis2.read(buffer2);
            zout.write(buffer2);
            zout.putNextEntry(new ZipEntry("save3.txt"));
            byte[] buffer3 = new byte[fis3.available()];
            fis3.read(buffer3);
            zout.write(buffer3);
            zout.closeEntry();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public static void deleteFile() {
        final File pathForSaveFile1 = new File ("C:" + File.separator + "Games" + File.separator + "savegames" + File.separator + "save1.txt");
        final File pathForSaveFile2 = new File("C:" + File.separator + "Games" + File.separator + "savegames" + File.separator + "save2.txt");
        final File pathForSaveFile3 = new File ("C:" + File.separator + "Games" + File.separator + "savegames" + File.separator + "save3.txt");
        pathForSaveFile1.delete();
        System.out.println("Файл " + pathForSaveFile1.getName() + " удален");
        pathForSaveFile2.delete();
        System.out.println("Файл " + pathForSaveFile2.getName() + " удален");
        pathForSaveFile3.delete();
        System.out.println("Файл " + pathForSaveFile3.getName() + " удален");
    }

    public static void openZip() {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(pathForZip))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(pathForOpenZip);
                FileOutputStream fout2 = new FileOutputStream(pathForOpenZip2);
                FileOutputStream fout3 = new FileOutputStream(pathForOpenZip3);
                for (int c = zin.read(); c !=-1; c = zin.read()) {
                    fout.write(c);
                    fout2.write(c);
                    fout3.write(c);
                }
                fout.flush();
                fout2.flush();
                fout3.flush();
                zin.closeEntry();
                fout.close();
                fout2.close();
                fout3.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void openProgress() {
        GameProgress gameProgress1;
        try (FileInputStream fis = new FileInputStream(pathForOpenZip); ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress1 = (GameProgress) ois.readObject();
            System.out.println(gameProgress1.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
