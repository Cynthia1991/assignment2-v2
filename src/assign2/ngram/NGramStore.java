package assign2.ngram;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.microsoft.research.webngram.service.GenerationService;
import com.microsoft.research.webngram.service.NgramServiceFactory;
import com.microsoft.research.webngram.service.GenerationService.TokenSet;

/**
 * @version 2.0
 * @author QianFu n9223002
 * @created 2014.5.25.
 */
public class NGramStore implements NGramMap {

	// private List<NGramContainer> ngramContainersList = new
	// ArrayList<NGramContainer>();
	// new version change the list to map, which can operate more efficiently.
	private Map<String, NGramContainer> ngramContainerList;
	private static final String Key = "068cc746-31ff-4e41-ae83-a2d3712d3e68";
	// private int MAXRESULTS = 5;
	// private LinkedList<String[]> ngramList = new LinkedList<String[]>();
	private List<Boolean> HAVERESULT = new ArrayList<Boolean>();

	public NGramStore() {
		ngramContainerList = new LinkedHashMap<String, NGramContainer>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramMap#addNGram(assign2.ngram.NGramContainer)
	 */
	@Override
	public void addNGram(NGramContainer ngram) {

		// judge whether or not the context exists in
		// the Map,if yes, update the context,if no, add the context
		ngramContainerList.put(ngram.getContext(), ngram);

		/*
		 * old version: for (NGramContainer currentNgram : ngramContainersList)
		 * { if (currentNgram == ngram) {
		 * ngramContainersList.remove(currentNgram);// updated } }
		 * ngramContainersList.add(ngram);
		 */
		// HAVERESULT.add(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramMap#removeNGram(java.lang.String)
	 */
	@Override
	public void removeNGram(String context) {

		ngramContainerList.remove(context);

		/*
		 * old version: for (NGramContainer currentNgram : ngramContainersList)
		 * { if (currentNgram.getContext() == context) {
		 * ngramContainersList.remove(currentNgram);// updated } }
		 */

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see assign2.ngram.NGramMap#getNGram(java.lang.String)
	 */
	@Override
	public NGramContainer getNGram(String context) {

		NGramContainer ngramContainer = ngramContainerList.get(context);

		/*
		 * old version: for (NGramContainer currentNgram : ngramContainersList)
		 * { if (currentNgram.getContext() == context) { return currentNgram; }
		 * }
		 */
		return ngramContainer;
	}

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

			if ((context == null) || (context.isEmpty())) {
				throw new NGramException("ERROR: context is null or empty!");
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

		} catch (Exception e) {
			throw new NGramException(
					"ERROR: the NGramContainer cannot be created");
		}
	}

	@Override
	public String toString() {

		StringBuffer ngramBuffer = new StringBuffer();
		Set<String> keySet = ngramContainerList.keySet();
		for (String s : keySet) {
			NGramNode node = (NGramNode) ngramContainerList.get(s);

			ngramBuffer.append(node.toString());
			ngramBuffer.append("\n");
		}
		return ngramBuffer.toString();
	}
}
