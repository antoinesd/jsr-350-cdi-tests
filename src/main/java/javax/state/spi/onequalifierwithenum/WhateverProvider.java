package javax.state.spi.onequalifierwithenum;

import javax.state.spi.Provider;

/**
 * @author Antoine Sabot-Durand
 */
@Has({Has.Capability.WhatEverEnum, Has.Capability.DurableStorageEnum })
public class WhateverProvider extends Provider {
}
