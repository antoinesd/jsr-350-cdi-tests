package javax.state.spi.multiqualifiers;

import javax.state.DurableStorageCapabiliity;
import javax.state.WhatEverCapabiliity;
import javax.state.spi.Provider;

/**
 * @author Antoine Sabot-Durand
 */
@DurableStorageCapable
@WhatEverCapable
public class DurableStorageWhateverProvider extends Provider implements DurableStorageCapabiliity,WhatEverCapabiliity {
    @Override
    public String getName() {
        return "I'm durbale and whatever";
    }
}
