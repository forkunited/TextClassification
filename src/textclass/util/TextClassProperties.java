package textclass.util;

import ark.util.Properties;

/**
 * TextClassProperties loads in a textclass.properties configuration
 * file.
 * 
 * @author Bill McDowell
 *
 */
public class TextClassProperties extends Properties {
	private String textDocumentDataDirPath;
	private String experimentInputDirPath;
	private String experimentOutputDirPath;
	
	public TextClassProperties() {
		super(new String[] { "textclass.properties" } );
		
		this.textDocumentDataDirPath = loadProperty("textDocumentDataDirPath");
		this.experimentInputDirPath = loadProperty("experimentInputDirPath");
		this.experimentOutputDirPath = loadProperty("experimentOutputDirPath");
	}
	
	public String getTextDocumentDataDirPath() {
		return this.textDocumentDataDirPath;
	}

	public String getExperimentInputDirPath() {
		return this.experimentInputDirPath;
	}
	
	public String getExperimentOutputDirPath() {
		return this.experimentOutputDirPath;
	}
}