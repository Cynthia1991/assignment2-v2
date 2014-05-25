/**  
 * @title NGramStoreTest.java  
 * @package assign2.ngram  
 * @author kervin  
 * @version V1.0  
 * created 2014年5月19日  
 */
package assign2.ngram;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.regex.*;

/**  
 * @version 1.0  
 * @author ChuanLi  
 * @created 2014年5月19日  
 */

public class NGramStoreTest {

	
	private NGramStore ngramsMap;
	private NGramNode ngram1;
	private NGramNode ngram2;
	private String[] words;
	private String context1 = "to be or";
	private String context2 = "my book is";
	private String[] predictions;
	private Double[] probabilities;
	private int MAXRESULTS = 5;
	
	/*void addNGram(NGramContainer ngram)

(Silently) Add an ngram to the Map. If the context does not exist in the Map, the entry is added.
 If the context exists in the Map, then the associated ngram is updated.
Parameters:ngram - - ngram to be added*/
	
	/**
	 * Test method : {@link assign2.ngram.NGramStore#void
	 * addNGram(NGramContainer ngram)} . <br/>
	 * <br/>
	 * Test (Silently) Add an ngram to the Map. If the context does not exist in
	 * the Map, the entry is added. If the context exists in the Map, then the
	 * associated ngram is updated. 
	 * @param ngram
	 * @throws NGramException
	 */

	@Before
	public void setUp() throws Exception {
		ngramsMap = new NGramStore();
		String[] words = { "my", "book", "is" };
		String context1 = "my book is";
		String context2 = "to be or";
		String[] predictions = { "not", "be", "or" };
		// String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
	    //ngram1 = new NGramNode(context, predictions, probabilities);
		//ngram2 = new NGramNode(context, predictions, probabilities);
		
		
	}

	/**  
	 * @throws java.lang.Exception   
	 * @version 1.0  
	 * @author kervin  
	 * @created 2014年5月19日   
	 */
	@After
	public void tearDown() throws Exception {
	}
	/*-------------------------start test addNGram()----------------------------------------------------*/
	/**
	 * Test method for
	 * {@link assign2.ngram.NGramStore#addNGram(assign2.ngram.NGramContainer)}. <br/>
	 * <br/>
	 * Test (Silently) Add an ngram to the Map. 
	 * If the context does not exist in
	 * the Map, the entry is added.
	 * 
	 * @param ngram
	 * @throws NGramException
	 */
	
	//@Test(expected = NGramException.class)
	//public void Test_words_setContextArrayString_Exception_wordsIsEmpty_Test()
	
