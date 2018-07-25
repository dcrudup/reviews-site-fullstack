package reviewssitefullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

	@Resource
	private CommentRepository commentRepo;

	@Resource
	private ReviewRepository reviewRepo;

	@RequestMapping("/add-comment")
	public String addComment(String author, Long reviewId, String content) {
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();

		Comment newComment = new Comment(author, review, content);
		commentRepo.save(newComment);

		return "redirect:/review?id=" + reviewId;
	}

	// Can Remove Comment with HTML Forms
	@RequestMapping("/remove-comment-button")
	public String removeComment(Long reviewId, Long commentId) {
		commentRepo.deleteById(commentId);

		return "redirect:/review?id=" + reviewId;
	}
}
