package assign2.ngram;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @version 2.0
 * @author QianFu n9223002
 * @created 2014.5.23
 */
public class NGramNode implements NGramContainer {
	// Array of words in order that make up the context
	private String[] words;
	// String containing the context phrase
	private String context;
	// Array of next words in the phrase as predicted by the model
	ArrayList<String> predictionsList;
	// Corresponding probabilities of context>prediction w.r.t. model
	ArrayList<Double> probabilitiesList;

	// Format for output of probabilities
	// public static final String DecFormat = "#.######";

	/**
	 * Constructor1 Method
	 * 
	 * @param String
	 *            [] words - array of words in order that make up the context
	 * @param String
	 *            [] predictions - array of next words in the phrase as
	 *            predicted by the model
	 * @param Double
	 *            [] probabilities - corresponding probabilities of
	 *            context>prediction w.r.t. model
	 * @throws NGramException
	 *             - if words is null or empty or contains at least one empty or
	 *             null string predictions is null or empty or contains at least
	 *             one empty or null string probabilities is null or contains at
	 *             least one entry which is null , zero, negative or greater
	 *             than 1.0 or the predictions.length is different from
	 *             probabilities.length
	 */
	public NGramNode(String[] words, String[] predictions,
			Double[] probabilities) throws NGramException {
		// Use check methods to check every parameters
		if (checkArray(words) == false) {
			throw new NGramException(
					"ERROR: words is null or empty or contains at least one empty or null string");
		}
		if (checkArray(predictions) == false) {
			throw new NGramException(
					"ERROR: predictions is null or empty or contains at least one empty or null string ");
		}
		if (checkArray(probabilities) == false) {
			throw new NGramException(
					"ERROR: probabilities is null or contains at least one entry which is null , zero, negative or greater than 1.0");
		}

		if (predictions.length != probabilities.length) {
			throw new NGramException(
					"ERROR: the lengthof predictions is different from the length of probabilities");
		}
		// Initialize the words;
		// words[] = ;
		// Initialize the context;
		context = "";
		// Initialize the predictionsList
		predictionsList = new ArrayList<String>();
		// Initialize the probabilitiesList
		probabilitiesList = new ArrayList<Double>();

		// Set the three parameters
		setContext(words);// Convert the words into the context

		setPredictions(predictions);
		setProbabilities(probabilities);

	}

	/**
	 * Constructor2 Method
	 * 
	 * @param String
	 *            context - string containing the context phrase
	 * @param String
	 *            [] predictions - array of next words in the phrase as
	 *            predicted by the model
	 * @param Double
	 *            [] probabilities - corresponding probabilities of
	 *            context>prediction w.r.t. models
	 * @throws NGramExceptionNGramException
	 *             - if context is null or empty predictions is null or empty or
	 *             contains at least one empty or null string probabilities is
	 *             null or contains at least one entry which is null , zero,
	 *             negative or greater than 1.0 or the predictions.length is
	 *             different from probabilities.length
	 */
	public NGramNode(String context, String[] predictions,
			Double[] probabilities) throws NGramException {

		// Use check methods to check every parameters
		if ((context == null) || (context.isEmpty() == true)) {
			throw new NGramException("ERROR: context is null or empty!");
		}
		if (checkArray(predictions) == false) {
			throw new NGramException(
					"ERROR: predictions is null or empty or contains at least one empty or null string");//
		}
		if (checkArray(probabilities) == false) {
			throw new NGramException(
					"ERROR: probabilities is null or contains at least one entry which is null , zero, negative or greater than 1.0");//
		}
		if (predictions.length != probabilities.length) {
			throw new NGramException(
					"ERROR: predictions.length is different from probabilities.length");
		}
		// Initialize the words;
		// words[] = ;
		// Initialize the context;
		this.context = "";
		// Initialize the predictionsList;
		predictionsList = new ArrayList<String>();
		// Initialize the probabilitiesList;
		probabilitiesList = new ArrayList<Double>();

		// Set the three parameters
		setContext(context);
		setPredictions(predictions);
		setProbabilities(probabilities);
	}

	/**
	 * checkArray(String[] stringArray)
	 * 
	 * Check whether or not input string array is null or empty or contains at
	 * least one empty or null string
	 * 
	 * @param stringArray
	 * @return false -if string array is null or empty or contains at least one
	 *         empty or null string. true - if not
	 * 
	 */
	private boolean checkArray(String[] stringArray) {
		boolean isValid = true;
		if ((stringArray != null) && (stringArray.length > 0)) {
			for (String s : stringArray) {
				if ((s == null) || s.isEmpty()) {
					isValid = false;
					break;
				}
			}
		} else {
			isValid = false;
		}
		return isValid;
	}

