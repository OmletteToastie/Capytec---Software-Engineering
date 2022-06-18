package taskentrycomponent;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Jtextfieldcharlimit extends PlainDocument{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int limit;

	  public Jtextfieldcharlimit(int limit) {
	    super();
		  this.limit = limit;
	  }

	  
	  public void insertString(int offs, String str, AttributeSet attr)
	      throws BadLocationException {
	    if (str == null)
	      return;

	    if ((getLength() + str.length()) <= limit) {
	      super.insertString(offs, str, attr);
	    }
	  }
	}