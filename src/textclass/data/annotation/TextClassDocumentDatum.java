package textclass.data.annotation;

import cost.data.annotation.CostDatumTools;
import ark.data.DataTools;
import ark.data.annotation.Datum;
import ark.data.annotation.Datum.Tools.LabelMapping;
import ark.data.annotation.nlp.TokenSpan;

public class TextClassDocumentDatum<L> extends Datum<L> {
	protected TextClassDocument textClassDocument;
	
	public TextClassDocumentDatum(int id, TextClassDocument textClassDocument, L label) {
		this.id = id;
		this.textClassDocument = textClassDocument;
		this.label = label;
	}
	
	public TextClassDocument getTextClassDocument() {
		return this.textClassDocument;
	}
	
	public static Tools<TextClassDocumentCategory> getCategoryTools(DataTools dataTools) {
		Tools<TextClassDocumentCategory> tools = new Tools<TextClassDocumentCategory>(dataTools) {
			@Override
			public TextClassDocumentCategory labelFromString(String str) {
				if (str.equals("comp.graphics"))
					return TextClassDocumentCategory.COMP_GRAPHICS;
				else if (str.equals("comp.os.ms-windows.misc"))
					return TextClassDocumentCategory.COMP_OS_MS_WINDOWS_MISC;
				else if (str.equals("comp.sys.ibm.pc.hardware"))
					return TextClassDocumentCategory.COMP_SYS_IBM_PC_HARDWARE;
				else if (str.equals("comp.sys.mac.hardware"))
					return TextClassDocumentCategory.COMP_SYS_MAC_HARDWARE;
				else if (str.equals("comp.windows.x"))
					return TextClassDocumentCategory.COMP_WINDOWS_X;
				else if (str.equals("rec.autos"))
					return TextClassDocumentCategory.REC_AUTOS;
				else if (str.equals("rec.motorcycles"))
					return TextClassDocumentCategory.REC_MOTORCYCLES;
				else if (str.equals("rec.sport.baseball"))
					return TextClassDocumentCategory.REC_SPORT_BASEBALL;
				else if (str.equals("rec.sport.hockey"))
					return TextClassDocumentCategory.REC_SPORT_HOCKEY;
				else if (str.equals("sci.crypt"))
					return TextClassDocumentCategory.SCI_CRYPT;
				else if (str.equals("sci.electronics"))
					return TextClassDocumentCategory.SCI_ELECTRONICS;
				else if (str.equals("sci.med"))
					return TextClassDocumentCategory.SCI_MED;
				else if (str.equals("sci.space"))
					return TextClassDocumentCategory.SCI_SPACE;
				else if (str.equals("misc.forsale"))
					return TextClassDocumentCategory.MISC_FORSALE;
				else if (str.equals("talk.politics.misc"))
					return TextClassDocumentCategory.TALK_POLITICS_MISC;
				else if (str.equals("talk.politics.guns"))
					return TextClassDocumentCategory.TALK_POLITICS_GUNS;
				else if (str.equals("talk.politics.mideast"))
					return TextClassDocumentCategory.TALK_POLITICS_MIDEAST;
				else if (str.equals("talk.religion.misc"))
					return TextClassDocumentCategory.TALK_RELIGION_MISC;
				else if (str.equals("alt.atheism"))
					return TextClassDocumentCategory.ALT_ATHEISM;
				else if (str.equals("soc.religion.christian"))
					return TextClassDocumentCategory.SOC_RELIGION_CHRISTIAN;
				else
					return TextClassDocumentCategory.valueOf(str);
			}
			
			
		};
		
		tools.addLabelMapping(new LabelMapping<TextClassDocumentCategory>() {
			@Override
			public String toString() {
				return "Clusters";
			}
			
			@Override
			public TextClassDocumentCategory map(TextClassDocumentCategory label) {
				if (label == TextClassDocumentCategory.ALT_ATHEISM)
					return TextClassDocumentCategory.ALT_ATHEISM;
				else if (label == TextClassDocumentCategory.COMP_GRAPHICS)
					return TextClassDocumentCategory.COMP_GRAPHICS;
				else if (label == TextClassDocumentCategory.COMP_OS_MS_WINDOWS_MISC)
					return TextClassDocumentCategory.COMP_GRAPHICS;
				else if (label == TextClassDocumentCategory.COMP_SYS_IBM_PC_HARDWARE)
					return TextClassDocumentCategory.COMP_GRAPHICS;
				else if (label == TextClassDocumentCategory.COMP_SYS_MAC_HARDWARE)
					return TextClassDocumentCategory.COMP_GRAPHICS;
				else if (label == TextClassDocumentCategory.COMP_WINDOWS_X)
					return TextClassDocumentCategory.COMP_GRAPHICS;
				else if (label == TextClassDocumentCategory.MISC_FORSALE)
					return TextClassDocumentCategory.MISC_FORSALE;
				else if (label == TextClassDocumentCategory.REC_AUTOS)
					return TextClassDocumentCategory.REC_AUTOS;
				else if (label == TextClassDocumentCategory.REC_MOTORCYCLES)
					return TextClassDocumentCategory.REC_AUTOS;
				else if (label == TextClassDocumentCategory.REC_SPORT_BASEBALL)
					return TextClassDocumentCategory.REC_AUTOS;
				else if (label == TextClassDocumentCategory.REC_SPORT_HOCKEY)
					return TextClassDocumentCategory.REC_AUTOS;
				else if (label == TextClassDocumentCategory.SCI_CRYPT)
					return TextClassDocumentCategory.SCI_CRYPT;
				else if (label == TextClassDocumentCategory.SCI_ELECTRONICS)
					return TextClassDocumentCategory.SCI_CRYPT;
				else if (label == TextClassDocumentCategory.SCI_MED)
					return TextClassDocumentCategory.SCI_CRYPT;
				else if (label == TextClassDocumentCategory.SCI_SPACE)
					return TextClassDocumentCategory.SCI_CRYPT;
				else if (label == TextClassDocumentCategory.SOC_RELIGION_CHRISTIAN)
					return TextClassDocumentCategory.ALT_ATHEISM;
				else if (label == TextClassDocumentCategory.TALK_POLITICS_GUNS)
					return TextClassDocumentCategory.TALK_POLITICS_GUNS;
				else if (label == TextClassDocumentCategory.TALK_POLITICS_MIDEAST)
					return TextClassDocumentCategory.TALK_POLITICS_GUNS;
				else if (label == TextClassDocumentCategory.TALK_POLITICS_MISC)
					return TextClassDocumentCategory.TALK_POLITICS_GUNS;
				else if (label == TextClassDocumentCategory.TALK_RELIGION_MISC)
					return TextClassDocumentCategory.ALT_ATHEISM;
				else 
					return null;
			}
		});
		
		tools.addLabelMapping(new LabelMapping<TextClassDocumentCategory>() {
			@Override
			public String toString() {
				return "FirstLevel";
			}
			
			@Override
			public TextClassDocumentCategory map(TextClassDocumentCategory label) {
				if (label == TextClassDocumentCategory.ALT_ATHEISM)
					return TextClassDocumentCategory.ALT_ATHEISM;
				else if (label == TextClassDocumentCategory.COMP_GRAPHICS)
					return TextClassDocumentCategory.COMP_GRAPHICS;
				else if (label == TextClassDocumentCategory.COMP_OS_MS_WINDOWS_MISC)
					return TextClassDocumentCategory.COMP_GRAPHICS;
				else if (label == TextClassDocumentCategory.COMP_SYS_IBM_PC_HARDWARE)
					return TextClassDocumentCategory.COMP_GRAPHICS;
				else if (label == TextClassDocumentCategory.COMP_SYS_MAC_HARDWARE)
					return TextClassDocumentCategory.COMP_GRAPHICS;
				else if (label == TextClassDocumentCategory.COMP_WINDOWS_X)
					return TextClassDocumentCategory.COMP_GRAPHICS;
				else if (label == TextClassDocumentCategory.MISC_FORSALE)
					return TextClassDocumentCategory.MISC_FORSALE;
				else if (label == TextClassDocumentCategory.REC_AUTOS)
					return TextClassDocumentCategory.REC_AUTOS;
				else if (label == TextClassDocumentCategory.REC_MOTORCYCLES)
					return TextClassDocumentCategory.REC_AUTOS;
				else if (label == TextClassDocumentCategory.REC_SPORT_BASEBALL)
					return TextClassDocumentCategory.REC_AUTOS;
				else if (label == TextClassDocumentCategory.REC_SPORT_HOCKEY)
					return TextClassDocumentCategory.REC_AUTOS;
				else if (label == TextClassDocumentCategory.SCI_CRYPT)
					return TextClassDocumentCategory.SCI_CRYPT;
				else if (label == TextClassDocumentCategory.SCI_ELECTRONICS)
					return TextClassDocumentCategory.SCI_CRYPT;
				else if (label == TextClassDocumentCategory.SCI_MED)
					return TextClassDocumentCategory.SCI_CRYPT;
				else if (label == TextClassDocumentCategory.SCI_SPACE)
					return TextClassDocumentCategory.SCI_CRYPT;
				else if (label == TextClassDocumentCategory.SOC_RELIGION_CHRISTIAN)
					return TextClassDocumentCategory.SOC_RELIGION_CHRISTIAN;
				else if (label == TextClassDocumentCategory.TALK_POLITICS_GUNS)
					return TextClassDocumentCategory.TALK_POLITICS_GUNS;
				else if (label == TextClassDocumentCategory.TALK_POLITICS_MIDEAST)
					return TextClassDocumentCategory.TALK_POLITICS_GUNS;
				else if (label == TextClassDocumentCategory.TALK_POLITICS_MISC)
					return TextClassDocumentCategory.TALK_POLITICS_GUNS;
				else if (label == TextClassDocumentCategory.TALK_RELIGION_MISC)
					return TextClassDocumentCategory.TALK_POLITICS_GUNS;
				else 
					return null;
			}
		});
		
		tools.addLabelMapping(new LabelMapping<TextClassDocumentCategory>() {
			@Override
			public String toString() {
				return "SecondLevel";
			}
			
			@Override
			public TextClassDocumentCategory map(TextClassDocumentCategory label) {
				if (label == TextClassDocumentCategory.ALT_ATHEISM)
					return TextClassDocumentCategory.ALT_ATHEISM;
				else if (label == TextClassDocumentCategory.COMP_GRAPHICS)
					return TextClassDocumentCategory.COMP_GRAPHICS;
				else if (label == TextClassDocumentCategory.COMP_OS_MS_WINDOWS_MISC)
					return TextClassDocumentCategory.COMP_OS_MS_WINDOWS_MISC;
				else if (label == TextClassDocumentCategory.COMP_SYS_IBM_PC_HARDWARE)
					return TextClassDocumentCategory.COMP_SYS_IBM_PC_HARDWARE;
				else if (label == TextClassDocumentCategory.COMP_SYS_MAC_HARDWARE)
					return TextClassDocumentCategory.COMP_SYS_IBM_PC_HARDWARE;
				else if (label == TextClassDocumentCategory.COMP_WINDOWS_X)
					return TextClassDocumentCategory.COMP_WINDOWS_X;
				else if (label == TextClassDocumentCategory.MISC_FORSALE)
					return TextClassDocumentCategory.MISC_FORSALE;
				else if (label == TextClassDocumentCategory.REC_AUTOS)
					return TextClassDocumentCategory.REC_AUTOS;
				else if (label == TextClassDocumentCategory.REC_MOTORCYCLES)
					return TextClassDocumentCategory.REC_MOTORCYCLES;
				else if (label == TextClassDocumentCategory.REC_SPORT_BASEBALL)
					return TextClassDocumentCategory.REC_SPORT_BASEBALL;
				else if (label == TextClassDocumentCategory.REC_SPORT_HOCKEY)
					return TextClassDocumentCategory.REC_SPORT_BASEBALL;
				else if (label == TextClassDocumentCategory.SCI_CRYPT)
					return TextClassDocumentCategory.SCI_CRYPT;
				else if (label == TextClassDocumentCategory.SCI_ELECTRONICS)
					return TextClassDocumentCategory.SCI_ELECTRONICS;
				else if (label == TextClassDocumentCategory.SCI_MED)
					return TextClassDocumentCategory.SCI_MED;
				else if (label == TextClassDocumentCategory.SCI_SPACE)
					return TextClassDocumentCategory.SCI_SPACE;
				else if (label == TextClassDocumentCategory.SOC_RELIGION_CHRISTIAN)
					return TextClassDocumentCategory.SOC_RELIGION_CHRISTIAN;
				else if (label == TextClassDocumentCategory.TALK_POLITICS_GUNS)
					return TextClassDocumentCategory.TALK_POLITICS_GUNS;
				else if (label == TextClassDocumentCategory.TALK_POLITICS_MIDEAST)
					return TextClassDocumentCategory.TALK_POLITICS_GUNS;
				else if (label == TextClassDocumentCategory.TALK_POLITICS_MISC)
					return TextClassDocumentCategory.TALK_POLITICS_GUNS;
				else if (label == TextClassDocumentCategory.TALK_RELIGION_MISC)
					return TextClassDocumentCategory.TALK_RELIGION_MISC;
				else 
					return null;
			}
		});
		
		return tools;
	}
	
