package assign2.ngram;
import java.util.ArrayList;
import java.util.List;
/**  
 * @version 1.2  
 * @author QianFu  
 * @created 2014.5.23  
 */  
public class NGramNode implements NGramContainer{
	 
	private String[] words;
	private String[] predictions;
	private Double[] probabilities;
	
	//private Boolean[] HAVERESULT;
	
	private String context;
	public static final String DecFormat = "#.######";// Format for output of
														// probabilities
	
	/**
	 * Constructor Method
	 * 
	 * @param words
	 * @param predictions
	 * @param probabilities
	 * @throws NGramException
	 */
	public NGramNode(String[] words, String[] predictions,
			Double[] probabilities) throws NGramException {
		// TODO Auto-generated constructor stub
		/*
		 * words is null or empty or contains at least one empty or null string
		 * predictions is null or empty or contains at least one empty or null
		 * string probabilities is null or contains at least one entry which is
		 * null , zero, negative or greater than 1.0 or the predictions.length
		 * is different from probabilities.length
		 */
		if ((words == null)||((words!=null)&&(words.length == 0))) {
			throw new NGramException("Not aviliable word!");
		}
		if (predictions == null) {
			throw new NGramException("");
		}
		if ((probabilities == null)) {
			throw new NGramException("");
		}
		if (predictions.length != probabilities.length) {
			// predictions.length is different from
			// probabilities.length
			throw new NGramException("");
		}
		if (words.length >= 0) {
			for (String s : words) {
				if ((s == null) || (s.isEmpty())) {
					// contains at least one entry
					// which is null , zero,
					// negative or greater than 1.0
					throw new NGramException("");
				}

			}
		}
		if (predictions.length >= 0) {
			for (String s : predictions) {
				if ((s == null) || (s == "")) {
					// contains at least one entry
					// which is null , zero,
					// negative or greater than 1.0
					throw new NGramException("");
				}

			}
		}
		if (probabilities.length >= 0) {
			for (Double i : probabilities) {
				if ((i == null) || (i > 1) || (i <= 0)) {
					// contains at least one entry
					// which is null , zero, negative 
					// or greater than 1.0
					throw new NGramException("");
				}

			}
		}
		this.words = words;
		this.predictions = predictions;
		this.probabilities = probabilities;

	}

	/**
	 * Constructor Method
	 * 
	 * @param context
	 * @param predictions
	 * @param probabilities
	 * @throws NGramException
	 */
	//@SuppressWarnings("unused")
	public NGramNode(String context, String[] predictions,
			Double[] probabilities) throws NGramException {

		if ((context == null)||(context.isEmpty() == true)) {
			throw new NGramException("Not aviliable word!");
		}
		if (predictions == null) {
			throw new NGramException("");//
		}
		if ((probabilities == null)) {
			throw new NGramException("");//
		}
		if (predictions.length != probabilities.length) {
			// predictions.length is different from
			// probabilities.length
			throw new NGramException("");
		}
		if (predictions.length >= 0) {
			for (String s : predictions) {
				if ((s == null) || (s == "")) {
					// contains at least one entry
					// which is null , zero,
					// negative or greater than 1.0
					throw new NGramException("");
				}

			}
		}
		if (probabilities.length >= 0) {
			for (Double i : probabilities) {
				if ((i == null) || (i > 1) || (i <= 0)) {
					// contains at least one entry
					// which is null , zero,
					// negative or greater than 1.0
					throw new NGramException("");
				}

			}
		}
		this.context = context;// string containing the context phrase
		this.predictions = predictions;// array of next words in the phrase as
										// predicted by the model
		this.probabilities = probabilities;// corresponding probabilities of
											// context>prediction w.r.t. model
	}

	/**
	 * 
	 * Simple getter method for the context string
	 * 
	 * @return String containing context phrase for predictions
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramContainer#getContext()
	 */
	@Override
	public String getContext() {

		int index = 0;//record the index of words
		if ((words!=null)&&(words.length > 0)) {
			
			//for copy the words to context
			for (String s : words) {
				if(index == 0){
					//inser
					this.context = s + " ";
				}
				else if((index == words.length-1)&&(index!=0)){
					//
					this.context += s;
				}
				else if((index != words.length-1)&&(index!=0)){
					//
					this.context += s + " ";
				}
				index ++;
			}
		}
	
			return this.context;
	}

