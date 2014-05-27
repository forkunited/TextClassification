package textclass.data.annotation;

import ark.data.DataTools;
import ark.data.annotation.Datum;
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
					return TextClassDocumentCategory.TALK_RELIGION_MISC;
				else
					return TextClassDocumentCategory.valueOf(str);
			}
			
			
		};
		
		return tools;
	}
	
	private static abstract class Tools<L> extends Datum.Tools<TextClassDocumentDatum<L>, L> {
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
