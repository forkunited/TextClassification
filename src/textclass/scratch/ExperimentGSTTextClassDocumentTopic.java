package textclass.scratch;

import java.io.File;
import java.util.List;

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
		String experimentName = documentSetName + "/GSTTextClassDocumentTopic/" + args[0];
		boolean useTestData = Boolean.valueOf(args[2]);

		TextClassProperties properties = new TextClassProperties();
		String experimentInputPath = new File(properties.getExperimentInputDirPath(), experimentName + ".experiment").getAbsolutePath();
		String experimentOutputPath = new File(properties.getExperimentOutputDirPath(), experimentName).getAbsolutePath(); 
		
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
		DataSet<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic> trainData = loadDataFromDirectory(documentSetPath + "/train", datumTools);
		DataSet<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic> devData = loadDataFromDirectory(documentSetPath + "/dev", datumTools);
		DataSet<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic> testData = null;
		if (useTestData)
			testData = loadDataFromDirectory(documentSetPath + "/test", datumTools);
		
		ExperimentGST<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic> experiment = 
				new ExperimentGST<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic>(experimentName, experimentInputPath, trainData, devData, testData);
	
		if (!experiment.run())
			output.debugWriteln("Error: Experiment run failed.");
	}
	
	private static DataSet<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic> loadDataFromDirectory(String path, Tools<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic> datumTools) {
		TextClassDocumentSet documentSet = TextClassDocumentSet.loadFromJSONDirectory(path);
		DataSet<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic> data = new DataSet<TextClassDocumentDatum<TextClassDocumentTopic>, TextClassDocumentTopic>(datumTools, null);
		
		List<TextClassDocument> documents = documentSet.getDocuments();
		for (TextClassDocument document : documents) {
			String[] topics = document.getMetaData("TOPICS");
			for (String topic : topics) {
				TextClassDocumentDatum<TextClassDocumentTopic> datum = new TextClassDocumentDatum<TextClassDocumentTopic>(textClassDocumentId, document, datumTools.labelFromString(topic));
				data.add(datum);				
				textClassDocumentId++;
			}
		}
		
		return data;
	}
}
