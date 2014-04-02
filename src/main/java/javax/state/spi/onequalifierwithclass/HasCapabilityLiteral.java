package javax.state.spi.onequalifierwithclass;

import javax.enterprise.util.AnnotationLiteral;
import javax.state.Capability;

/**
 * @author Antoine Sabot-Durand
 */
public class HasCapabilityLiteral extends AnnotationLiteral<HasCapability> implements HasCapability {

    public HasCapabilityLiteral(Class<? extends Capability> caps) {
        this.caps = caps;
    }

    private Class<? extends Capability> caps;


    @Override
    public Class<? extends Capability> value() {
        return caps;
    }
}
