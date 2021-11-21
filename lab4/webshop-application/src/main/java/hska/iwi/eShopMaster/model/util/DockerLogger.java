package hska.iwi.eShopMaster.model.util;
import java.io.*;

public class DockerLogger {
    private FileWriter file_writer;
    private BufferedWriter bufferedWriter;

    public DockerLogger(String classname){
        try {
            this.file_writer = new FileWriter("./logs/" + classname + ".txt",  true);
            this.bufferedWriter = new BufferedWriter(file_writer);
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
