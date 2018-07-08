package reviewssitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@Mock
	private Review review;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@Mock
	private Category category;
	
	@MockBean
	private TagRepository tagRepo;
	
	@Mock
	private Tag tag;
	
	@Test
	public void shouldRouteToSingleReviewView() throws Exception{
		long reviewId = 1;
		when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(view().name(is("review")));
	}
	
	@Test
	public void shouldRouteToAllReviewView() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(view().name(is("reviews")));
	}
	
	@Test
	public void shouldBeOkForAllReviews() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldBeOkForSingleReview() throws Exception {
		long reviewId = 1;
		when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToSingleCategoryView() throws Exception {
		long categoryId = 1;
		when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(view().name(is("category")));
	}

	@Test
	public void shouldRouteToAllCategoriesView() throws Exception{
		mvc.perform(get("/show-categories")).andExpect(view().name(is("categories")));
	}
	
	@Test
	public void shouldBeOkForAllCategories() throws Exception{
		mvc.perform(get("/show-categories")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToSingleTagView() throws Exception {
		long tagId = 1;
		when(tagRepo.findById(tagId)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tag?id=1")).andExpect(view().name(is("tag")));
	}
	
	@Test
	public void shouldBeOkForSingleCategory() throws Exception {
		long categoryId = 1;
		when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldBeOkForSingleTag() throws Exception {
		long tagId = 1;
		when(tagRepo.findById(tagId)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tag?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToAllTagsView() throws Exception {
		mvc.perform(get("/show-tags")).andExpect(view().name(is("tags")));
	}
	
	@Test
	public void shouldBeOkForAllTags() throws Exception {
		mvc.perform(get("/show-tags")).andExpect(status().isOk());
	}
}