	/**
	 * 
	 * Simple setter method for the context string
	 * 
	 * @param context
	 *            - single String containing context phrase for predictions
	 * @throws NGramException
	 *             if <code>context</code> is null or empty
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramContainer#setContext(java.lang.String)
	 */
	@Override
	public void setContext(String context) throws NGramException {

		if ((context == null) || (context.isEmpty())) {
			throw new NGramException("ERROR:The context is null or empty!");
		}
		this.context = context;
	}

	/**
	 * 
	 * Simple setter method for the context string from multiple words
	 * 
	 * @param words
	 *            - array of words in order that make up the context
	 * @throws NGramException
	 *             if <code>words</code> is null or empty or contains at least
	 *             one empty or null string
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramContainer#setContext(java.lang.String[])
	 */
	@Override
	public void setContext(String[] words) throws NGramException {
		// TODO Auto-generated method stub
		if ((words == null)||((words!=null)&&(words.length == 0))) {
			throw new NGramException("ERROR: words is null or empty!");
		}
		if (words.length > 0) {
			for (String s : words) {
				if ((s == null) || (s.isEmpty())) {
					throw new NGramException(
							"ERROR: words contain at least one empty or null string");
				}
				this.context = this.context + " " + s;
			}
		}
	}

	/**
	 * 
	 * Simple getter method for the prediction strings
	 * 
	 * @return array of alternative next words in the phrase as predicted by the
	 *         model
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramContainer#getPredictions()
	 */
	@Override
	public String[] getPredictions() {
		// TODO Auto-generated method stub

		return predictions;
	}// end getPredictions

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
	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramContainer#setPredictions(java.lang.String[])
	 */
	@Override
	public void setPredictions(String[] predictions) throws NGramException {
		// TODO Auto-generated method stub
		//int count = 0;// count the index of string[] predictions.
		if ((predictions == null)||((predictions!=null)&&(predictions.length==0))) {
			throw new NGramException("ERROR:");
		}
		if (predictions.length > 0) {
			for (String s : predictions) {
				if ((s == null)||(s.isEmpty())) {
					throw new NGramException("ERROR:predictions contain"
							+ "at least one empty or null string");
				}
			}
		}
		// Init count to the last index value of
		// predictions string[]
		//count = predictions.length;
		//for (String s : predictions) {
		//	this.predictions[count] = s;// add the new predictions words
		//	count++;
		//}
		this.predictions = predictions;
	}

	/**
	 * 
	 * Simple getter method for the probabilities
	 * 
	 * @return array of probabilities of context>prediction w.r.t. model
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramContainer#getProbabilities()
	 */
	@Override
	public Double[] getProbabilities() {
		// TODO Auto-generated method stub
		return this.probabilities;
	}

	/**
	 * 
	 * Simple setter method for the probabilities
	 * 
	 * @param probabilities
	 *            - array of probabilities of context>prediction w.r.t. model
	 * @throws NGramException
	 *             if <code>probabilities</code> null or contains at least one
	 *             entry which is null , zero, negative or greater than 1.0
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramContainer#setProbabilities(java.lang.Double[])
	 */
	@Override
	public void setProbabilities(Double[] probabilities) throws NGramException {

		int count = 0;// count the index of string[] probabilities
		if (probabilities == null) {
			throw new NGramException("ERROR: probabilities null or contains!");
		}
		for (Double i : probabilities) {
			if ((i==null)||(i > 1) || (i <= 0)) {
				throw new NGramException(
						"ERROR: probabilities contain at least one entry "
								+ "which is null , zero, negative or greater than 1.0");
			}
		}
		// init count to the last index value of
		// predictions string[]
		/*
		count = probabilities.length;
		for (Double d : probabilities) {
			this.probabilities[count] = d;// add the new probabilities number.
			count++;
		}*/
		this.probabilities = probabilities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = "NGram Results for Query: ";
		int count = 0;// This is for count the index of probability
		str += this.getContext();
		str += "\n";

		for (String p : this.predictions) {

			str += this.getContext() + " | " + p + " : "
					+ this.probabilities[count] + "\n";
			count++;
		}
		return str;
	}
}
