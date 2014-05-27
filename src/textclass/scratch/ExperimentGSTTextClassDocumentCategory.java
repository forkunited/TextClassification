package textclass.scratch;

import java.io.File;
import java.util.List;

import textclass.data.TextClassDataTools;
import textclass.data.annotation.TextClassDocument;
import textclass.data.annotation.TextClassDocumentCategory;
import textclass.data.annotation.TextClassDocumentDatum;
import textclass.data.annotation.TextClassDocumentSet;
import textclass.util.TextClassProperties;

import ark.data.annotation.DataSet;
import ark.data.annotation.Datum.Tools;
import ark.experiment.ExperimentGST;
import ark.util.OutputWriter;

public class ExperimentGSTTextClassDocumentCategory {
	private static int textClassDocumentId;
	
	public static void main(String[] args) {
		String documentSetName = args[1];
		String experimentName = documentSetName + "/GSTTextClassDocumentCategory/" + args[0];
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
		
		Tools<TextClassDocumentDatum<TextClassDocumentCategory>, TextClassDocumentCategory> datumTools = TextClassDocumentDatum.getCategoryTools(dataTools);
		
		String documentSetPath = (new File(properties.getTextDocumentDataDirPath(), documentSetName)).getAbsolutePath();
		DataSet<TextClassDocumentDatum<TextClassDocumentCategory>, TextClassDocumentCategory> trainData = loadDataFromDirectory(documentSetPath + "/train", datumTools);
		DataSet<TextClassDocumentDatum<TextClassDocumentCategory>, TextClassDocumentCategory> devData = loadDataFromDirectory(documentSetPath + "/dev", datumTools);
		DataSet<TextClassDocumentDatum<TextClassDocumentCategory>, TextClassDocumentCategory> testData = null;
		if (useTestData)
			testData = loadDataFromDirectory(documentSetPath + "/test", datumTools);
		
		ExperimentGST<TextClassDocumentDatum<TextClassDocumentCategory>, TextClassDocumentCategory> experiment = 
				new ExperimentGST<TextClassDocumentDatum<TextClassDocumentCategory>, TextClassDocumentCategory>(experimentName, experimentInputPath, trainData, devData, testData);
	
		if (!experiment.run())
			output.debugWriteln("Error: Experiment run failed.");
	}
	
	private static DataSet<TextClassDocumentDatum<TextClassDocumentCategory>, TextClassDocumentCategory> loadDataFromDirectory(String path, Tools<TextClassDocumentDatum<TextClassDocumentCategory>, TextClassDocumentCategory> datumTools) {
		TextClassDocumentSet documentSet = TextClassDocumentSet.loadFromJSONDirectory(path);
		DataSet<TextClassDocumentDatum<TextClassDocumentCategory>, TextClassDocumentCategory> data = new DataSet<TextClassDocumentDatum<TextClassDocumentCategory>, TextClassDocumentCategory>(datumTools, null);
		
		List<TextClassDocument> documents = documentSet.getDocuments();
		for (TextClassDocument document : documents) {
			TextClassDocumentDatum<TextClassDocumentCategory> datum = new TextClassDocumentDatum<TextClassDocumentCategory>(textClassDocumentId, document, datumTools.labelFromString(document.getMetaData("category")[0]));
			data.add(datum);				
			textClassDocumentId++;
		}
		
		return data;
	}
}
