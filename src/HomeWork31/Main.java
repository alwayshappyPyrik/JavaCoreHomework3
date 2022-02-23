package HomeWork31;

import java.io.*;

public class Main {

    static StringBuilder sbForDirectoriesAndFiles = new StringBuilder();
    static String separator = System.lineSeparator();

    public static void main(String[] args) {
        try {
            createSomeDirectories();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public static void createSomeDirectories() throws IOException {
        File src = new File("C:\\Games\\src");
        File res = new File("C:\\Games\\res");
        File savegames = new File("C:\\Games\\savegames");
        File temp = new File("C:\\Games\\temp");
        File srcMain = new File("C:\\Games\\src\\main");
        File srcTest = new File("C:\\Games\\src\\test");
        File resDrawables  = new File("C:\\Games\\res\\drawables");
        File resVectors  = new File("C:\\Games\\res\\vectors");
        File resIcons  = new File("C:\\Games\\res\\icons");
        if (src.mkdir()) {
            sbForDirectoriesAndFiles.append("Каталог " + src.getName() + " был создан");
        } if (res.mkdir()) {
            sbForDirectoriesAndFiles.append(separator).append("Каталог " + res.getName() + " был создан");
        } if (savegames.mkdir()) {
            sbForDirectoriesAndFiles.append(separator).append("Каталог " + savegames.getName() + " был создан");
        } if (temp.mkdir()) {
            sbForDirectoriesAndFiles.append(separator).append("Каталог " + temp.getName() + " был создан");
        } if (srcMain.mkdir()) {
            sbForDirectoriesAndFiles.append(separator).append("Каталог " + srcMain.getName() + " был создан");
        } if (srcTest.mkdir()) {
            sbForDirectoriesAndFiles.append(separator).append("Каталог " + srcTest.getName() + " был создан");
        } if (resDrawables.mkdir()) {
            sbForDirectoriesAndFiles.append(separator).append("Каталог " + resDrawables.getName() + " был создан");
        } if (resVectors.mkdir()) {
            sbForDirectoriesAndFiles.append(separator).append("Каталог " + resVectors.getName() + " был создан");
        } if (resIcons.mkdir()) {
            sbForDirectoriesAndFiles.append(separator).append("Каталог " + resIcons.getName() + " был создан");
        }
        createSomeFiles();
        String result = sbForDirectoriesAndFiles.toString();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Games\\temp\\text.txt", false))) {
            bw.write(result);
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public static void createSomeFiles() {
        File main = new File("C:\\Games\\src\\main\\Main.java");
        File utils = new File("C:\\Games\\src\\main\\Utils.java");
        File temp = new File("C:\\Games\\temp\\text.txt");
        try {
            if (main.createNewFile()) {
                sbForDirectoriesAndFiles.append(separator).append("Файл " + main.getName() + " был создан");
            }
            if (utils.createNewFile()) {
                sbForDirectoriesAndFiles.append(separator).append("Файл " + utils.getName() + " был создан");
            }
            if (temp.createNewFile()) {
                sbForDirectoriesAndFiles.append(separator).append("Файл " + temp.getName() + " был создан");
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
}
