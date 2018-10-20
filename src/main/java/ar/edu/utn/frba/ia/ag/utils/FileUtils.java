package ar.edu.utn.frba.ia.ag.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class FileUtils {
    private static String nombreArchivo = null;

    public static void inicializarCorrida() {
        nombreArchivo = "salida-" + System.currentTimeMillis() + ".txt";
        String texto = "--> Inicio corrida del horario: " + new Date() + "\n";
        try {
            Files.write(Paths.get(nombreArchivo), texto.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void agregarTexto(String texto) {
        try {
            Files.write(Paths.get(nombreArchivo), (texto + "\n").getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
