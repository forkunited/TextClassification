package textclass.scratch;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import textclass.data.TextClassDataTools;
import textclass.data.annotation.TextClassDocument;
import textclass.data.annotation.TextClassDocumentDatum;
import textclass.data.annotation.TextClassDocumentSet;
import textclass.data.annotation.TextClassDocumentTopic;
import textclass.util.TextClassProperties;

import ark.data.annotation.DataSet;
import ark.data.annotation.Datum.Tools;
import ark.experiment.ExperimentGST;
import ark.util.OutputWriter;

public class ExperimentGSTTextClassDocumentTopic {
	private static int textClassDocumentId;
	
	public static void main(String[] args) {
		String documentSetName = args[1];
		String validTopicsId = args[3];
		String experimentName = documentSetName + "/GSTTextClassDocumentTopic_" + validTopicsId + "/" + args[0];
		boolean useTestData = Boolean.valueOf(args[2]);

		TextClassProperties properties = new TextClassProperties();
		String experimentInputPath = new File(properties.getExperimentInputDirPath(), experimentName + ".experiment").getAbsolutePath();
		String experimentOutputPath = new File(properties.getExperimentOutputDirPath(), experimentName).getAbsolutePath(); 
		Set<String> validTopics = getValidTopics(validTopicsId);
		
		OutputWriter output = new OutputWriter(
				new File(experimentOutputPath + ".debug.out"),
				new File(experimentOutputPath + ".results.out"),
				new File(experimentOutputPath + ".data.out"),
				new File(experimentOutputPath + ".model.out")
			);
		
		TextClassDataTools dataTools = new TextClassDataTools(output, properties);
		dataTools.addToParameterEnvironment("DOCUMENT_SET", documentSetName);
		
		Tools<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic> datumTools = TextClassDocumentDatum.getTopicTools(dataTools);
		
		String documentSetPath = (new File(properties.getTextDocumentDataDirPath(), documentSetName)).getAbsolutePath();
		DataSet<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic> trainData = loadDataFromDirectory(documentSetPath + "/train", datumTools, validTopics);
		DataSet<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic> devData = loadDataFromDirectory(documentSetPath + "/dev", datumTools, validTopics);
		DataSet<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic> testData = null;
		if (useTestData)
			testData = loadDataFromDirectory(documentSetPath + "/test", datumTools, validTopics);
		
		ExperimentGST<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic> experiment = 
				new ExperimentGST<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic>(experimentName, experimentInputPath, trainData, devData, testData);
	
		if (!experiment.run())
			output.debugWriteln("Error: Experiment run failed.");
	}
	
	private static DataSet<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic> loadDataFromDirectory(String path, Tools<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic> datumTools, Set<String> validTopics) {
		TextClassDocumentSet documentSet = TextClassDocumentSet.loadFromJSONDirectory(path);
		DataSet<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic> data = new DataSet<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic>(datumTools, null);
		
		List<TextClassDocument> documents = documentSet.getDocuments();
		for (TextClassDocument document : documents) {
			String[] topics = document.getMetaData("TOPICS");
			if (topics.length != 1)
				continue;
			if (!validTopics.contains(topics[0]))
				continue;
			TextClassDocumentDatum<TextClassDocumentTopic> datum = new TextClassDocumentDatum<TextClassDocumentTopic>(textClassDocumentId, document, datumTools.labelFromString(topics[0]));
			data.add(datum);				
			textClassDocumentId++;
		}
		
		return data;
	}
	
	private static Set<String> getValidTopics(String validTopicsId) {
		Set<String> validTopics = new HashSet<String>();
		
		if (validTopicsId.equals("R52")) {
			validTopics.add("acq");
			validTopics.add("alum");
			validTopics.add("bop");
			validTopics.add("carcass");
			validTopics.add("cocoa");
			validTopics.add("coffee");
			validTopics.add("copper");
			validTopics.add("cotton");
			validTopics.add("cpi");
			validTopics.add("cpu");
			validTopics.add("crude");
			validTopics.add("dlr");
			validTopics.add("earn");
			validTopics.add("fuel");
			validTopics.add("gas");
			validTopics.add("gnp");
			validTopics.add("gold");
			validTopics.add("grain");
			validTopics.add("heat");
			validTopics.add("housing");
			validTopics.add("income");
			validTopics.add("instal-debt");
			validTopics.add("interest");
			validTopics.add("ipi");
			validTopics.add("iron-steel");
			validTopics.add("jet");
			validTopics.add("jobs");
			validTopics.add("lead");
			validTopics.add("lei");
			validTopics.add("livestock");
			validTopics.add("lumber");
			validTopics.add("meal-feed");
			validTopics.add("money-fx");
			validTopics.add("money-supply");
			validTopics.add("nat-gas");
			validTopics.add("nickel");
			validTopics.add("orange");
			validTopics.add("pet-chem");
			validTopics.add("platinum");
			validTopics.add("potato");
			validTopics.add("reserves");
			validTopics.add("retail");
			validTopics.add("rubber");
			validTopics.add("ship");
			validTopics.add("strategic-metal");
			validTopics.add("sugar");
			validTopics.add("tea");
			validTopics.add("tin");
			validTopics.add("trade");
			validTopics.add("veg-oil");
			validTopics.add("wpi");
			validTopics.add("zinc");
		} else {
			validTopics.add("acq");
			validTopics.add("crude");
			validTopics.add("earn");
			validTopics.add("grain");
			validTopics.add("interest");
			validTopics.add("money-fx");
			validTopics.add("ship");
			validTopics.add("trade");
		}
		
		return validTopics;
	}
}
