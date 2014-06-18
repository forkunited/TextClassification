package textclass.data.annotation;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TextClassDocumentSet {
	private List<TextClassDocument> documents;
	
	public TextClassDocumentSet() {
		this.documents = new ArrayList<TextClassDocument>();
	}
	
	public List<TextClassDocument> getDocuments() {
		return this.documents;
	}
	
	public boolean addDocument(TextClassDocument document) {
		this.documents.add(document);
		return true;
	}
	
	public boolean saveToJSONDirectory(String directoryPath) {
		for (TextClassDocument document : this.documents) {
			System.out.print("Outputting " + document.getName() + "... ");
			document.saveToJSONFile(new File(directoryPath, document.getName() + ".json").getAbsolutePath());
			System.out.println("Done.");
		}
		
		return true;
	}
	
	public static TextClassDocumentSet loadFromJSONDirectory(String directoryPath) {
		File directory = new File(directoryPath);
		TextClassDocumentSet documentSet = new TextClassDocumentSet();
		try {
			if (!directory.exists() || !directory.isDirectory())
				throw new IllegalArgumentException("Invalid directory: " + directory.getAbsolutePath());
			
			List<File> files = new ArrayList<File>();
			files.addAll(Arrays.asList(directory.listFiles()));
			int numTopLevelFiles = files.size();
			for (int i = 0; i < numTopLevelFiles; i++)
				if (files.get(i).isDirectory())
					files.addAll(Arrays.asList(files.get(i).listFiles()));
			
			List<File> tempFiles = new ArrayList<File>();
			for (File file : files) {
				if (!file.isDirectory() && file.getName().endsWith(".json")) {
					tempFiles.add(file);
				}
			}
			
			Collections.sort(tempFiles, new Comparator<File>() { // Ensure determinism
			    public int compare(File o1, File o2) {
			        return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
			    }
			});
			
			for (File file : tempFiles) {
				System.out.println("Loading document " + file.getName());
				documentSet.addDocument(new TextClassDocument(file.getAbsolutePath()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
		
		return documentSet;
	}
}