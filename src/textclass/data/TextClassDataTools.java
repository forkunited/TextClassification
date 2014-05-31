package textclass.data;

import textclass.util.TextClassProperties;

import ark.data.DataTools;
import ark.util.OutputWriter;

public class TextClassDataTools extends DataTools {
	private TextClassProperties properties;
	
	public TextClassDataTools(OutputWriter outputWriter, TextClassProperties properties) {
		super(outputWriter);
		this.properties = properties;
		
		// From http://qwone.com/~jason/writing/loocv.pdf
		// Only use on unigrams
		this.addCleanFn(new DataTools.StringTransform() {
			public String toString() {
				return "TextClassCleanFn";
			}
			
			public String transform(String str) {
				str = str.trim();
				
				//if (str.equals("$") || str.equals("&") || str.equals("+") || str.equals("@"))
				//	return str; // Keep these symbols... differs from http://qwone.com/~jason/writing/loocv.pdf
				
				if (str.length() >= 25)
					return "";
				
				str = str.toLowerCase()
						.replaceAll("[\\W&&[^\\s]]+", "") // replaces all non-alpha-numeric (differs from http://qwone.com/~jason/writing/loocv.pdf)
						.replaceAll("_", "")
						.replaceAll("\\d+.*", ""); // replace numbers
				
				return str;
			}
		}
		);
	}
	
	public TextClassProperties getProperties() {
		return this.properties;
	}
}