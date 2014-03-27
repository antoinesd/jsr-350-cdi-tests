package javax.state.spi.multiqualifiers;

import javax.enterprise.util.AnnotationLiteral;

/**
 * @author Antoine Sabot-Durand
 */
public class XATransactionCapableLiteral extends AnnotationLiteral<XATransactionCapable> implements XATransactionCapable {

   public static final XATransactionCapableLiteral INSTANCE= new XATransactionCapableLiteral();
}
