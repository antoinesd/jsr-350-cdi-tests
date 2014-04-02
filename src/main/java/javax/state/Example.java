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
     * It's the only scenario to run out of the box
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
    
    
    /*  The other 2 scenario don't work out of the box : a portable extension must be developed to enable them. These approach limit
     * the use of Qualifier to programmatic lookup. direct Injection will have to be done with final type */

    /**
     * In this scenario there's only one qualifier with a nonbinding field containing an array of capability expressed as Enum
     * It's less verbose but is more oriented to a close set of capabilities. An extension should use the content of the array to
     * add unitary qualifiers to the bean. THIS SCENARIO NAS NO EXTENSION IMPLEMENTED THUS NO TEST
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

    /**
     * Same approach than the previous one, but we use the capability interfaces to make the distinction. Could be a better 
     * approach to let third party add their capability.
     * This scenario is also a good candidate for creating automatically qualified bean thru an extension. That would give 
     * less work to provider developper that wouldn't have any CDI specificity in their code...
     * TEST FOR THIS SCENARIO ARE IN javax.state.OneQualifierWithClassTest
     */
    public void queryProviderWithOneQualAndClassesForExactMartching() {

        Instance<Provider> resultSet;

        resultSet = providers.select(new HasCapabilityLiteral(javax.state.DurableStorageCapabiliity.class)); //Find the exact match

        if (resultSet.isUnsatisfied()) {
            //treat the error
        } else if (resultSet.isAmbiguous()) {
            //refined the search
        }
    }


}

