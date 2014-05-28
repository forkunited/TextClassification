package textclass.scratch;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import ark.data.annotation.Language;
import ark.model.annotator.nlp.NLPAnnotator;
import ark.model.annotator.nlp.NLPAnnotatorStanford;
import ark.util.FileUtil;
import ark.util.MathUtil;

import textclass.data.annotation.TextClassDocument;
import textclass.data.annotation.TextClassDocumentSet;

public class ConstructTextClassDocumentsReuters21578 {
	public static void main(String[] args) {
		String inputPath = args[0];
		String trainOutputPath = args[1];
		String devOutputPath = args[2];
		String testOutputPath = args[3];
		int seed = Integer.parseInt(args[4]);
		
		Random random = new Random(seed);
		
		NLPAnnotator annotator = new NLPAnnotatorStanford();
		annotator.disableConstituencyParses();
		annotator.disableDependencyParses();
		annotator.disablePoSTags();
		
		List<TextClassDocument> documents = new ArrayList<TextClassDocument>();
		File inputDirectory = new File(inputPath);
		File[] directoryFiles = inputDirectory.listFiles();
		for (File directoryFile : directoryFiles) {
			if (!directoryFile.getName().endsWith(".sgm"))
				continue;
			System.out.println("Loading documents from " + directoryFile.getAbsolutePath() + "...");
			documents.addAll(constructDocumentsFromFile(directoryFile, annotator));
		}
		
		System.out.println("Splitting documents to train/test...");
		
		// Load "ModApte" test/train split
		List<TextClassDocument> tempTrainDocuments = new ArrayList<TextClassDocument>();
		TextClassDocumentSet testDocumentSet = new TextClassDocumentSet();
		for (TextClassDocument document : documents) {
			String topicsAtt = document.getMetaData("ATTRIBUTE_TOPICS")[0];
			String lewisSplitAtt = document.getMetaData("ATTRIBUTE_LEWISSPLIT")[0];
			
			if (topicsAtt.equals("YES") && lewisSplitAtt.equals("TRAIN")) {
				tempTrainDocuments.add(document);
			} else if (topicsAtt.equals("YES") && lewisSplitAtt.equals("TEST")) {
				testDocumentSet.addDocument(document);
			}
		}
		
		// Split train from dev
		System.out.println("Splitting dev out of train..");
		TextClassDocumentSet trainDocumentSet = new TextClassDocumentSet();
		TextClassDocumentSet devDocumentSet = new TextClassDocumentSet();
		int trainDocumentMaxIndex = (int)Math.floor(0.9*tempTrainDocuments.size());
		tempTrainDocuments = MathUtil.randomPermutation(random, tempTrainDocuments);
		for (int i = 0; i < trainDocumentMaxIndex; i++)
			trainDocumentSet.addDocument(tempTrainDocuments.get(i));
		for (int i = trainDocumentMaxIndex; i < tempTrainDocuments.size(); i++)
			devDocumentSet.addDocument(tempTrainDocuments.get(i));
		
		System.out.println("Outputting documents..");
		trainDocumentSet.saveToJSONDirectory(trainOutputPath);
		devDocumentSet.saveToJSONDirectory(devOutputPath);
		testDocumentSet.saveToJSONDirectory(testOutputPath);
	}
	
