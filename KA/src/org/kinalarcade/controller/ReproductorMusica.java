package org.kinalarcade.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class ReproductorMusica {

    private MediaPlayer mediaPlayer;

    public void reproducirMusica(String URL_MUSICA) {
        try {
            URL recurso = getClass().getResource("/org/kinalarcade/resource/" + URL_MUSICA);

            if (recurso != null) {
                detenerMusica();
                Media musica = new Media(recurso.toExternalForm());
                mediaPlayer = new MediaPlayer(musica);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.play();
            } else {
                System.out.println("No se encontró el archivo de música.");
            }

        } catch (Exception e) {
            System.out.println("Error al reproducir música: " + e.getMessage());
        }
    }

    public void detenerMusica() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            mediaPlayer = null;
        }
    }
    
    public void musicaRepetir (String URL_MUSICA, int cantidad) {
        try {
            URL recurso = getClass().getResource("/org/kinalarcade/resource/" + URL_MUSICA);

            if (recurso != null) {
                detenerMusica();
                Media musica = new Media(recurso.toExternalForm());
                mediaPlayer = new MediaPlayer(musica);
                mediaPlayer.setCycleCount(cantidad);
                mediaPlayer.play();
            } else {
                System.out.println("No se encontró el archivo de música.");
            }

        } catch (Exception e) {
            System.out.println("Error al reproducir música: " + e.getMessage());
        }
    }
}
