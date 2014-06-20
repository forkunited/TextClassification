package textclass.data;

import textclass.util.TextClassProperties;

import ark.data.DataTools;
import ark.util.OutputWriter;

/**
 * TextClassDataTools contains data tools (gazetteers, text
 * cleaning functions, etc) for text classification.
 * 
 * @author Bill McDowell
 *
 */
public class TextClassDataTools extends DataTools {
	private TextClassProperties properties;
	
	public TextClassDataTools(OutputWriter outputWriter, TextClassProperties properties) {
		super(outputWriter);
		this.properties = properties;
		
		// Allows experiment configuration files to load saved models from 
		// the 'ExperimentOutputPath' directory
		this.addPath("ExperimentOutputPath", new Path("ExperimentOutputPath", this.properties.getExperimentOutputDirPath()));
		
		this.addCleanFn(new TextClassCleanFn());
	}
	
	public TextClassProperties getProperties() {
		return this.properties;
	}
}