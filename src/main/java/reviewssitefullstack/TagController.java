package reviewssitefullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TagController {

	@Resource
	TagRepository tagRepo;

	@Resource
	ReviewRepository reviewRepo;

	// Can add tag with HTML Forms
	@RequestMapping("/add-tag")
	public String addTag(@RequestParam(value = "reviewId") Long reviewId, String tagName) {
		Tag newTag = tagRepo.findByDescription(tagName);
		if (newTag == null) {
			newTag = new Tag(tagName);
			tagRepo.save(newTag);
		}

		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();
		Tag existing = tagRepo.findByDescription(tagName);

		if (!review.getTags().contains(existing)) {
			review.addTag(newTag);
			reviewRepo.save(review);
		}

		return "redirect:/review?id=" + reviewId;
	}

	// Can Remove Tag with HTML Forms
	@RequestMapping("/remove-tag-button")
	public String removeTagButton(@RequestParam Long tagId, @RequestParam Long reviewId) {
		Optional<Tag> tagToRemoveResult = tagRepo.findById(tagId);
		Tag tagToRemove = tagToRemoveResult.get();

		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();

		review.removeTag(tagToRemove);
		reviewRepo.save(review);

		return "redirect:/review?id=" + reviewId;
	}

	// Showing Tags with Java and Thymeleaf
	@RequestMapping("/all-tags-ajax")
	public String showAllTags(Model model) {
		model.addAttribute("tagsModel", tagRepo.findAll());
		return "tagsAjax";
	}

	// Use Ajax to Add Tags to the Database
	@RequestMapping(path = "/tags/{tagName}", method = RequestMethod.POST)
	public String AddTag(@PathVariable String tagName, Model model) {
		Tag tagToAdd = tagRepo.findByDescription(tagName);
		if (tagToAdd == null) {
			tagToAdd = new Tag(tagName);
			tagRepo.save(tagToAdd);
		}
		model.addAttribute("tagsModel", tagRepo.findAll());
		return "partials/tags-list-added";
	}

	// Use Ajax to Remove from Database
	@RequestMapping(path = "/tags/remove/{tagName}", method = RequestMethod.POST)
	public String RemoveTag(@PathVariable String tagName, Model model) {
		Tag tagToDelete = tagRepo.findByDescription(tagName);
		if (tagRepo.findByDescription(tagName) != null) {
			for (Review review : tagToDelete.getReviews()) {
				review.removeTag(tagToDelete);
				reviewRepo.save(review);
			}
		}
		tagRepo.delete(tagToDelete);
		model.addAttribute("tagsModel", tagRepo.findAll());
		return "partials/tags-list-removed";
	}
}
