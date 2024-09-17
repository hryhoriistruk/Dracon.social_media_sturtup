
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/likePost")
public class LikePostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int postId = Integer.parseInt(request.getParameter("postId"));
        // Update the like count in the database
        Post post = PostDAO.getPostById(postId);
        if (post != null) {
            post.setLikes(post.getLikes() + 1);
            PostDAO.updatePost(post);
            response.getWriter().write("true");
        } else {
            response.getWriter().write("false");
        }
    }
}