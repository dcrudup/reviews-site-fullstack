package reviewssitefullstack;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
	private CategoryRepository categoryRepo;
	
	@Mock
	private Review review1;
	
	@Mock
	private Category category1;
	
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
		
		underTest.find1Review(review1Id, model);
		verify(model).addAttribute("reviewsModel", review1);
	}
	

}
