package assign2.ngram;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.text.DecimalFormat;

import org.junit.Test;

/**
 * @version 1.0
 * @author ChuanLi n8818452
 * @created 2014.5.18.
 */
public class NGramNodeTest {
	/*-------------------------start test construct----------------------------------------------------*/
	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String[], java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when words is null or empty then throw
	 * NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor1_Exception_Words_NullOrEmpty_Test()
			throws NGramException {

		String[] words = null;
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String[], java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when words contain at least have null string
	 * then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor1_Exception_WordsEntry_Null_Test()
			throws NGramException {

		String[] words = { "to", null, "be" };
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String[], java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when words contain at least have one empty
	 * string then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor1_Exception_WordsEntry_Empty_Test()
			throws NGramException {

		String[] words = { "to", "", "be" };
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String[], java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when predictions is null or empty then throw
	 * NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor1_Exception_Predictions_nullOrEmpty_Test()
			throws NGramException {

		String[] words = { "to", "be", "or" };
		String[] predictions = null;
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String[], java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when predictions contains at least one empty or
	 * null string then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor1_Exception_PredictionsEntry_Null_Test()
			throws NGramException {

		String[] words = { "to", "be", "or" };
		String[] predictions = { "to", "be", null };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String[], java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when predictions contains at least one empty or
	 * null string then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor1_Exception_PredictionsEntry_Empty_Test()
			throws NGramException {

		String[] words = { "to", "be", "or" };
		String[] predictions = { "to", "", "be" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String[], java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when probabilities is null then throw
	 * NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor1_Exception_Probabilities_NullOrEmpty_Test()
			throws NGramException {

		String[] words = { "to", "", "" };
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = null;
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String[], java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when probabilities contains at least one entry
	 * which is null then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor1_Exception_ProbabilitiesEntry_NullOrEmpty_Test()
			throws NGramException {

		String[] words = { "to", "be", "or" };
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { null, 0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String[], java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when probabilities contains at least one entry
	 * which is zero then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor1_Exception_ProbabilitiesEntry_Zero_Test()
			throws NGramException {

		String[] words = { "to", "", "" };
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.11, 0.00, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String[], java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when probabilities contains at least one entry
	 * which is zero then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor1_Exception_ProbabilitiesEntry_Negative_Test()
			throws NGramException {

		String[] words = { "to", "", "" };
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, -0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String[], java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when probabilities contains at least one entry
	 * which greater than 1.0 then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor1_Exception_ProbabilitiesEntry_Greater1_Test()
			throws NGramException {

		String[] words = { "to", "", "" };
		String[] predictions = { "to", "be", "or", "not" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String[], java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when predictions.length is different from
	 * probabilities.length then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor1_Exception_PredLength_Differ_ProbLength_Test()
			throws NGramException {

		String[] words = { "to", "be", "or" };
		String[] predictions = { "to", "be", "or", "not" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String, java.lang.String,java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when context is null then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor2_Exception_Context_Null_Test()
			throws NGramException {

		String context = null;
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String, java.lang.String,java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when context is empty then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor2_Exception_Context_Empty_Test()
			throws NGramException {

		String context = "";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String, java.lang.String,java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when predictions is null or empty then throw
	 * NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor2_Exception_Predictions_NullOrEmpty_Test()
			throws NGramException {

		String context = "to";
		String[] predictions = null;
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String, java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when predictions contains at least one null
	 * string then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor2_Exception_PredictionsEntry_Null_Test()
			throws NGramException {

		String context = "to";
		String[] predictions = { "to", "be", null };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String, java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when predictions contains at least one empty
	 * string then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor2_Exception_PredictionsEntry_Empty_Test()
			throws NGramException {

		String context = "to";
		String[] predictions = { "", "be", "not" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String[], java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when probabilities is null then throw
	 * NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor2_Exception_Probabilities_NullOrEmpty_Test()
			throws NGramException {

		String context = "to";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = null;
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String[], java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when probabilities contains at least one entry
	 * which is null then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor2_Exception_ProbabilitiesEntry_nullOrEmpty_Test()
			throws NGramException {

		String context = "to";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { null, 0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String[], java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when probabilities contains at least one entry
	 * which is zero then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor2_Exception_ProbabilitiesEntry_Zero_Test()
			throws NGramException {

		String context = "to";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.11, 0.00, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String, java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when probabilities contains at least one entry
	 * which is zero then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor2_Exception_ProbabilitiesEntry_Negative_Test()
			throws NGramException {

		String context = "to";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, -0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String, java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when probabilities contains at least one entry
	 * which greater than 1.0 then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor2_Exception_ProbabilitiesEntry_Greater1_Test()
			throws NGramException {

		String context = "to";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 1.6, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#NGramNode(java.lang.String, java.lang.String[],java.lang.Double[])}
	 * . <br/>
	 * <br/>
	 * Test NGramNode construct, when predictions.length is different from
	 * probabilities.length then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Constructor2_Exception_PredLength_Differ_ProbLength_Test()
			throws NGramException {

		String context = "to";
		String[] predictions = { "to", "be", "or", "not" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		@SuppressWarnings("unused")
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
	}

	/*--------------------------End of test construct----------------------------------------------------*/

	/*------------------------- test getContext()----------------------------------------------------*/
	/**
	 * Test method : {@link assign2.ngram.NGramNode#getContext()}. <br/>
	 * <br/>
	 * Test getContext() method get context by using constructor 1(words)
	 * 
	 * @throws NGramException
	 */
	@Test
	public void Test_words_getContext() throws NGramException {
		String[] words = { "to", "or", "not" };
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")
		// String context = "to or not";
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		System.out.println("******************" + ngram.getContext());
		assertEquals("to or not", ngram.getContext());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#getContext()}. <br/>
	 * <br/>
	 * Test getContext() method get context by using constructor 2(context)
	 * 
	 * @throws NGramException
	 */
	@Test
	public void Test_context_getContext() throws NGramException {
		String[] words = { "to", "or", "not" };
		String context = "to or not";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")
		// String context = "to or not";
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		// System.out.println("12234*************"+ngram.getContext());
		assertEquals("to or not", ngram.getContext());
	}

	/*-------------------------end of test getContext()----------------------------------------------*/

	/*------------------------- test setContext()----------------------------------------------------*/
	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#setContext(java.lang.String)}. <br/>
	 * <br/>
	 * Test setContext() by using constructor 1(words), Simple setter method for
	 * the context string if context is null then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_words_setContextString_Exception_contextIsNull()
			throws NGramException {
		String[] words = { "to", "be", "or" };
		String context = null;
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")

		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		ngram.setContext(context);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#setContext(java.lang.String)}. <br/>
	 * <br/>
	 * Test setContext() by using constructor 2(context), Simple setter method
	 * for the context string if context is null then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_context_setContextString_Exception_contextIsNull()
			throws NGramException {
		String[] words = { "to", "be", "or" };
		String context = null;
		String contextSet = "12345";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")

		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		ngram.setContext(contextSet);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#setContext(java.lang.String)}. <br/>
	 * <br/>
	 * Test setContext() by using constructor 1(words), Simple setter method for
	 * the context string if context is empty then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_words_setContextString_Exception_contextIsEmpty()
			throws NGramException {
		String[] words = { "to", "be", "or" };
		String context = "";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")

		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		ngram.setContext(context);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#setContext(java.lang.String)}. <br/>
	 * <br/>
	 * Test setContext() by using constructor 2(context), Simple setter method
	 * for the context string if context is empty then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_context_setContextString_Exception_contextIsEmpty()
			throws NGramException {
		String[] words = { "to", "be", "or" };
		String context = "12345";
		String contextSet = "";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")

		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		ngram.setContext(contextSet);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#setContext(java.lang.String)}. <br/>
	 * <br/>
	 * Test setContext() method by using constructor 1(words) Simple setter
	 * method for the context string
	 * 
	 * @throws NGramException
	 */
	@Test
	public void Test_words_setContextString() throws NGramException {
		String[] words = { "to", "be", "or" };
		String context = "to be or";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")

		NGramNode ngram = new NGramNode(words, predictions, probabilities);

		ngram.setContext(context);
		System.out.println("1234235********************************"
				+ ngram.getContext());
		assertEquals(context, ngram.getContext());
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#setContext(java.lang.String)}. <br/>
	 * <br/>
	 * Test setContext() method by using constructor 2(context) Simple setter
	 * method for the context string
	 * 
	 * @throws NGramException
	 */
	@Test
	public void Test_context_setContextString() throws NGramException {
		String[] words = { "to", "be", "or" };
		String context = "to be or";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")

		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		ngram.setContext(context);
		assertEquals(context, ngram.getContext());
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#setContext(java.lang.String[])}. <br/>
	 * <br/>
	 * Test setContext() by using constructor 1(words), Simple setter method for
	 * the context string from multiple words if words is null then throw
	 * NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_words_setContextArraryString_Exception_wordsIsNull_Test()
			throws NGramException {
		String[] words = null;
		// String context = null;
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")

		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		ngram.setContext(words);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#setContext(java.lang.String[])}. <br/>
	 * <br/>
	 * Test setContext() by using constructor 2(context), Simple setter method
	 * for the context string from multiple words if words is null then throw
	 * NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_context_setContextArraryString_Exception_wordsIsNull_Test()
			throws NGramException {
		String[] words = null;
		String context = "4578679 uyu";
		String[] wordsSet = null;
		String contextSet = "4578679 uyu";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")

		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		ngram.setContext(wordsSet);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#setContext(java.lang.String[])}. <br/>
	 * <br/>
	 * Test setContext() by using constructor 1(words), Simple setter method for
	 * the context string from multiple words if words is empty then throw
	 * NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_words_setContextArrayString_Exception_wordsIsEmpty_Test()
			throws NGramException {
		String[] words = {};
		// String context = "";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")

		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		ngram.setContext(words);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#setContext(java.lang.String[])}. <br/>
	 * <br/>
	 * Test setContext() by using constructor 2(context), Simple setter method
	 * for the context string from multiple words if words is empty then throw
	 * NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_context_setContextArrayString_Exception_wordsIsEmpty_Test()
			throws NGramException {
		// String[] words = {};
		String context = "4558 yo tou";
		String[] wordsSet = {};
		// String contextSet = "";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")

		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		System.out.println("**********************" + wordsSet);

		ngram.setContext(wordsSet);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#setContext(java.lang.String[])}. <br/>
	 * <br/>
	 * Test setContext() by using constructor 1(words), Simple setter method for
	 * the context string from multiple words if words contain at least one null
	 * entry then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_words_setContexArraryString_Exception_wordsEntryIsNull_Test()
			throws NGramException {
		String[] words = { "to", "be", null };
		String context = null;
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")

		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		ngram.setContext(words);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#setContext(java.lang.String[])}. <br/>
	 * <br/>
	 * Test setContext() by using constructor 2(context), Simple setter method
	 * for the context string from multiple words if words contain at least one
	 * null entry then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_context_setContexArraryString_Exception_wordsEntryIsNull_Test()
			throws NGramException {
		String[] words = { "to", "be", null };
		String context = "123435b tuy";
		String[] wordsSet = { "to", "be", null };
		String contextSet = null;
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")

		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		ngram.setContext(wordsSet);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#setContext(java.lang.String[])}. <br/>
	 * <br/>
	 * Test setContext() by using constructor 1(words), Simple setter method for
	 * the context string from multiple words if words contain at least one null
	 * entry then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_words_setContextArrayString_Exception_wordsEntryIsEmpty_Test()
			throws NGramException {
		String[] words = { "to", "", "or" };
		String context = "";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")

		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		ngram.setContext(words);
	}

	/**
	 * Test method :
	 * {@link assign2.ngram.NGramNode#setContext(java.lang.String[])}. <br/>
	 * <br/>
	 * Test setContext() by using constructor 1(context), Simple setter method
	 * for the context string from multiple words if words contain at least one
	 * null entry then throw NGramException
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_context_setContextArrayString_Exception_wordsEntryIsEmpty_Test()
			throws NGramException {
		String[] words = { "to", "", "or" };
		String context = "789ffhj huii";

		String[] wordsSet = { "to", "", "or" };
		String contextSet = "";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")

		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		ngram.setContext(wordsSet);
	}

	/*-------------------------end of test setContext()----------------------------------------------------*/

	/*------------------------- test getPredictions()----------------------------------------------------*/

	/**
	 * 
	 * Simple getter method for the prediction strings
	 * 
	 * @return array of alternative next words in the phrase as predicted by the
	 *         model
	 */

	/**
	 * Test method : {@link assign2.ngram.NGramNode#java [] getPredictions()}. <br/>
	 * <br/>
	 * Test getPredictions() method by using constructor 1(words) Simple getter
	 * method for the prediction strings
	 * 
	 * @throws NGramException
	 */
	@Test
	public void Test_words_getPredictions() throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		// String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		assertArrayEquals(predictions, ngram.getPredictions());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#java [] getPredictions()}. <br/>
	 * <br/>
	 * Test getPredictions() method by using constructor 2(context) Simple
	 * getter method for the prediction strings
	 * 
	 * @throws NGramException
	 */
	@Test
	public void Test_context_getPredictions() throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		// String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		assertArrayEquals(predictions, ngram.getPredictions());
	}

	/*------------------------- End of test getPredictions()----------------------------------------------------*/

	/*------------------------- test setPredictions()----------------------------------------------------*/

	/**
	 * 
	 * Simple setter method for the predictions string array
	 * 
	 * @param predictions
	 *            - next word in the phrase as predicted by the model
	 * @throws NGramException
	 *             if <code>predictions</code> is null or empty or contains at
	 *             least one empty or null string
	 */
	// public void setPredictions(String[] predictions) throws NGramException;

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setPredictions(java.lang.String[] predictions) }. <br/>
	 * <br/>
	 * Test setPredictions() method by using constructor 1(words), Simple setter
	 * method for the predictions string array,
	 * 
	 * 
	 * 
	 * @throws NGramException
	 */
	@Test
	public void Test_words_setPredictions() throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		String[] predictionsSet = { "not", "to", "be" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		ngram.setPredictions(predictionsSet);
		assertArrayEquals(predictionsSet, ngram.getPredictions());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setPredictions(java.lang.String[] predictions) }. <br/>
	 * <br/>
	 * Test setPredictions() method by using constructor 2(context), Simple
	 * setter method for the predictions string array, if predictions is null or
	 * empty or contains at least one empty or null string then throw
	 * NGramException
	 * 
	 * 
	 * @throws NGramException
	 */
	@Test
	public void Test_context_setPredictions() throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		String[] predictionsSet = { "not", "to", "be" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		ngram.setPredictions(predictionsSet);
		assertArrayEquals(predictionsSet, ngram.getPredictions());
	}

	// public void
	// Test_context_setContextArrayString_Exception_wordsEntryIsEmpty_Test()
	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setPredictions(java.lang.String[] predictions) }. <br/>
	 * <br/>
	 * Test setPredictions() method by using constructor1 (words), Simple setter
	 * method for the predictions string array, if predictions is null then
	 * throw NGramException
	 * 
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_word_setPredictions_Exception_predictionIsNull()
			throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		String[] predictionsSet = null;
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		ngram.setPredictions(predictionsSet);
		// assertArrayEquals(predictionsSet, ngram.getPredictions());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setPredictions(java.lang.String[] predictions) }. <br/>
	 * <br/>
	 * Test setPredictions() method by using constructor2 (context), Simple
	 * setter method for the predictions string array, if predictions is null
	 * then throw NGramException
	 * 
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_context_setPredictions_Exception_predictionIsNull()
			throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		String[] predictionsSet = null;
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		ngram.setPredictions(predictionsSet);
		// assertArrayEquals(predictionsSet, ngram.getPredictions());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setPredictions(java.lang.String[] predictions) }. <br/>
	 * <br/>
	 * Test setPredictions() method by using constructor1 (words), Simple setter
	 * method for the predictions string array, if predictions is empty then
	 * throw NGramException
	 * 
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_word_setPredictions_Exception_predictionIsEmpty()
			throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		String[] predictionsSet = {};
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		ngram.setPredictions(predictionsSet);
		// assertArrayEquals(predictionsSet, ngram.getPredictions());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setPredictions(java.lang.String[] predictions) }. <br/>
	 * <br/>
	 * Test setPredictions() method by using constructor2 (context), Simple
	 * setter method for the predictions string array, if predictions is empty
	 * then throw NGramException
	 * 
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_context_setPredictions_Exception_predictionIsEmpty()
			throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		String[] predictionsSet = {};
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		ngram.setPredictions(predictionsSet);
		// assertArrayEquals(predictionsSet, ngram.getPredictions());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setPredictions(java.lang.String[] predictions) }. <br/>
	 * <br/>
	 * Test setPredictions() method by using constructor1 (words), Simple setter
	 * method for the predictions string array, if predictions contains at least
	 * one null string then throw NGramException
	 * 
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_word_setPredictions_Exception_predictionEntryIsNull()
			throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		String[] predictionsSet = { "uiob", null, "678" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		ngram.setPredictions(predictionsSet);
		// assertArrayEquals(predictionsSet, ngram.getPredictions());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setPredictions(java.lang.String[] predictions) }. <br/>
	 * <br/>
	 * Test setPredictions() method by using constructor2 (context), Simple
	 * setter method for the predictions string array, if predictions contains
	 * at least one null string then throw NGramException
	 * 
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_context_setPredictions_Exception_predictionEntryIsNull()
			throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		String[] predictionsSet = { "uiob", null, "678" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		ngram.setPredictions(predictionsSet);
		// assertArrayEquals(predictionsSet, ngram.getPredictions());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setPredictions(java.lang.String[] predictions) }. <br/>
	 * <br/>
	 * Test setPredictions() method by using constructor1 (words), Simple setter
	 * method for the predictions string array, if predictions contains at least
	 * one empty string then throw NGramException
	 * 
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_word_setPredictions_Exception_predictionEntryIsEmpty()
			throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		String[] predictionsSet = { "uiob", "", "678" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		ngram.setPredictions(predictionsSet);
		// assertArrayEquals(predictionsSet, ngram.getPredictions());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setPredictions(java.lang.String[] predictions) }. <br/>
	 * <br/>
	 * Test setPredictions() method by using constructor2 (context), Simple
	 * setter method for the predictions string array, if predictions contains
	 * at least one empty string then throw NGramException
	 * 
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_context_setPredictions_Exception_predictionEntryIsEmpty()
			throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		String[] predictionsSet = { "uiob", "", "678" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		ngram.setPredictions(predictionsSet);
		// assertArrayEquals(predictionsSet, ngram.getPredictions());
	}

	/*-------------------------End of test setPredictions()----------------------------------------------------*/

	/*-------------------------test getProbabilities()----------------------------------------------------*/
	/**
	 * 
	 * Simple getter method for the probabilities
	 * 
	 * @return array of probabilities of context>prediction w.r.t. model
	 */

	/**
	 * Test method : {@link assign2.ngram.NGramNode#java [] getProbabilities()}. <br/>
	 * <br/>
	 * Test getProbabilities() method by using constructor1 (words), Simple
	 * getter method for the probabilities
	 * 
	 * @throws NGramException
	 */
	@Test
	public void Test_word_getProbabilities() throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		Double[] probabilitiesSet = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		// ngram.getProbabilities();
		// ngram.setPredictions(predictionsSet);
		assertArrayEquals(probabilities, ngram.getProbabilities());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#java [] getProbabilities()}. <br/>
	 * <br/>
	 * Test getProbabilities() method by using constructor2 (context), Simple
	 * getter method for the probabilities
	 * 
	 * @throws NGramException
	 */
	@Test
	public void Test_context_getProbabilities() throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		Double[] probabilitiesSet = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		// ngram.getProbabilities();
		// ngram.setPredictions(predictionsSet);
		assertArrayEquals(probabilities, ngram.getProbabilities());
	}

	/*-------------------------end of test getProbabilities()----------------------------------------------------*/

	/*-------------------------test setProbabilities()----------------------------------------------------*/

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setProbabilities(java.lang.Double[] probabilities) }. <br/>
	 * <br/>
	 * Test setProbabilities() method by using constructor1 (words), Simple
	 * setter method for the probabilities
	 * 
	 * @throws NGramException
	 */
	@Test
	public void Test_word_setProbabilities() throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		Double[] probabilitiesSet = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		// ngram.getProbabilities();
		// ngram.setPredictions(predictionsSet);
		ngram.setProbabilities(probabilitiesSet);
		assertArrayEquals(probabilitiesSet, ngram.getProbabilities());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setProbabilities(java.lang.Double[] probabilities) }. <br/>
	 * <br/>
	 * Test setProbabilities() method by using constructor2 (context), Simple
	 * setter method for the probabilities
	 * 
	 * @throws NGramException
	 */
	@Test
	public void Test_context_setProbabilities() throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		Double[] probabilitiesSet = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		// ngram.getProbabilities();
		// ngram.setPredictions(predictionsSet);
		ngram.setProbabilities(probabilitiesSet);

		System.out.println("fhhgjgjhkj" + probabilitiesSet + "++++++"
				+ ngram.getProbabilities());

		assertArrayEquals(probabilitiesSet, ngram.getProbabilities());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setProbabilities(java.lang.Double[] probabilities) }. <br/>
	 * <br/>
	 * Test setProbabilities() method by using constructor1 (words), Simple
	 * setter method for the probabilities, if probabilities null
	 * 
	 * @throws NGramException
	 */
	// @Test(expected = NGramException.class)
	// public void Test_word_setPredictions_Exception_predictionIsNull() throws
	// NGramException {
	@Test(expected = NGramException.class)
	public void Test_words_setProbabilities_Exception_probabilitiesIsNull()
			throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		Double[] probabilitiesSet = null;
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		// ngram.getProbabilities();
		// ngram.setPredictions(predictionsSet);
		ngram.setProbabilities(probabilitiesSet);
		// assertArrayEquals(probabilitiesSet, ngram.getProbabilities());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setProbabilities(java.lang.Double[] probabilities) }. <br/>
	 * <br/>
	 * Test setProbabilities() method by using constructor2 (context), Simple
	 * setter method for the probabilities, if probabilities null
	 * 
	 * @throws NGramException
	 */
	// @Test(expected = NGramException.class)
	// public void Test_word_setPredictions_Exception_predictionIsNull() throws
	// NGramException {
	@Test(expected = NGramException.class)
	public void Test_context_setProbabilities_Exception_probabilitiesIsNull()
			throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		Double[] probabilitiesSet = null;
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		// ngram.getProbabilities();
		// ngram.setPredictions(predictionsSet);
		ngram.setProbabilities(probabilitiesSet);
		// assertArrayEquals(probabilitiesSet, ngram.getProbabilities());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setProbabilities(java.lang.Double[] probabilities) }. <br/>
	 * <br/>
	 * Test setProbabilities() method by using constructor1 (words), Simple
	 * setter method for the probabilities, if probabilities contains at least
	 * one entry which is null
	 * 
	 * @throws NGramException
	 */
	// @Test(expected = NGramException.class)
	// public void Test_word_setPredictions_Exception_predictionIsNull() throws
	// NGramException {
	/*
	 * @Test(expected = NGramException.class) public void
	 * Test_words_setProbabilities_Exception_probabilitiesEntryIsNull() throws
	 * NGramException { String[] words = { "my", "book", "is" }; String context
	 * = "my book is"; String[] predictions = { "to", "be", "or" }; Double[]
	 * probabilities = { 0.111, 0.222, 0.333 }; Double[] probabilitiesSet = {
	 * 0.876, null, 0.76 }; NGramNode ngram = new NGramNode(context,
	 * predictions, probabilities); // ngram.getProbabilities(); //
	 * ngram.setPredictions(predictionsSet);
	 * ngram.setProbabilities(probabilitiesSet); //
	 * assertArrayEquals(probabilitiesSet, ngram.getProbabilities()); }
	 */

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setProbabilities(java.lang.Double[] probabilities) }. <br/>
	 * <br/>
	 * Test setProbabilities() method by using constructor2 (context), Simple
	 * setter method for the probabilities, if probabilities contains at least
	 * one entry which is null
	 * 
	 * @throws NGramException
	 */
	/*
	 * @Test(expected = NGramException.class) public void
	 * Test_context_setProbabilities_Exception_probabilitiesEntryIsNull() throws
	 * NGramException { String[] words = { "my", "book", "is" }; String context
	 * = "my book is"; String[] predictions = { "to", "be", "or" }; Double[]
	 * probabilities = { 0.111, 0.222, 0.333 }; Double[] probabilitiesSet = {
	 * 0.876,1.0, 0.76 }; NGramNode ngram = new NGramNode(context, predictions,
	 * probabilities); // ngram.getProbabilities(); //
	 * ngram.setPredictions(predictionsSet);
	 * ngram.setProbabilities(probabilitiesSet); //
	 * assertArrayEquals(probabilitiesSet, ngram.getProbabilities()); }
	 */

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setProbabilities(java.lang.Double[] probabilities) }. <br/>
	 * <br/>
	 * Test setProbabilities() method by using constructor1 (words), Simple
	 * setter method for the probabilities, if probabilities contains at least
	 * one entry which is zero
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_words_setProbabilities_Exception_probabilitiesEntryIsZero()
			throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		Double[] probabilitiesSet = { 0.876, 0.0, 0.76 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		// ngram.getProbabilities();
		// ngram.setPredictions(predictionsSet);
		ngram.setProbabilities(probabilitiesSet);
		// assertArrayEquals(probabilitiesSet, ngram.getProbabilities());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setProbabilities(java.lang.Double[] probabilities) }. <br/>
	 * <br/>
	 * Test setProbabilities() method by using constructor2 (context), Simple
	 * setter method for the probabilities, if probabilities contains at least
	 * one entry which is zero
	 * 
	 * @throws NGramException
	 */
	// @Test(expected = NGramException.class)
	// public void Test_word_setPredictions_Exception_predictionIsNull() throws
	// NGramException {
	@Test(expected = NGramException.class)
	public void Test_context_setProbabilities_Exception_probabilitiesEntryIsZero()
			throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		Double[] probabilitiesSet = { 0.876, 0.0, 0.76 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		// ngram.getProbabilities();
		// ngram.setPredictions(predictionsSet);
		ngram.setProbabilities(probabilitiesSet);
		// assertArrayEquals(probabilitiesSet, ngram.getProbabilities());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setProbabilities(java.lang.Double[] probabilities) }. <br/>
	 * <br/>
	 * Test setProbabilities() method by using constructor1 (words), Simple
	 * setter method for the probabilities, if probabilities contains at least
	 * one entry which is negative
	 * 
	 * @throws NGramException
	 */
	// @Test(expected = NGramException.class)
	// public void Test_word_setPredictions_Exception_predictionIsNull() throws
	// NGramException {
	@Test(expected = NGramException.class)
	public void Test_words_setProbabilities_Exception_probabilitiesEntryIsNegative()
			throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		Double[] probabilitiesSet = { 0.876, -0.89, 0.76 };
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		// ngram.getProbabilities();
		// ngram.setPredictions(predictionsSet);
		ngram.setProbabilities(probabilitiesSet);
		// assertArrayEquals(probabilitiesSet, ngram.getProbabilities());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setProbabilities(java.lang.Double[] probabilities) }. <br/>
	 * <br/>
	 * Test setProbabilities() method by using constructor2 (context), Simple
	 * setter method for the probabilities, if probabilities contains at least
	 * one entry which is negative
	 * 
	 * @throws NGramException
	 */
	// @Test(expected = NGramException.class)
	// public void Test_word_setPredictions_Exception_predictionIsNull() throws
	// NGramException {
	@Test(expected = NGramException.class)
	public void Test_context_setProbabilities_Exception_probabilitiesEntryIsNegative()
			throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		Double[] probabilitiesSet = { 0.876, -0.89, 0.76 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		// ngram.getProbabilities();
		// ngram.setPredictions(predictionsSet);
		ngram.setProbabilities(probabilitiesSet);
		// assertArrayEquals(probabilitiesSet, ngram.getProbabilities());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setProbabilities(java.lang.Double[] probabilities) }. <br/>
	 * <br/>
	 * Test setProbabilities() method by using constructor1 (words), Simple
	 * setter method for the probabilities, if probabilities contains at least
	 * one entry which is greater than 1.0
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_words_setProbabilities_Exception_probabilitiesEntryHigherThan1()
			throws NGramException {
		String[] words = { "my", "book", "is" };
		// String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		Double[] probabilitiesSet = { 2.0, 0.89, 0.76 };
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		// ngram.getProbabilities();
		// ngram.setPredictions(predictionsSet);
		ngram.setProbabilities(probabilitiesSet);
		// assertArrayEquals(probabilitiesSet, ngram.getProbabilities());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#void
	 * setProbabilities(java.lang.Double[] probabilities) }. <br/>
	 * <br/>
	 * Test setProbabilities() method by using constructor1 (words), Simple
	 * setter method for the probabilities, if probabilities contains at least
	 * one entry which is greater than 1.0
	 * 
	 * @throws NGramException
	 */
	@Test(expected = NGramException.class)
	public void Test_context_setProbabilities_Exception_probabilitiesEntryHigherThan1()
			throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		Double[] probabilitiesSet = { 2.0, 0.89, 0.76 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		// ngram.getProbabilities();
		// ngram.setPredictions(predictionsSet);
		ngram.setProbabilities(probabilitiesSet);
		// assertArrayEquals(probabilitiesSet, ngram.getProbabilities());
	}

	/*-------------------------end of test setProbabilities()----------------------------------------------------*/
	/**
	 * Test method : {@link assign2.ngram.NGramNode#java toString()}. <br/>
	 * <br/>
	 * Test ToString() method by using constructor1 (words),
	 * 
	 * @throws NGramException
	 */
	@Test
	public void Test_words_ToString() throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")
		// String context = "to or not";
		NGramNode ngram = new NGramNode(words, predictions, probabilities);
		// assertEquals("my book is", ngram);
		System.out.println(ngram.toString());
	}

	/**
	 * Test method : {@link assign2.ngram.NGramNode#java toString()}. <br/>
	 * <br/>
	 * Test ToString() method by using constructor2 (context),
	 * 
	 * @throws NGramException
	 */
	@Test
	public void Test_context_ToString() throws NGramException {
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		// @SuppressWarnings("unused")
		// String context = "to or not";
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		// assertEquals("my book is", ngram);
		String str = "my book is | to : 0.111\n" + "my book is | be : 0.222\n"
				+ "my book is | or : 0.333\n";
		System.out.println(ngram.toString());
		assertEquals(ngram.toString(), str);

	}

	@Test
	public void TOSTRING_ComplexObject() throws NGramException {
		DecimalFormat df = new DecimalFormat(NGramContainer.DecFormat);
		String test = "be or not to | be : 0.136059\n"
				+ "be or not to | mention : 0.066563\n"
				+ "be or not to | exceed : 0.032759\n"
				+ "be or not to | say : 0.028824\n"
				+ "be or not to | the : 0.024524\n";
		String context = "be or not to";
		String[] predictions = { "be", "mention", "exceed", "say", "the" };
		Double[] probabilities = { 0.13605912332, 0.066563234345,
				0.03275912314, 0.028823899932, 0.0245242343 };

		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		// ngram.setContext("be or not to");
		// ngram.setPredictions(new
		// String[]{"be","mention","exceed","say","the"});
		// ngram.setProbabilities(new
		// Double[]{0.13605912332,0.066563234345,0.03275912314,0.028823899932,0.0245242343});
		String str = ngram.toString();
		assertEquals(test, str);
	}

	// NGramNodeTests.java
	/*
	 * Confirm that the API spec has not been violated through the addition of
	 * public fields, constructors or methods that were not requested
	 */
	@Test
	public void NoExtraPublicMethods() {
		// Extends Object, implements NGramContainer
		final int toStringCount = 1;
		final int NumObjectClassMethods = Array.getLength(Object.class
				.getMethods());
		final int NumInterfaceMethods = Array.getLength(NGramContainer.class
				.getMethods());
		final int NumNGramNodeClassMethods = Array.getLength(NGramNode.class
				.getMethods());
		assertTrue(
				"obj:" + NumObjectClassMethods + ":inter:"
						+ NumInterfaceMethods + " - 1 (toString()) = class:"
						+ NumNGramNodeClassMethods,
				(NumObjectClassMethods + NumInterfaceMethods - toStringCount) == NumNGramNodeClassMethods);
	}

	@Test
	public void NoExtraPublicFields() {
		// Extends Object, implements NGramContainer
		final int NumObjectClassFields = Array.getLength(Object.class
				.getFields());
		final int NumInterfaceFields = Array.getLength(NGramContainer.class
				.getFields());
		final int NumNGramNodeClassFields = Array.getLength(NGramNode.class
				.getFields());
		assertTrue(
				"obj + interface = class",
				(NumObjectClassFields + NumInterfaceFields) == NumNGramNodeClassFields);
	}

	@Test
	public void NoExtraPublicConstructors1() {
		// Extends Object, implements NGramContainer
		final int ExtraConsCount = 1;
		final int NumObjectClassConstructors = Array.getLength(Object.class
				.getConstructors());
		final int NumInterfaceConstructors = Array
				.getLength(NGramContainer.class.getConstructors());
		final int NumNGramNodeClassConstructors = Array
				.getLength(NGramNode.class.getConstructors());
		assertTrue(
				"obj:" + NumObjectClassConstructors + ":inter:"
						+ NumInterfaceConstructors + " 1 (extra) = class:"
						+ NumNGramNodeClassConstructors,
				(NumObjectClassConstructors + NumInterfaceConstructors + ExtraConsCount) == NumNGramNodeClassConstructors);
	}

	/*
	 * @Test public void TOSTRING_ComplexObject1() throws NGramException {
	 * DecimalFormat df = new DecimalFormat(NGramContainer.DecFormat); String
	 * test = "be or not to | be : 0.136059\n" +
	 * "be or not to | mention : 0.066563\n" +
	 * "be or not to | exceed : 0.032759\n" + "be or not to | say : 0.028824\n"
	 * + "be or not to | the : 0.024524\n"; String context = "be or not to";
	 * String[] predictions = {"be","mention","exceed","say","the"}; Double[]
	 * probabilities =
	 * {0.13605912332,0.066563234345,0.03275912314,0.028823899932,0.0245242343};
	 * 
	 * NGramNode ngram = new NGramNode(context, predictions, probabilities);
	 * 
	 * toTest.setContext("be or not to"); toTest.setPredictions(new
	 * String[]{"be","mention","exceed","say","the"});
	 * toTest.setProbabilities(new
	 * Double[]{0.13605912332,0.066563234345,0.03275912314
	 * ,0.028823899932,0.0245242343}); String str = toTest.toString();
	 * assertEquals(test,str); }
	 */

}
