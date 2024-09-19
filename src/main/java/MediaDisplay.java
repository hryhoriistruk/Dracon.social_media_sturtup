import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class MediaDisplay {
    private static final Logger LOGGER = Logger.getLogger(MediaDisplay.class.getName());

    public enum MediaType {
        IMAGE,
        VIDEO,
        AUDIO
    }

    public static void displayMedia(File mediaFile, MediaType type) {
        switch (type) {
            case IMAGE -> displayImage(mediaFile);
            case VIDEO -> displayVideo(mediaFile);
            case AUDIO -> displayAudio(mediaFile);
            default -> LOGGER.warning("Unsupported media type");
        }
    }

    private static void displayImage(File imageFile) {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            if (image == null) {
                throw new IOException("Failed to read image file");
            }

            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Image Display: " + imageFile.getName());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                ImageIcon icon = new ImageIcon(image);
                JLabel label = new JLabel(icon);
                JScrollPane scrollPane = new JScrollPane(label);

                frame.add(scrollPane);
                frame.pack();
                frame.setSize(Math.min(800, image.getWidth()),
                        Math.min(600, image.getHeight()));
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });

            LOGGER.info("Displaying image: " + imageFile.getName());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error displaying image", e);
        }
    }

    private static void displayVideo(File videoFile) {
        // TODO: implement video display using a library such as VLCJ or JavaFX
        LOGGER.info("Video display not implemented yet for: " + videoFile.getName());
    }

    private static void displayAudio(File audioFile) {
        // TODO: implement audio display using a library such as JMusic or JavaFX
        LOGGER.info("Audio display not implemented yet for: " + audioFile.getName());
    }

    public static void main(String[] args) {
        displayMedia(new File("image.jpg"), MediaType.IMAGE);
        displayMedia(new File("video.mp4"), MediaType.VIDEO);
        displayMedia(new File("audio.mp3"), MediaType.AUDIO);
    }
}