	/**
	 * checkArray(Double[] stringArray)
	 * 
	 * Check whether or not input Double array is null or contains at least one
	 * entry which is null , zero, negative or greater than 1.0
	 * 
	 * @param stringArray
	 * @return false -if Double array is null or contains at least one entry
	 *         which is null , zero, negative or greater than 1.0. true - if not
	 * 
	 */
	private boolean checkArray(Double[] doubleArray) {
		boolean isValid = true;
		if (doubleArray != null) {
			for (Double d : doubleArray) {
				if ((d == null) || Double.compare(d, 0.0) <= 0
						|| Double.compare(d, 1.0) > 0) {
					isValid = false;
					break;
				}
			}
		} else {
			isValid = false;
		}
		return isValid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramContainer#getContext()
	 */
	@Override
	public String getContext() {

		// Do not need this parts, because it has converted the
		// words to context in constructor1
		/*
		 * int index = 0;// record the index of words if ((words != null) &&
		 * (words.length > 0)) {
		 * 
		 * // for copy the words to context for (String s : words) { if (index
		 * == 0) { // inser this.context = s + " "; } else if ((index ==
		 * words.length - 1) && (index != 0)) { // this.context += s; } else if
		 * ((index != words.length - 1) && (index != 0)) { // this.context += s
		 * + " "; } index++; } }
		 */

		return this.context;
	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramContainer#setContext(java.lang.String[])
	 */
	@Override
	public void setContext(String[] words) throws NGramException {

		if (checkArray(words) == false) {
			throw new NGramException(
					"ERROR: words is null or empty or contains at least one empty or null string!");
		}

		/*
		 * if (words.length > 0) { for (String s : words) { if ((s == null) ||
		 * (s.isEmpty())) { throw new NGramException(
		 * "ERROR: words contain at least one empty or null string"); }
		 * this.context = this.context + " " + s; } }
		 */
		// new version use the string buffer to store the context, and convert
		// words to context
		StringBuffer wordsContextBuffer = new StringBuffer();
		for (int i = 0; i < words.length; i++) {

			wordsContextBuffer.append(words[i]);

			if (i < words.length - 1) {
				wordsContextBuffer.append(" ");
			}
		}
		this.context = wordsContextBuffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramContainer#getPredictions()
	 */
	@Override
	public String[] getPredictions() {

		// Convert the list to array
		String[] predictionStrings = new String[predictionsList.size()];
		return predictionsList.toArray(predictionStrings);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramContainer#setPredictions(java.lang.String[])
	 */
	@Override
	public void setPredictions(String[] predictions) throws NGramException {

		/*
		 * if ((predictions == null) || ((predictions != null) &&
		 * (predictions.length == 0))) { throw new NGramException("ERROR:"); }
		 * if (predictions.length > 0) { for (String s : predictions) { if ((s
		 * == null) || (s.isEmpty())) { throw new
		 * NGramException("ERROR:predictions contain" +
		 * "at least one empty or null string"); } } } predictions string[]
		 * count = predictions.length; for (String s : predictions) {
		 * this.predictions[count] = s;// add the new predictions words count++;
		 * } this.predictions = predictions;
		 */
		// new version use the checkArray check prediction,and use list store
		// the prediction
		if (checkArray(predictions) == false) {
			throw new NGramException(
					"ERROR: predictions is null or empty or contains at least one empty or null string ");
		}

		predictionsList.clear();
		for (String s : predictions) {
			predictionsList.add(s);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramContainer#getProbabilities()
	 */
	@Override
	public Double[] getProbabilities() {

		// Convert the list to array
		Double[] probabilitiesDoubles = new Double[probabilitiesList.size()];
		return probabilitiesList.toArray(probabilitiesDoubles);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramContainer#setProbabilities(java.lang.Double[])
	 */
	@Override
	public void setProbabilities(Double[] probabilities) throws NGramException {

		/*
		 * int count = 0;// count the index of string[] probabilities if
		 * (probabilities == null) { throw new
		 * NGramException("ERROR: probabilities null or contains!"); } for
		 * (Double i : probabilities) { if ((i == null) || (i > 1) || (i <= 0))
		 * { throw new NGramException(
		 * "ERROR: probabilities contain at least one entry " +
		 * "which is null , zero, negative or greater than 1.0"); } }
		 * 
		 * count = probabilities.length; for (Double d : probabilities) {
		 * this.probabilities[count] = d; count++; }
		 */
		// new version use the checkArray check probabilities,and use list store
		// the probabilities
		if (checkArray(probabilities) == false) {
			throw new NGramException(
					"ERROR: probabilities is null or contains at least one entry which is null , zero, negative or greater than 1.0");
		}

		probabilitiesList.clear();
		for (double d : probabilities) {
			probabilitiesList.add(d);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// Use string buffer output
		StringBuffer ngramBuffer = new StringBuffer();
		DecimalFormat df = new DecimalFormat(DecFormat);
		for (int i = 0; i < predictionsList.size(); i++) {
			ngramBuffer.append(context);
			ngramBuffer.append(" | ");
			ngramBuffer.append(predictionsList.get(i));
			ngramBuffer.append(" : ");
			ngramBuffer.append(df.format(probabilitiesList.get(i)));
			ngramBuffer.append("\n");
		}

		return ngramBuffer.toString();

	}

}
