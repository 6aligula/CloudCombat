package dad.cloudcombat.engine;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Music {
	private MediaPlayer mediaPlayer;
	 private boolean playOnSelect;

    public Music(String musicFile) {
        try {
            Media sound = new Media(getClass().getResource(musicFile).toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        } catch (Exception e) {
            e.printStackTrace(); // Esto imprimirá la excepción completa en la consola.
            mediaPlayer = null; // Asegúrate de establecer mediaPlayer en null si la inicialización falla.

        }
        
    }

    public void play() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.play();
            } catch (Exception e) {
                e.printStackTrace(); // Imprime la excepción si la reproducción falla.
            }
        } else {
            // Manejar el caso cuando mediaPlayer es null.
            System.out.println("MediaPlayer no se ha inicializado correctamente.");
        }
    }
    

    public void stop() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            } else {
                System.out.println("MediaPlayer no se ha inicializado correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la traza de la pila de la excepción
        }
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void setVolume(double volume) {
        try {
            mediaPlayer.setVolume(volume);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public double getVolume() {
        if (mediaPlayer == null) {
            // Manejar el caso cuando mediaPlayer no se ha inicializado correctamente.
            return 0.0;
        }
        return mediaPlayer.getVolume();
    }
    

    public boolean isPlaying() {
        return mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }
    
    public void changeSong(String musicFile) {
        mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(new Media(getClass().getResource(musicFile).toString()));
        mediaPlayer.setOnEndOfMedia(() -> {
            if (playOnSelect) {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            }
        });
        if (playOnSelect) {
            mediaPlayer.play();
        }
    }

    public void setPlayOnSelect(boolean playOnSelect) {
        this.playOnSelect = playOnSelect;
    }
    
}