	@Test
	public void Test_addNGram_NewContext() throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "be", "or" };
		// String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		ngramsMap.addNGram(ngram);
		
		assertEquals(ngram, ngramsMap.getNGram(context));
		
		
	}
	/**
	 * Test method for
	 * {@link assign2.ngram.NGramStore#addNGram(assign2.ngram.NGramContainer)}. <br/>
	 * <br/>
	 * Test (Silently) Add an ngram to the Map. 
	 * If the context exists in the Map, then the
	 * associated ngram is updated.
	 * 
	 * @param ngram
	 * @throws NGramException
	 */
	
	//@Test(expected = NGramException.class)
	//public void Test_words_setContextArrayString_Exception_wordsIsEmpty_Test()
	
	@Test
	public void Test_addNGram_ExistContext() throws NGramException {
		
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String[] predictions = { "to", "or", "not" };
		// String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		ngramsMap.addNGram(ngram);//it exist, so updated 
		
		assertEquals(ngram, ngramsMap.getNGram(context));
		
		
	}


	/*-------------------------End of test addNGram()----------------------------------------------------*/
	
	/*-------------------------start test RemoveNGram()----------------------------------------------------*/
	/**
	 * Test method for {@link assign2.ngram.NGramStore#removeNGram(java.lang.String)}.
	 * Remove an ngram from the Map. 
	 * If the context does not exist in the Map, the entry is not removed, 
	 * but no status is returned.
	 * If the context exists in the Map, then the associated ngram is removed.
	 * @throws NGramException 
	 */
	
	@Test
	public void Test_RemoveNGram_notExist() throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String contextNew = "to be or";
		String[] predictions = { "to", "or", "not" };
		// String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		ngramsMap.addNGram(ngram);//it exist, so updated 
		ngramsMap.removeNGram(contextNew);
		//assertEquals(ngram, ngramsMap.getNGram(context));
		
	}
	/**
	 * Test method for {@link assign2.ngram.NGramStore#removeNGram(java.lang.String)}.
	 * Remove an ngram from the Map. 
	 * If the context does not exist in the Map, the entry is not removed, 
	 * but no status is returned.
	 * If the context exists in the Map, then the associated ngram is removed.
	 * @throws NGramException 
	 */
	
	@Test
	public void Test_RemoveNGram_exist() throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String contextNew = "to be or";
		String[] predictions = { "to", "or", "not" };
		// String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		ngramsMap.removeNGram(context);
		//assertEquals(ngram, ngramsMap.getNGram(context));
		
	}
	
	
	/*-------------------------End of test RemoveNGram()----------------------------------------------------*/
	
	/*------------------------- test getNGram()----------------------------------------------------*/
	/**
	 * Test method for {@link assign2.ngram.NGramStore#getNGram(java.lang.String)}.
	 * Find the NGram associated with the context if it exists in the Map. 
	 * Return null if the context is not a key in the Map.
	 * @throws NGramException 
	 */
	@Test
	public void Test_GetNGram_notExist() throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String contextNew = "to be or";
		String[] predictions = { "to", "or", "not" };
		// String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		//NGramNode ngram = new NGramNode(context, predictions, probabilities);
		//ngramsMap.removeNGram(context);
		assertEquals(null, ngramsMap.getNGram(contextNew));
	}
	/**
	 * Test method for {@link assign2.ngram.NGramStore#getNGram(java.lang.String)}.
	 * Find the NGram associated with the context if it exists in the Map. 
	 * Return null if the context is not a key in the Map.
	 * @throws NGramException 
	 */
	@Test
	public void Test_GetNGram_exist() throws NGramException {
		String[] words = { "my", "book", "is" };
		String context = "my book is";
		String contextNew = "to be or";
		String[] predictions = { "to", "or", "not" };
		// String[] predictions = { "to", "be", "or" };
		Double[] probabilities = { 0.111, 0.222, 0.333 };
		NGramNode ngram = new NGramNode(context, predictions, probabilities);
		//ngramsMap.removeNGram(context);
		ngramsMap.addNGram(ngram);
		assertEquals(ngram, ngramsMap.getNGram(context));
	}
	/*-------------------------End of test getNGram()----------------------------------------------------*/
	
	/*------------------------- test getNGramsFromService()----------------------------------------------------*/
	/**
	 * Test method for {@link assign2.ngram.NGramStore#getNGramsFromService(java.lang.String, int)}.
	 * Get the top maxResults ngrams returned from the service.
	 * return true and store the NGram in the Map if the service returns at least one result
	 * 
	 * @throws NGramException 
	 */
	@Test
	public void Test_GetNGramsFromService_Normal() throws NGramException {
		String context = "to be or";
		ngramsMap.getNGramsFromService(context, MAXRESULTS);
		assertEquals(true, ngramsMap.getNGramsFromService(context, MAXRESULTS));
		
		//System.out.println(ngramsMap.toString());
	}
	/**
	 * Test method for {@link assign2.ngram.NGramStore#getNGramsFromService(java.lang.String, int)}.
	 * Get the top maxResults ngrams returned from the service.
	 * 
	 * return false and do not store the bare context if the service returns no predictions
	 * 
	 * @throws NGramException 
	 */
	@Test
	public void Test_GetNGramsFromService_NoResult() throws NGramException {
		String context = "gghjjjhk jkhakjjwlqiuu hjaklakjLJWQ";
		//ngramsMap.getNGramsFromService(context, MAXRESULTS);
		assertEquals(false, ngramsMap.getNGramsFromService(context, MAXRESULTS));
		//System.out.println(ngramsMap.toString());
	}
	/**
	 * Test method for {@link assign2.ngram.NGramStore#getNGramsFromService(java.lang.String, int)}.
	 * Get the top maxResults ngrams returned from the service.
	 * 
	 * if the service fails to connect then throws Exception.
	 * @throws NGramException 
	 */
	@Test(expected = NGramException.class)
	public void Test_GetNGramsFromService_cannotCreatNgram() throws NGramException {
		String context = "";
		ngramsMap.getNGramsFromService(context, MAXRESULTS);
		
		//System.out.println(ngramsMap.toString());
	}
	/**
	 * Test method for {@link assign2.ngram.NGramStore#getNGramsFromService(java.lang.String, int)}.
	 * Get the top maxResults ngrams returned from the service.
	 * 
	 * 
	 * if the service fails to connect then throws Exception.
	 * @throws NGramException 
	 */
	@Test(expected = NGramException.class)
	public void Test_GetNGramsFromService_cannotConnect() throws NGramException {
		String context = "";
		ngramsMap.getNGramsFromService(context, MAXRESULTS);
		
		//System.out.println(ngramsMap.toString());
	}
	/*-------------------------End of test getNGramsFromService()----------------------------------------------------*/

	/*-------------------------start test toString()----------------------------------------------------*/
	/**
	 * Test method for {@link assign2.ngram.NGramStore#toString()}.
	 * if only one ngram in the list,return the last ngram
	 * @throws NGramException 
	 */
	
	
	@Test
	public void test_ToString_one() throws NGramException {
		//String context = "to be or";
		//String[] predictions = {,,};
		//Double[] probabilities = {0.256746,0.537486,0.6523176};
		String context = "to be or";
		ngramsMap.getNGramsFromService(context, MAXRESULTS);
		
		//ngramsMap.addNGram(ngram);
		//ngramsMap.getNGramsFromService(context, MAXRESULTS);
		System.out.println(ngramsMap.toString());
		
	}
	/**
	 * Test method for {@link assign2.ngram.NGramStore#toString()}.
	 * if some ngrams in the list,return the last ngram
	 * @throws NGramException 
	 */
	
	
	@Test
	public void test_ToString_moreThanOne() throws NGramException {
		//String context = "to be or";
		//String[] predictions = {,,};
		//Double[] probabilities = {0.256746,0.537486,0.6523176};
		String context1 = "to be or";
		String context2 = "my book is";
		ngramsMap.getNGramsFromService(context1, MAXRESULTS);
		ngramsMap.getNGramsFromService(context2, MAXRESULTS);
		//ngramsMap.addNGram(ngram);
		//ngramsMap.getNGramsFromService(context, MAXRESULTS);
		System.out.println(ngramsMap.toString());
		
	}

}
