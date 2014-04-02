package javax.state.spi.onequalifierwithclass;

import javax.state.WhatEverCapabiliity;
import javax.state.spi.Provider;

/**
 * @author Antoine Sabot-Durand
 */
public class WhateverProviderOne extends Provider implements WhatEverCapabiliity {


    @Override
    public String getName() {
        return "Whatever Provider";
    }
}
