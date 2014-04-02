package javax.state.spi.multiqualifiers;

import javax.state.DurableStorageCapabiliity;
import javax.state.spi.Provider;

/**
 * @author Antoine Sabot-Durand
 */
@DurableStorageCapable
public class DurableStorageProvider extends Provider implements DurableStorageCapabiliity{
    @Override
    public String getName() {
        return "I'm durbale";
    }
}
