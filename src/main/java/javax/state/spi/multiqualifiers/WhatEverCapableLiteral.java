package javax.state.spi.multiqualifiers;

import javax.enterprise.util.AnnotationLiteral;

/**
 * @author Antoine Sabot-Durand
 */
public class WhatEverCapableLiteral extends AnnotationLiteral<WhatEverCapable> implements WhatEverCapable {

   public static final WhatEverCapableLiteral INSTANCE= new WhatEverCapableLiteral();
}
