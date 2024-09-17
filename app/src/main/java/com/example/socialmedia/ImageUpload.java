import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/uploadImage")
public class ImageUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uploadFolder = getServletContext().getInitParameter("uploadFolder");
        File folder = new File(uploadFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String fileName = request.getParameter("fileName");
        File file = new File(folder, fileName);
        // Resize the image
        BufferedImage img = ImageIO.read(file);
        BufferedImage resizedImg = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resizedImg.createGraphics();
        g2d.drawImage(img, 0, 0, 800, 800, null);
        g2d.dispose();
        ImageIO.write(resizedImg, "jpg", file);
        response.getWriter().write("Image uploaded successfully");
    }
}