package reviewssitefullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



public class ReviewController {
	
	@Resource
	ReviewRepository reviewRepo;
	
	@RequestMapping("/review")
	public String find1Review(@RequestParam(value="id") long reviewId, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(reviewId);
		
		if (review.isPresent()) {
			model.addAttribute("reviewsModel", review.get());
			
			return "reviewTemplate";
		}
		
		throw new ReviewNotFoundException();
		
	}

}

