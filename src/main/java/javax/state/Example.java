package javax.state;

import static javax.state.spi.onequalifierwithenum.Has.Capability.DurableStorageEnum;
import static javax.state.spi.onequalifierwithenum.Has.Capability.WhatEverEnum;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.state.spi.Provider;
import javax.state.spi.multiqualifiers.DurableStorageCapableLiteral;
import javax.state.spi.multiqualifiers.WhatEverCapableLiteral;
import javax.state.spi.onequalifierwithclass.HasCapabilityLiteral;
import javax.state.spi.onequalifierwithenum.HasLiteral;

/**
 * @author Antoine Sabot-Durand
 */
public class Example {

    @Inject
    @Any
    Instance<Provider> providers;


    /**
     * In this scenario each capability has a specific Qaulifier annotation and corresponding literal for the request
     */
    public void queryProviderWithMultiQual() {

        Instance<Provider> resultSet;

        resultSet = providers.select(DurableStorageCapableLiteral.INSTANCE, WhatEverCapableLiteral.INSTANCE);

        if (resultSet.isUnsatisfied()) {
            //treat the error
        } else if (resultSet.isAmbiguous()) {
            //refined the search
        }
    }

    /**
     * In this scenario there's only one qualifier with a binding field containing an array of capability expressed as Enum
     * It's less verbose but is more oriented to a close set of capabilities.
     * This scenario and the one below are ok for a perfect match (won't find providers having the requested capabilities and
     * other). But they can be extended thru an extension to
     * return answers including provider with a super set of capabilites.
     */
    public void queryProviderWithOneQualForExactMartching() {

        Instance<Provider> resultSet;

        resultSet = providers.select(new HasLiteral(DurableStorageEnum, WhatEverEnum)); //Find the exact match

        if (resultSet.isUnsatisfied()) {
            //treat the error
        } else if (resultSet.isAmbiguous()) {
            //refined the search
        }
    }

    /**
     * Same approach than the previous one, but we use the capability interfaces to make the distinction. Could be a better 
     * approach to let third party add their capability.
     * This scenario is also a good candidate for creating automatically qualified bean thru an extension. That would give 
     * less work to provider developper that wouldn't have any CDI sp in their code...
     */
    public void queryProviderWithOneQualAndClassesForExactMartching() {

        Instance<Provider> resultSet;

        resultSet = providers.select(new HasCapabilityLiteral(javax.state.DurableStorageCapabiliity.class, 
                WhatEverCapabiliity.class)); //Find the exact match

        if (resultSet.isUnsatisfied()) {
            //treat the error
        } else if (resultSet.isAmbiguous()) {
            //refined the search
        }
    }


    /**
     * On a second thought this scenario is not possible out of the box
     * as  it would be impossible to add multiple @Has annotaion to a provider class.
     * Yet these provider could be added by an extension so it's still interesting to keep it.*
     */
    public void queryProviderWithOneQual() {

        Instance<Provider> resultSet;

        resultSet = providers.select(new HasLiteral(DurableStorageEnum), new HasLiteral(WhatEverEnum)); //Find provider 
        // having a super set of these qualifiers

        if (resultSet.isUnsatisfied()) {
            //treat the error
        } else if (resultSet.isAmbiguous()) {
            //refined the search
        }
    }


}

