package javax.state.spi.onequalifierwithenum;

import javax.state.spi.Provider;

/**
 * @author Antoine Sabot-Durand
 */
@Has(Has.Capability.WhatEverEnum)
public class WhateverProvider extends Provider {
    @Override
    public String getName() {
        return null;
    }
}
