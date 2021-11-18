package com.vslab.webshop;
import java.io.*;

public class DockerLogger {
    private FileWriter file_writer;
    private BufferedWriter bufferedWriter;
    private FileOutputStream fileOutputStream;

    public DockerLogger(String classname){
        try {
           File file = new File("./logs/" + classname + ".txt");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            this.file_writer = new FileWriter(file.getAbsoluteFile(), true);
            this.bufferedWriter = new BufferedWriter(this.file_writer);
            this.write("New Logging Session:");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(String text) {
        try {
            this.bufferedWriter.write(text);
            this.bufferedWriter.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void close() {
        try {
            this.bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
