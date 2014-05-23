package assign2.ngram;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;

import net.sf.jautodoc.preferences.MainPreferencePage;

import com.microsoft.research.webngram.service.GenerationService;
import com.microsoft.research.webngram.service.NgramServiceFactory;
import com.microsoft.research.webngram.service.GenerationService.TokenSet;

/**
 * @version 1.2
 * @author QianFu(Cynthia)
 * @created 2014.5.23.
 */
public class NGramStore implements NGramMap {

	private List<NGramContainer> ngramContainersList = new ArrayList<NGramContainer>();
	private static final String Key = "068cc746-31ff-4e41-ae83-a2d3712d3e68";
    private int MAXRESULTS = 5;
	// private LinkedList<String[]> ngramList = new LinkedList<String[]>();
    private List<Boolean> HAVERESULT = new ArrayList<Boolean>();
   
	/**
	 * 
	 * (Silently) Add an ngram to the Map. If the <code>context</code> does not
	 * exist in the Map, the entry is added.<br>
	 * If the <code>context</code> exists in the Map, then the associated ngram
	 * is updated (and thus overwritten).
	 * 
	 * @param ngram
	 *            - ngram to be added
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramMap#addNGram(assign2.ngram.NGramContainer)
	 */
	@Override
	public void addNGram(NGramContainer ngram) {
		
	   // judge whether or not the context exists in
		// the Map
		for (NGramContainer currentNgram : ngramContainersList) {
			if (currentNgram == ngram) {
				ngramContainersList.remove(currentNgram);// updated
			}
		}
		ngramContainersList.add(ngram);
		//HAVERESULT.add(true);
	}

	/**
	 * 
	 * (Silently) Remove an ngram from the Map. If the <code>context</code> does
	 * not exist in the Map, the entry is not removed, but no status is
	 * returned. We guarantee that the entry no longer exists<br>
	 * If the <code>context</code> exists in the Map, then the associated ngram
	 * is removed.
	 * 
	 * @param context
	 *            - context string for ngram to be removed
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramMap#removeNGram(java.lang.String)
	 */
	@Override
	public void removeNGram(String context) {
		
		for (NGramContainer currentNgram : ngramContainersList) {
			if (currentNgram.getContext() == context) {
				ngramContainersList.remove(currentNgram);// updated
			}
		}

	}

	/**
	 * 
	 * Find the NGram associated with the context if it exists in the Map.
	 * Return null if the context is not a key in the Map.
	 * 
	 * @param context
	 * @return NGramContainer associated with the context or null
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramMap#getNGram(java.lang.String)
	 */
	@Override
	public NGramContainer getNGram(String context) {
		
		for (NGramContainer currentNgram : ngramContainersList) {
			if (currentNgram.getContext() == context) {
				return currentNgram;
			}
		}
		return null;
	}

	/**
	 * 
	 * Get the top maxResults ngrams returned from the service.
	 * 
	 * @param context
	 *            - the context for the ngram search
	 * @param maxResults
	 *            - the maximum number of
	 * @return true and store the NGram in the Map if the service returns at
	 *         least one result<br>
	 *         return false and do not store the bare context if the service
	 *         returns no predictions
	 * @throws NGramException
	 *             if the service fails to connect or if the NGramContainer
	 *             cannot be created.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramMap#getNGramsFromService(java.lang.String, int)
	 */
	@Override
	public boolean getNGramsFromService(String context, int maxResults)
			throws NGramException {

		try {
			
			NgramServiceFactory factory = NgramServiceFactory
					.newInstance(NGramStore.Key);

			if (factory == null) {
				// NGramException if the service fails to connect
				throw new NGramException(
						"ERROR: fail to connect to the service");
			}
			
			if((context == null)||(context.isEmpty())){
				throw new NGramException(
						"ERROR: context is null or empty!");
			}
			GenerationService service = factory.newGenerationService();
			
			if (service == null) {
				// NGramException if the service fails to connect
				
				throw new NGramException(
						"ERROR: fail to connect to the service");
			}
			
			TokenSet tokenSet = service.generate(NGramStore.Key,
					"bing-body/2013-12/5", context, maxResults, null);

			
			if ((tokenSet == null) || (tokenSet.getWords() == null)
					|| (tokenSet.getWords().isEmpty())) {
				HAVERESULT.add(false);
				return false;
			}
			
			// get the predictions
			List<String> predictionsList = tokenSet.getWords();
			// get the probabilities
			List<Double> probabilitiesList_pre = tokenSet.getProbabilities();

			// Calculate the real probabilities
			List<Double> probabilitiesList = new ArrayList<Double>();
			for (double p : probabilitiesList_pre) {
				// change the formation of the probabilities
				probabilitiesList.add(Math.pow(10.0, p));
			}

			String[] predictions = (String[]) predictionsList
					.toArray(new String[0]);
			Double[] probabilities = (Double[]) probabilitiesList
					.toArray(new Double[0]);
			NGramNode ngramContainer = new NGramNode(context, predictions,
					probabilities);
			this.addNGram(ngramContainer);
			return true;
		
		} catch (NGramException e) {
			throw new NGramException(
					"ERROR: the NGramContainer cannot be created");
		}
	}
	
	@Override
	public String toString() {
		String str = null;
		int LASTINDEX = 0;
		//display the last one
		if((ngramContainersList.isEmpty())||ngramContainersList == null){
			str = null;
			return str;
		}
		LASTINDEX = ngramContainersList.size()-1;
		str = ngramContainersList.get(LASTINDEX).toString() + "\n";
		return str;
	}
}
