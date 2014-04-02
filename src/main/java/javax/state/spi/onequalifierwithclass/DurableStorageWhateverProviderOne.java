package javax.state.spi.onequalifierwithclass;

import javax.state.DurableStorageCapabiliity;
import javax.state.WhatEverCapabiliity;
import javax.state.spi.Provider;

/**
 * @author Antoine Sabot-Durand
 */

public class DurableStorageWhateverProviderOne extends Provider implements DurableStorageCapabiliity,WhatEverCapabiliity {
    @Override
    public String getName() {
        return "I'm durbale and whatever";
    }
}
