package reviewssitefullstack;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class ReviewControllerTest {

	@InjectMocks
	private ReviewController underTest;

	@Mock
	private ReviewRepository reviewRepo;

	@Mock
	private Review review1;

	@Mock
	private Review review;

	@Mock
	private Review anotherReview;

	@Mock
	private CategoryRepository categoryRepo;

	@Mock
	private Category category1;

	@Mock
	private Category category;

	@Mock
	private Category anotherCategory;

	@Mock
	private TagRepository tagRepo;

	@Mock
	private Tag tag1;

	@Mock
	private Tag tag;

	@Mock
	private Tag anotherTag;

	@Mock
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {
		long review1Id = 1;
		when(reviewRepo.findById(review1Id)).thenReturn(Optional.of(review1));

		underTest.findOneReview(review1Id, model);
		verify(model).addAttribute("reviewsModel", review1);
	}

	@Test
	public void shouldAddSingleCategoryToModel() throws CategoryNotFoundException {
		long categoryId = 1;
		when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category1));

		underTest.findOneCategory(categoryId, model);
		verify(model).addAttribute("categoriesModel", category1);
	}

	@Test
	public void shouldAddSingleTagToModel() throws TagNotFoundException {
		long tagId = 1;
		when(tagRepo.findById(tagId)).thenReturn(Optional.of(tag1));

		underTest.findOneTag(tagId, model);
		verify(model).addAttribute("tagsModel", tag1);
	}

	@Test
	public void shouldAddAllReviewsToModel() {
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);

		underTest.findAllReviews(model);
		verify(model).addAttribute("reviewsModel", allReviews);
	}

	@Test
	public void shouldAddAllCategoriesToModel() {
		Collection<Category> allCategories = Arrays.asList(category, anotherCategory);
		when(categoryRepo.findAll()).thenReturn(allCategories);

		underTest.findAllCategories(model);
		verify(model).addAttribute("categoriesModel", allCategories);
	}

	@Test
	public void shouldAddAllTagsToModel() {
		Collection<Tag> allTags = Arrays.asList(tag, anotherTag);
		when(tagRepo.findAll()).thenReturn(allTags);

		underTest.findAllTags(model);
		verify(model).addAttribute("tagsModel", allTags);
	}
}