	@SuppressWarnings("unchecked")
	private static List<TextClassDocument> constructDocumentsFromFile(File file, NLPAnnotator annotator) {
		Element fileElement = loadXMLFromFile(file);
		List<TextClassDocument> documents = new ArrayList<TextClassDocument>();
		
		List<Element> documentElements = (List<Element>)fileElement.getChildren("REUTERS");
		for (Element documentElement : documentElements) {
			Map<String, String[]> metaData = new HashMap<String, String[]>();
			String documentText = null;
			
			String topicsAttribute = documentElement.getAttributeValue("TOPICS");
			String lewisSplitAttribute = documentElement.getAttributeValue("LEWISSPLIT");
			String cgiSplitAttribute = documentElement.getAttributeValue("CGISPLIT");
			String oldIdAttribute = documentElement.getAttributeValue("OLDID");
			String newIdAttribute = documentElement.getAttributeValue("NEWID");
			String date = documentElement.getChildText("DATE");
			String unknown = documentElement.getChildText("UNKNOWN");
			
			metaData.put("ATTRIBUTE_TOPICS", new String[] { topicsAttribute });
			metaData.put("ATTRIBUTE_LEWISSPLIT", new String[] { lewisSplitAttribute });
			metaData.put("ATTRIBUTE_CGISPLIT", new String[] { cgiSplitAttribute });
			metaData.put("ATTRIBUTE_OLDID", new String[] { oldIdAttribute });
			metaData.put("ATTRIBUTE_NEWID", new String[] { newIdAttribute });
			metaData.put("DATE", new String[] { date });
			metaData.put("UNKNOWN", new String[] { unknown });
			
			Element topicsElement = documentElement.getChild("TOPICS");
			List<Element> topicElements = topicsElement.getChildren("D");
			String[] topics = new String[topicElements.size()];
			for (int i = 0; i < topics.length; i++)
				topics[i] = topicElements.get(i).getText();
			metaData.put("TOPICS", topics);
			
			Element placesElement = documentElement.getChild("PLACES");
			List<Element> placesElements = placesElement.getChildren("D");
			String[] places = new String[placesElements.size()];
			for (int i = 0; i < places.length; i++)
				places[i] = placesElements.get(i).getText();
			metaData.put("PLACES", places);
			
			Element peopleElement = documentElement.getChild("PEOPLE");
			List<Element> peopleElements = peopleElement.getChildren("D");
			String[] people = new String[peopleElements.size()];
			for (int i = 0; i < people.length; i++)
				people[i] = peopleElements.get(i).getText();
			metaData.put("PEOPLE", people);
			
			Element orgsElement = documentElement.getChild("ORGS");
			List<Element> orgsElements = orgsElement.getChildren("D");
			String[] orgs = new String[orgsElements.size()];
			for (int i = 0; i < orgs.length; i++)
				orgs[i] = orgsElements.get(i).getText();
			metaData.put("ORGS", orgs);
			
			Element exchangesElement = documentElement.getChild("EXCHANGES");
			List<Element> exchangesElements = exchangesElement.getChildren("D");
			String[] exchanges = new String[exchangesElements.size()];
			for (int i = 0; i < exchanges.length; i++)
				exchanges[i] = exchangesElements.get(i).getText();
			metaData.put("EXCHANGES", exchanges);
			
			Element companiesElement = documentElement.getChild("COMPANIES");
			List<Element> companiesElements = companiesElement.getChildren("D");
			String[] companies = new String[companiesElements.size()];
			for (int i = 0; i < companies.length; i++)
				companies[i] = companiesElements.get(i).getText();
			metaData.put("COMPANIES", companies);
			
			Element textElement = documentElement.getChild("TEXT");
			List<Element> textChildren = (List<Element>)textElement.getChildren();
			for (Element textChild : textChildren) {
				if (textChild.getName().equals("BODY")) {
					documentText = textChild.getText();
				} else {
					metaData.put(textChild.getName(), new String[] { textChild.getText() });
				}
			}
			
			TextClassDocument document = new TextClassDocument(newIdAttribute, documentText, metaData, Language.English, annotator);
			documents.add(document);
		}
		
		return documents;
	}
	
	
	private static Element loadXMLFromFile(File file) {
		String fileText = readTextFromFile(file);
		SAXBuilder builder = new SAXBuilder();
		Document document = null;
		try {
			
			document = builder.build(new StringReader(fileText));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return document.getRootElement();
	}
	
	private static String readTextFromFile(File file) {
		BufferedReader documentTextReader = FileUtil.getFileReader(file.getAbsolutePath());
		StringBuilder documentText = new StringBuilder();
		
		try {
			documentTextReader.readLine(); // Skip the header
			documentText.append("<ROOT>").append("\n"); // Reuters documents are missing root element
			String line = null;
			
			while ((line = documentTextReader.readLine()) != null) {
				documentText.append(line.replaceAll("&#", "")).append("\n");
			}
			documentText.append("</ROOT>").append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return documentText.toString();
	}
}
