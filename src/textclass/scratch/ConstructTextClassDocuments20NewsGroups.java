package textclass.scratch;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ark.data.annotation.Language;
import ark.model.annotator.nlp.NLPAnnotator;
import ark.model.annotator.nlp.NLPAnnotatorStanford;
import ark.util.FileUtil;
import ark.util.MathUtil;

import textclass.data.annotation.TextClassDocument;
import textclass.data.annotation.TextClassDocumentSet;

public class ConstructTextClassDocuments20NewsGroups {
	public static void main(String[] args) {
		String inputPath = args[0];
		String outputPath = args[1];
		int seed = Integer.parseInt(args[2]);
		String splitPath = (args.length > 3) ? args[3] : null;
		
		Random random = new Random(seed);
		
		NLPAnnotator annotator = new NLPAnnotatorStanford();
		annotator.disableConstituencyParses();
		annotator.disableDependencyParses();
		annotator.disablePoSTags();
		
		List<TextClassDocument> documents = new ArrayList<TextClassDocument>();
		File inputDirectory = new File(inputPath);
		File[] categoryDirectories = inputDirectory.listFiles();
		for (File categoryDirectory : categoryDirectories) {
			if (!categoryDirectory.isDirectory())
				continue;
			String category = categoryDirectory.getName();
			File[] documentTextFiles = categoryDirectory.listFiles();
			for (File documentTextFile : documentTextFiles) {				
				String documentName = documentTextFile.getName();
				String documentText = readFileText(documentTextFile);
				Map<String, String[]> documentMetaData = new HashMap<String, String[]>();
				documentMetaData.put("category", new String[]{ category });
				TextClassDocument document = new TextClassDocument(documentName, documentText, documentMetaData, Language.English, annotator);
				documents.add(document);
			}
		}
		
		if (splitPath != null)
			documents = MathUtil.randomPermutation(random, documents);
		
		TextClassDocumentSet documentSet = new TextClassDocumentSet();
		TextClassDocumentSet splitSet = new TextClassDocumentSet();
		int documentSetMaxIndex = (splitPath == null) ? documents.size() : (int)Math.floor(0.9*documents.size());
		
		for (int i = 0; i < documentSetMaxIndex; i++)
			documentSet.addDocument(documents.get(i));
		for (int i = documentSetMaxIndex; i < documents.size(); i++)
			splitSet.addDocument(documents.get(i));
		
		documentSet.saveToJSONDirectory(outputPath);
		if (splitPath != null)
			splitSet.saveToJSONDirectory(splitPath);
	}
	
	private static String readFileText(File file) {
		BufferedReader documentTextReader = FileUtil.getFileReader(file.getAbsolutePath());
		StringBuilder documentText = new StringBuilder();
		String line = null;
		try {
			while ((line = documentTextReader.readLine()) != null) {
				documentText.append(line).append("\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return documentText.toString();
	}
}
