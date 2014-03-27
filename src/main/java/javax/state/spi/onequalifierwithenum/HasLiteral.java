package javax.state.spi.onequalifierwithenum;

import javax.enterprise.util.AnnotationLiteral;

/**
 * @author Antoine Sabot-Durand
 */
public class HasLiteral extends AnnotationLiteral<Has> implements Has {

    public HasLiteral(Capability... caps) {
        this.caps = caps;
    }

    private Capability[] caps;
    
    @Override
    public Capability[] value() {
        return caps;
    }
}
