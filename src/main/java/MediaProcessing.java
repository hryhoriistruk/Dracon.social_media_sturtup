import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class MediaProcessing {
    private static final Logger LOGGER = Logger.getLogger(MediaProcessing.class.getName());

    public enum MediaType {
        IMAGE,
        VIDEO,
        AUDIO
    }

    public static void processMedia(File mediaFile, MediaType type) {
        switch (type) {
            case IMAGE -> processImage(mediaFile);
            case VIDEO -> processVideo(mediaFile);
            case AUDIO -> processAudio(mediaFile);
            default -> LOGGER.warning("Unsupported media type");
        }
    }

    private static void processImage(File imageFile) {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            if (image == null) {
                throw new IOException("Failed to read image file");
            }

            int width = image.getWidth();
            int height = image.getHeight();
            LOGGER.info("Image dimensions: " + width + "x" + height);

            BufferedImage resizedImage = resizeImage(image, 0.5);

            File resizedImageFile = new File("resized_" + imageFile.getName());
            String format = getFileExtension(imageFile);
            if (!ImageIO.write(resizedImage, format, resizedImageFile)) {
                throw new IOException("Failed to write resized image");
            }

            LOGGER.info("Resized image saved as: " + resizedImageFile.getAbsolutePath());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error processing image", e);
        }
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, double scale) {
        int newWidth = (int) (originalImage.getWidth() * scale);
        int newHeight = (int) (originalImage.getHeight() * scale);
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        resizedImage.createGraphics().drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        return resizedImage;
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // Empty extension
        }
        return name.substring(lastIndexOf + 1);
    }

    private static void processVideo(File videoFile) {
        // TODO: implement video processing operations (e.g. transcoding, trimming, watermarking)
        LOGGER.info("Video processing not implemented yet for: " + videoFile.getName());
    }

    private static void processAudio(File audioFile) {
        // TODO: implement audio processing operations (e.g. transcoding, trimming, normalization)
        LOGGER.info("Audio processing not implemented yet for: " + audioFile.getName());
    }

    public static void main(String[] args) {
        processMedia(new File("image.jpg"), MediaType.IMAGE);
        processMedia(new File("video.mp4"), MediaType.VIDEO);
        processMedia(new File("audio.mp3"), MediaType.AUDIO);
    }
}