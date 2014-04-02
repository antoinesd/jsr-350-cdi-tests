package javax.state.spi.onequalifierwithclass;

import org.apache.deltaspike.core.util.metadata.builder.AnnotatedTypeBuilder;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.state.Capability;
import javax.state.spi.Provider;

/**
 * @author Antoine Sabot-Durand
 */
public class CapabilityExtension implements Extension {
    
    
    public void startExtension(@Observes BeforeBeanDiscovery bbd)
    {
        System.out.println("*** JSR 350 extension is starting ***");
    }
    

    public void enrichProvider(@Observes ProcessAnnotatedType<? extends Provider> pat) {

        Set<Class<?>> capabilities = resolveAllCapabilityType(pat.getAnnotatedType());

        AnnotatedTypeBuilder atb = new AnnotatedTypeBuilder();
        atb.readFromType(pat.getAnnotatedType());

        for (Type c : capabilities) {
            atb.addToClass(new HasCapabilityLiteral((Class<? extends Capability>) c));
        }

        pat.setAnnotatedType(atb.create());


    }

    private Set<Class<?>> resolveAllCapabilityType(AnnotatedType<? extends Provider> type) {
        Set<Class<?>> res = new HashSet<Class<?>>();

        /*for (Class<?> c = type.getJavaClass(); c != Object.class && c != null; c = c.getSuperclass()) {
            if (Provider.class.isAssignableFrom(c))
                res.add(c);
        }*/
        for (Class<?> i : type.getJavaClass().getInterfaces()) {
            if (Capability.class.isAssignableFrom(i))
                res.add(i);
        }

        return res;

    }


}