	public static Tools<TextClassDocumentTopic> getTopicTools(DataTools dataTools) {
		Tools<TextClassDocumentTopic> tools = new Tools<TextClassDocumentTopic>(dataTools) {
			@Override
			public TextClassDocumentTopic labelFromString(String str) {
				return TextClassDocumentTopic.valueOf(str.toUpperCase().replace("-", "_"));
			}
		};
		
		tools.addLabelMapping(new LabelMapping<TextClassDocumentTopic>() {
			@Override
			public String toString() {
				return "First";
			}
			
			@Override
			public TextClassDocumentTopic map(TextClassDocumentTopic label) {
				String labelStr = label.toString();
				int underscoreIndex = labelStr.indexOf("_");
				if (underscoreIndex < 0)
					return label;
				
				String first = labelStr.substring(0, underscoreIndex);
				if (first.equals("CASTOR"))
					return TextClassDocumentTopic.CASTOR_MEAL;
				else if (first.equals("COCONUT"))
					return TextClassDocumentTopic.COCONUT;
				else if (first.equals("CORN"))
					return TextClassDocumentTopic.CORN;
				else if (first.equals("GROUNDNUT"))
					return TextClassDocumentTopic.GROUNDNUT;
				else if (first.equals("LIN"))
					return TextClassDocumentTopic.LIN_MEAL;
				else if (first.equals("MONEY"))
					return TextClassDocumentTopic.MONEY_FX;
				else if (first.equals("PALM"))
					return TextClassDocumentTopic.PALM_MEAL;
				else if (first.equals("RAPE"))
					return TextClassDocumentTopic.RAPE_MEAL;
				else if (first.equals("SOY"))
					return TextClassDocumentTopic.SOY_MEAL;
				else if (first.equals("SUN"))
					return TextClassDocumentTopic.SUN_MEAL;
				else if (first.equals("TUNG"))
					return TextClassDocumentTopic.TUNG;
				else
					return label;
			}
		});
		
		tools.addLabelMapping(new LabelMapping<TextClassDocumentTopic>() {
			@Override
			public String toString() {
				return "Second";
			}
			
			@Override
			public TextClassDocumentTopic map(TextClassDocumentTopic label) {
				String labelStr = label.toString();
				int underscoreIndex = labelStr.indexOf("_");
				if (underscoreIndex < 0)
					return label;
				
				String second = labelStr.substring(underscoreIndex);
				if (second.equals("MEAL"))
					return TextClassDocumentTopic.CASTOR_MEAL;
				else if (second.equals("OIL"))
					return TextClassDocumentTopic.CASTOR_OIL;
				else if (second.equals("CAKE"))
					return TextClassDocumentTopic.COPRA_CAKE;
				else if (second.equals("CATTLE"))
					return TextClassDocumentTopic.F_CATTLE;
				else
					return label;
			}
		});
		
		return tools;
	}
	
	private static abstract class Tools<L> extends CostDatumTools<TextClassDocumentDatum<L>, L> {
		public Tools(DataTools dataTools) {
			super(dataTools);
			
			this.addTokenSpanExtractor(new TokenSpanExtractor<TextClassDocumentDatum<L>, L>() {
				@Override
				public String toString() {
					return "SentenceTokenSpans";
				}
				
				@Override
				public TokenSpan[] extract(TextClassDocumentDatum<L> textClassDocumentDatum) {
					TextClassDocument document = textClassDocumentDatum.getTextClassDocument();
					int sentenceCount = document.getSentenceCount();
					TokenSpan[] sentenceSpans = new TokenSpan[sentenceCount];
					for (int i = 0; i < sentenceCount; i++) {
						sentenceSpans[i] = new TokenSpan(document, i, 0, document.getSentenceTokenCount(i));
					}
					
					return sentenceSpans;
				}
			});
		}
	}
}
