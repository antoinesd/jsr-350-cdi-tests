package javax.state.spi.multiqualifiers;

import javax.enterprise.util.AnnotationLiteral;

/**
 * @author Antoine Sabot-Durand
 */
public class DurableStorageCapableLiteral extends AnnotationLiteral<DurableStorageCapable> implements DurableStorageCapable {

   public static final DurableStorageCapableLiteral INSTANCE= new DurableStorageCapableLiteral();
}
