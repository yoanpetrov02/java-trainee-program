import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sirma.javacourse.exceptions.elementlist.AddToFullListException;
import com.sirma.javacourse.exceptions.elementlist.ElementList;
import com.sirma.javacourse.exceptions.elementlist.RemoveFromEmptyListException;

public class ElementListTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ElementListTest.class);

	@Test
	public void testAddException() {
		ElementList testObject = new ElementList(0);

		Assert.assertThrows(AddToFullListException.class,
				() -> testObject.add("This will throw an exception"));
	}

	@Test
	public void testRemoveException() {
		ElementList testObject = new ElementList(0);

		Assert.assertThrows(RemoveFromEmptyListException.class,
				testObject::remove);
	}
}
